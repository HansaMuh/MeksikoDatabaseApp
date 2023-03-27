import javax.swing.*;
import java.sql.*;

public class Main
{

    // Application's Database Manager
    public static DatabaseManager Manager;

    // Application's Main Interface
    public static DatabaseInterface Interface;

    public static void main(String[] args)
    {
        initialize();
    }

    // Function to initialize the Application's main interface
    private static void initialize()
    {
        // Important variables for accessing and managing your company's database
        String port = "3306", urlHeader = "PTPudding", username = "root", password = "", companyName = "PT Pudding";

        if (isSafeToProceed(port, username, password))
        {
            Manager = new DatabaseManager(port, urlHeader, username, password);
            Interface = new DatabaseInterface(companyName);
        }
    }

    // Function to check whether it is safe to run the application based on certain conditions
    // Conditions:
    // Must have the MySQL Connector for Java driver attached to the project and be connected to the local server (MySQL) on the targeted port
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