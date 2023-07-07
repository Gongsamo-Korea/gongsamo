package org.project.gongsamo.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class Reachable extends Thread {
    private String host = "localhost";
    private int port = 80;
    private int retry = 3;
    private Callback callback;

    public Reachable(String host, int port, Callback callback) {
        super("[Reachable] " + host + ":" + port);
        this.host = host;
        this.port = port;
        this.callback = callback;
    }

    public Reachable(String host, int port, int retry, Callback callback) {
        super("[Reachable] " + host + ":" + port);
        this.host = host;
        this.port = port;
        this.retry = retry;
        this.callback = callback;
    }

    @Override
    public void run() {
        int limit = this.retry;

        while (true) {
            log.info("[Reachable] Retry - " + (this.retry - limit + 1));
            if (--limit == 0) break;
            try {
                if (this.checkPortOpened(this.host, this.port)) {
                    log.info("[Reachable] Callback called");
                    try {
                        this.callback.call();
                    } catch (Exception e) {
                        log.error("[Reachable] Callback failed");
                    } finally {
                        break;
                    }
                }
                Thread.sleep(3000);
            } catch (Exception e) {}
        }

        log.info("[Reachable] Thread terminated");
    }

    private boolean checkPortOpened(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            log.error("[Reachable] Invalid host or port");
            return false;
        }
    }
}
