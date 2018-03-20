import protobuf.MovementMessage.setDirection;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.net.SocketAddress;
import java.util.ArrayList;

/**
 * Created by A. Bashilov on 20/03/18
 *
 * Class for read channel, decode and pars DatagramPacker to SocketAddress and protobuf "setDirection"
 *
 * {@link #getSetDirection(DatagramPacket)} - method decode DatagramPacket to protobuf "setDirection",
 * return setDirection;
 *
 * {@link #addSocketAddress(DatagramPacket)} - method for add new SocketAddress to ArrayList;
 *
 * {@link #send(ChannelHandlerContext, DatagramPacket)} - method sending test protobuf "setDirection" to client;
 *
 * {@link #channelRead0(ChannelHandlerContext, DatagramPacket)} - override method for read channel;
 *
 */


public class ServerDecoderAndPacketHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    //Создаем коллекцию для хранения подключений
    private ArrayList<SocketAddress> socketAddresses = new ArrayList<SocketAddress>();
    private SocketAddress socketAddress;

    private setDirection getSetDirection(DatagramPacket packet) throws InvalidProtocolBufferException {
        //Забираем контент из DatagramPacket
        final ByteBuf byteBuf = packet.content();

        //Декодируем данные из DatagramPacket
        final byte[] array;
        final int offset;
        final int length = byteBuf.readableBytes(); //Определяем занимаемое пакетом пространство
        if (byteBuf.hasArray()) {
            array = byteBuf.array();
            offset = byteBuf.arrayOffset() + byteBuf.readerIndex();
        } else {
            array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
            offset = 0;
        }

        //Собираем объект Protobuf из массива
        setDirection messageIn = setDirection
                .getDefaultInstance()
                .newBuilderForType()
                .mergeFrom(array,offset,length)
                .build();
        return messageIn;
    }

    private void addSocketAddress(DatagramPacket packet) {
        //Парсим с DatagramPacket данные от клиента
        SocketAddress socketAddress = packet.sender();
        //Собираем в коллекцию новых клиентов
        if (!socketAddresses.contains(socketAddress)) {
            socketAddresses.add(socketAddress);
        }
    }

    private void send(ChannelHandlerContext ctx, DatagramPacket packet) {
        //Создаем объект Protobuf для отравки клиенту
        setDirection messageOut = setDirection.newBuilder()
                .setDirectionX(1 * packet.sender().getPort())
                .setDirectionY(1 * packet.sender().getPort()).build();

        SocketAddress socketAddress = packet.sender();
        //Устанавливаем подключение к клиенту
        ctx.connect(socketAddress);
        //Отправляем объект клиенту
        ctx.writeAndFlush(messageOut);
        //Закрываем подключение с клиентом
        ctx.disconnect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        setDirection messageIn = getSetDirection(msg);
        addSocketAddress(msg);
        send(ctx,msg);
        //Вывод в консоль для проверки отправленных данных
        System.out.println("Сервер получил сообщение DirectionX: " + messageIn.getDirectionX() + " DirectionY: " + messageIn.getDirectionY());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
