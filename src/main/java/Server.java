import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import utils.DBHandler;
import utils.ServerConfiguration;

import java.net.InetAddress;

/**
 * Created by A. Bashilov on 20/03/18
 *
 * Class contains UDP server parameters and method {@link #start()} for start server with configuration specified in required {@link #configFile}
 *
 */

public class Server {
    private static final int THREADS = Runtime.getRuntime().availableProcessors() * 2; //Потоки для обработки соединений
    private String configFile;
    private DBHandler dbHandler;


    public Server(String configFile) {
        this.configFile = configFile;
    }
    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(THREADS); //Создаем группу потоков для обработки подключений/пакетов
        try {
            ServerConfiguration serverConfiguration = new ServerConfiguration(configFile);
            //Инициализируем подключение к БД
            dbHandler = new DBHandler(serverConfiguration.getDbConnectionURL(),serverConfiguration.getDbClassPath());

            Bootstrap bootstrap = new Bootstrap();//Создаем хэлпер для настройки
            //Настраиваем и наполняем сервер компонентами
            bootstrap.group(workerGroup) //Назначаем группу потоков
                    .channel(NioDatagramChannel.class) //Назначаем тип канала
                    .handler(new ServerInitializer()); //Вешаем на канал хендлеры
            //Биндим порт, и ждем подключений/пакетов
            bootstrap.bind(InetAddress.getLocalHost(), serverConfiguration.getServerPort()).sync().channel().closeFuture().await();


        } finally {
            //Аккуратно крашим сервер по завершению работы
            dbHandler.close();
            workerGroup.shutdownGracefully();
        }
    }
}
