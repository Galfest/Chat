package ru.chat.chat_server.db;
import ru.chat.chat_server.error.WrongCredentialsException;

import java.sql.*;


public class ClientsDatabaseService {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTION = "jdbc:sqlite:db/clients.db";
    private static final String GET_USERNAME = "select username from clients where login = ? and password = ?";
    private static final String CHANGE_USERNAME = "update client set username = ? where username = ?";
    private static final String CREATE_DB = "create table if not exist clients(id integer primary key autoincrement" +
            "login test unique not null, password text not null, username text unique not null);";
    private static final String INIT_DB = "insert into clients (login, password, username)" +
            "values ('log1', 'pass1', 'GodOfChat), ('log1', 'pass2', 'FirstMinion'), ('log3', 'pass3', 'SecondMinion');";

    private static ClientsDatabaseService instance;
    private static Connection connection;

    private ClientsDatabaseService(){
        try {
            connect();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
       createDb();
    }

    public String getClientNameByLoginPass(String login, String pass){
        try
            (PreparedStatement ps = connection.prepareCall(GET_USERNAME)){
                ps.setString(1,login);
                ps.setString(2,pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    String result = rs.getString("username");
                    rs.close();
                    System.out.printf("login is %s\n", result);
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


    public void closeConnection(){
        try {
            if (connection != null) connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
