package ru.chat.chat_server.auth;

import ru.chat.chat_server.db.ClientsDatabaseService;
import ru.chat.chat_server.entity.User;

public class DatabaseAuthService implements AuthService{
    private ClientsDatabaseService dbService;

    @Override
    public void start() {
        dbService = ClientsDatabaseService.getInstance();
    }

    @Override
    public void stop() {
        dbService.closeConnection();
    }

    @Override
    public String authorizeUserByLoginAndPassword(String login, String password) {
        return dbService.getClientNameByLoginPass(login, password);
    }

    @Override
    public String changeNick(String login, String newNick) {
        return null;
    }

    @Override
    public User createNewUser(String login, String password, String nick) {
        return null;
    }

    @Override
    public void deleteUser(String login, String pass) {

    }

    @Override
    public void changePassword(String login, String oldPass, String newPass) {

    }

    @Override
    public void resetPassword(String login, String newPass, String secret) {

    }
}
