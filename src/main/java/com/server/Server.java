package com.server;

import javax.servlet.ServletException;
import com.undertow.standalone.UndertowServer;
import java.util.concurrent.locks.Condition;

import static com.util.cloud.DeploymentConfiguration.getProperty;



public class Server {

    public static void main(String[] args)
            throws ServletException {

        final String host = getProperty("undertow.host", "localhost");
        final Integer port = getProperty("undertow.port", 8080);

        final UndertowServer server = new UndertowServer(host, port, "conference-management.jar");

        final Condition newCondition = server.LOCK.newCondition();

        server.start();
        try {
            while (true)
                newCondition.awaitNanos(1);
        } catch (InterruptedException cause) {
            server.stop();
        }
    }

}
