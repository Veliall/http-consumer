package org.example;

import lombok.extern.java.Log;
import org.example.http.framework.Server;
import org.example.http.framework.resolver.argument.RequestHandlerMethodArgumentResolver;
import org.example.http.framework.resolver.argument.RequestHeaderHandlerMethodArgumentResolver;
import org.example.http.framework.resolver.argument.ResponseHandlerMethodArgumentResolver;

import java.io.IOException;

@Log
public class Main {
    public static void main(String[] args) {
        final var server = new Server(9999);
        server.autoRegisterHandlers("org.example.http.app");
        server.addArgumentResolver(
                new RequestHandlerMethodArgumentResolver(),
                new ResponseHandlerMethodArgumentResolver(),
                new RequestHeaderHandlerMethodArgumentResolver()
        );
    new Thread(() -> {
      try {
        Thread.sleep(1000);
        server.stop();
      } catch (InterruptedException | IOException e) {
        e.printStackTrace();
      }
    }).start();
        server.listen();
    }
}