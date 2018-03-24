public class Main {
    public static void main(String[] args) {
        Server server = new Server("server.properties");
        try {
            server.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
