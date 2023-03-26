import javax.swing.*;
import java.sql.*;

public class MeksikoDatabaseApp
{

    // Application's Database Manager
    public static DatabaseManager Manager;
    // Application's Main Interface
    public static DatabaseInterface Interface;

    public static void main(String[] args)
    {
        initialize();
    }

    // Function to initializes the Application's main interface
    private static void initialize()
    {
        String port = "3306", urlHeader = "PTPudding", username = "root", password = "", title = "PT Pudding Database Manager";
        if (isSafeToProceed(port, username, password))
        {
            Manager = new DatabaseManager(port, urlHeader, username, password);
            Interface = new DatabaseInterface(title);
        }
    }

    // Function to check whether it is safe to run the application based on certain conditions
    private static boolean isSafeToProceed(String port, String username, String password)
    {
        boolean code = true;

        // Checks on the MySQL Connector for Java driver and its connection
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/", username, password);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error detected. You cannot run this application.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            code = false;
        }

        return code;
    }

}