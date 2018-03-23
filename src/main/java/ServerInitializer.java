import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by A. Bashilov on 20/03/18
 *
 * Class contain pipline for channel when pipelin contain handlers for:
 * 1) Logging
 * 2) Encode out message
 * 3) Decode in message and save info from DatagramPacket
 *
 */


public class ServerInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel socketChannel) throws Exception {

        //Создаем пайплайн для работы с каналом (каждый попадающий пакет в пайплайн проходит по порядку через все хэндлеры)
        ChannelPipeline pipeline = socketChannel.pipeline();

        //Добавляем логгер для отладки
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        //Добавляем энкодер для серриализации отправляемых объектов Protobuf
        pipeline.addLast(new ProtobufEncoder());

        //Создаем хэндлер для пакетов Datagram, тут их ловим, парсим и обрабатываем
        pipeline.addLast(new ServerDecoderAndPacketHandler());

    }
}
