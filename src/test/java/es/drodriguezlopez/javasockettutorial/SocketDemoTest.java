package es.drodriguezlopez.javasockettutorial;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SocketDemoTest {

    private SocketDemo socketDemo = new SocketDemo();

    @Test
    public void socketTest() {
        // Lanzamos el servidor
        new Thread(() -> {
            try {
                socketDemo.executeServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Lanzamos los clientes
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    socketDemo.executeClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Esperamos que terminen
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}