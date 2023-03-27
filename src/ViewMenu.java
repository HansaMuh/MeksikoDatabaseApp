import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;

public class ViewMenu extends JPanel
{

    private JTable DatabaseTable;

    private JScrollPane ScrollPane;

    public ViewMenu()
    {
        initialize();
    }

    private void initialize()
    {
        this.setLayout(new BorderLayout());

        DatabaseTable = new JTable();
        ScrollPane = new JScrollPane(DatabaseTable);

        this.add(ScrollPane, BorderLayout.CENTER);

        viewTable();
    }

    public void viewTable()
    {
        try
        {
            Main.Manager.PreparedStatement = Main.Manager.prepareStatement("SELECT * FROM menu");
            ResultSet result = Main.Manager.PreparedStatement.executeQuery();
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]
                    {
                            "Code",
                            "Name",
                            "Price",
                            "Stock Quantity"
                    }, 0);

            while (result.next())
            {
                Object[] rowData =
                        {
                                result.getString("code_menu"),
                                result.getString("name_menu"),
                                result.getLong("price_menu"),
                                result.getLong("stockQuantity_menu")
                        };
                tableModel.addRow(rowData);
            }
            DatabaseTable.setModel(tableModel);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}