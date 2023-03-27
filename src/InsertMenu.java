import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class InsertMenu extends JPanel
{

    private JTextField CodeField;
    private JTextField NameField;
    private JTextField PriceField;
    private JTextField StockQuantityField;

    private JButton InsertButton;

    public InsertMenu()
    {
        initialize();
    }

    private void initialize()
    {
        this.setLayout(new GridLayout(5, 2));

        this.add(new JLabel("Code"));
        this.add(CodeField = new JTextField());
        this.add(new JLabel("Name"));
        this.add(NameField = new JTextField());
        this.add(new JLabel("Price"));
        this.add(PriceField = new JTextField());
        this.add(new JLabel("Stock Quantity"));
        this.add(StockQuantityField = new JTextField());
        this.add(new JLabel(""));
        this.add(InsertButton = new JButton("Insert"));

        CodeField.setEditable(false);
        CodeField.setText(generateCode());
        InsertButton.addActionListener(actionListener -> insertData());
    }

    public String generateCode()
    {
        Random random = new Random();
        String newCode = null;

        try
        {
            Main.Manager.PreparedStatement = Main.Manager.prepareStatement("SELECT * FROM menu");
            ResultSet result = Main.Manager.PreparedStatement.executeQuery();

            I1: while (true)
            {
                newCode = "PD-" + String.format("%03d", random.nextInt(999));
                I2: while (result.next())
                {
                    if (!result.getString("code_menu").equals(newCode))
                    {
                        break I1;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return newCode;
    }

    public void insertData()
    {
        try
        {
            Main.Manager.PreparedStatement = Main.Manager.prepareStatement("INSERT INTO menu (code_menu, name_menu, price_menu, stockQuantity_menu) VALUES (?, ?, ?, ?)");
            Main.Manager.PreparedStatement.setString(1, CodeField.getText());
            Main.Manager.PreparedStatement.setString(2, NameField.getText());
            Main.Manager.PreparedStatement.setLong(3, Long.parseLong(PriceField.getText()));
            Main.Manager.PreparedStatement.setLong(4, Long.parseLong(StockQuantityField.getText()));
            Main.Manager.PreparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Menu inserted!", "Success!", JOptionPane.INFORMATION_MESSAGE);

            CodeField.setText(generateCode());
            NameField.setText("");
            PriceField.setText("");
            StockQuantityField.setText("");
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}