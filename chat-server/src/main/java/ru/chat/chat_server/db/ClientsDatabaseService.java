package ru.chat.chat_server.db;
import ru.chat.chat_server.error.ChangeNickException;
import ru.chat.chat_server.error.WrongCredentialsException;

import java.io.IOException;
import java.sql.*;


public class ClientsDatabaseService {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTION = "jdbc:sqlite:data/clients.db";
    private static final String GET_USERNAME = "select username from clients where login = ? and password = ?;";
    private static final String CHANGE_USERNAME = "update clients set username = ? where login = ?;";
    private static final String CREATE_DB = "create table if not exists clients (id integer primary key autoincrement," +
            " login text unique not null, password text not null, username text unique not null);";
    private static final String INIT_DB = "insert into clients (login, password, username) " +
            "values ('log1', 'pass1', 'user1'), ('log2', 'pass2', 'user2'), ('log3', 'pass3', 'user3');";
    private static ClientsDatabaseService instance;
    private static Connection connection;

    PreparedStatement getClientStatement;
    PreparedStatement changeNickStatement;

    private ClientsDatabaseService(){
        try {
            connect();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
       //createDb();
    }

    public String changeNick(String login, String newNick) {
        try (PreparedStatement ps = connection.prepareStatement(CHANGE_USERNAME)){
        ps.setString (1, newNick);
        ps.setString (2, login);
        if (ps.executeUpdate() > 0) return newNick;
    } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ChangeNickException("Something going wrong");
}

    public String getClientNameByLoginPass(String login, String pass){
        try
            (PreparedStatement ps = connection.prepareStatement(GET_USERNAME)){
                ps.setString(1,login);
                ps.setString(2,pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    String result = rs.getString("username");
                    rs.close();
                    System.out.printf("login is %s\n", result);
                    return result;//return value;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            throw  new WrongCredentialsException("User not found");
    }

    private void createDb(){
        try
            (Statement st = connection.createStatement()){
                st.execute(CREATE_DB);
                st.execute(INIT_DB);
            }catch (SQLException throwables){
            throwables.printStackTrace();
            }
        }

    public static ClientsDatabaseService getInstance(){
        if (instance != null) return instance;
        instance = new ClientsDatabaseService();
        return instance;
    }


    private static void connect() throws SQLException, ClassNotFoundException{
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(CONNECTION);
        System.out.println("Connected to db!");
    }


    public void closeConnection() {
        try {
            if (getClientStatement != null) getClientStatement.close();
            if (changeNickStatement != null) changeNickStatement.close();
            if (connection != null) connection.close();
            System.out.println("Disconnected from db!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
