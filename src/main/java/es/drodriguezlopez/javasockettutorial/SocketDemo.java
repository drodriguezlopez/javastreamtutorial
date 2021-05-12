package es.drodriguezlopez.javasockettutorial;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class SocketDemo {

    public void executeServer() throws IOException {
        final int PUERTO = 5000;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ServerSocket skServidor = new ServerSocket(PUERTO, 0);
        while (true) {
            Socket skCliente = skServidor.accept();
            executorService.execute(acceptConnection(skCliente));
        }
        // No llega
        // skServidor.close();
    }

    private Runnable acceptConnection(Socket socket) {
        return () -> {
            try {
                String threadName = Thread.currentThread().getName();
                int port = ((InetSocketAddress) socket.getRemoteSocketAddress()).getPort();
                System.out.println("[" + threadName + "] acpetado clienteId " + port);
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                os.writeUTF("" + port);
                Stream<String> lines = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)).lines();
                lines.takeWhile(line -> !line.contains("quit")).forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    public void executeClient() throws IOException {
        final String HOST = "localhost";
        final int PUERTO = 5000;
        Socket socket = new Socket(HOST, PUERTO);
        DataInputStream is = new DataInputStream(socket.getInputStream());
        String clientId = is.readUTF();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        for (int i = 0; i < 1; i++) {
            os.writeBytes("[" + clientId + "] - " + i + "\n");
        }
        os.writeBytes("quit");
        socket.close();
    }


}
