package ru.chat.chat_server;

import ru.chat.chat_server.auth.InMemoryAuthService;
import ru.chat.chat_server.server.Server;

public class App {
    public static void main(String[] args) {
        new Server(new InMemoryAuthService()).start();
    }
}
