import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetAddress;

/**
 * Created by A. Bashilov on 20/03/18
 *
 * Class contain UDP server parameters and method {@link #start()} for start server with required {@link #port}
 *
 */

public class Server {
    private static final int THREADS = Runtime.getRuntime().availableProcessors() * 2; //Потоки для обработки соединений
    private final int port; // Порт сервера

    public Server(int port) {
        this.port = port;
    }
    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(THREADS); //Создаем группу потоков для обработки подключений/пакетов
        try {
            Bootstrap bootstrap = new Bootstrap();//Создаем хэлпер для настройки
            //Настраиваем и наполняем сервер компонентами
            bootstrap.group(workerGroup) //Назначаем группу потоков
                    .channel(NioDatagramChannel.class) //Назначаем тип канала
                    .handler(new ServerInitializer()); //Вешаем на канал хендлеры
            //Биндим порт, и ждем подключений/пакетов
            bootstrap.bind(InetAddress.getLocalHost(), port).sync().channel().closeFuture().await();

        } finally {
            //Аккуратно крашим сервер по завершению работы
            workerGroup.shutdownGracefully();
        }
    }
}
