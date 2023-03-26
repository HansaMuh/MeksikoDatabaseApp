import javax.swing.*;
import java.sql.*;

public class DatabaseManager
{

    public Connection Connection;
    public Statement Statement;
    public PreparedStatement PreparedStatement;

    private String URL;
    private String URLHeader;
    private String Username;
    private String Password;

    public DatabaseManager(String port, String urlHeader, String username, String password)
    {
        URL = "jdbc:mysql://localhost:" + port + "/";
        URLHeader = urlHeader;
        Username = username;
        Password = password;

        try
        {
            DriverManager.getConnection(URL + URLHeader, Username, Password);
        }
        catch (Exception ex)
        {
            createDatabase();
        }

        Connection = createConnection();
        Statement = createStatement();
    }

    public Connection createConnection()
    {
        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection(URL + URLHeader, Username, Password);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(MeksikoDatabaseApp.Interface, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return connection;
    }

    public Statement createStatement()
    {
        Statement statement = null;

        try
        {
            statement = Connection.createStatement();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(MeksikoDatabaseApp.Interface, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return statement;
    }

    public PreparedStatement prepareStatement(String parameter)
    {
        PreparedStatement statement = null;

        try
        {
            statement = Connection.prepareStatement(parameter);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(MeksikoDatabaseApp.Interface, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return statement;
    }

    public void createDatabase()
    {
        try
        {
            Connection mainConnection = DriverManager.getConnection(URL, Username, Password);
            Statement mainStatement = mainConnection.createStatement();
            mainStatement.executeUpdate("CREATE DATABASE " + URLHeader + ";");
            mainStatement.executeUpdate("USE " + URLHeader + ";");
            mainStatement.executeUpdate("CREATE TABLE menu (" +
                                    "code_menu VARCHAR(6) PRIMARY KEY," +
                                    "name_menu VARCHAR(100) NOT NULL," +
                                    "price_menu INT NOT NULL," +
                                    "stockQuantity_menu INT NOT NULL" +
                                    ");");
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(MeksikoDatabaseApp.Interface, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}