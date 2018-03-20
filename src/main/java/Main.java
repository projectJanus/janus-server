public class Main {
    public static void main(String[] args) {
        Server server = new Server(9999);
        try {
            server.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
