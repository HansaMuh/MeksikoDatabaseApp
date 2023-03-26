import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class InsertMenu extends JPanel
{

    private JLabel CodeLabel;
    private JTextField CodeField;
    private JLabel NameLabel;
    private JTextField NameField;
    private JLabel PriceLabel;
    private JTextField PriceField;
    private JLabel StockQuantityLabel;
    private JTextField StockQuantityField;
    private JButton InsertButton;

    public InsertMenu()
    {
        initialize();
    }

    private void initialize()
    {
        this.setLayout(new GridLayout(5, 2));

        this.add(CodeLabel = new JLabel("Code"));
        this.add(CodeField = new JTextField());
        this.add(NameLabel = new JLabel("Name"));
        this.add(NameField = new JTextField());
        this.add(PriceLabel = new JLabel("Price"));
        this.add(PriceField = new JTextField());
        this.add(StockQuantityLabel = new JLabel("Stock Quantity"));
        this.add(StockQuantityField = new JTextField());
        this.add(new JLabel(""));
        this.add(InsertButton = new JButton("Insert"));

        CodeField.setEditable(false);
        CodeField.setText(generateCode());
        InsertButton.addActionListener(actionListener ->
        {
            insertData();
        });
    }

    private String generateCode()
    {
        Random random = new Random();
        String newCode = null;

        try
        {
            MeksikoDatabaseApp.Manager.PreparedStatement = MeksikoDatabaseApp.Manager.prepareStatement("SELECT * FROM menu");
            ResultSet result = MeksikoDatabaseApp.Manager.PreparedStatement.executeQuery();
            I1: while (true)
            {
                newCode = "PD-" + String.format("%03d", random.nextInt(999));

                I2: while (result.next())
                {
                    if (result.getString("code_menu") != newCode)
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

    private void insertData()
    {
        try
        {
            MeksikoDatabaseApp.Manager.PreparedStatement = MeksikoDatabaseApp.Manager.prepareStatement("INSERT INTO menu (code_menu, name_menu, price_menu, stockQuantity_menu) VALUES (?, ?, ?, ?)");
            MeksikoDatabaseApp.Manager.PreparedStatement.setString(1, CodeField.getText());
            MeksikoDatabaseApp.Manager.PreparedStatement.setString(2, NameField.getText());
            MeksikoDatabaseApp.Manager.PreparedStatement.setLong(3, Long.parseLong(PriceField.getText()));
            MeksikoDatabaseApp.Manager.PreparedStatement.setLong(4, Long.parseLong(StockQuantityField.getText()));
            MeksikoDatabaseApp.Manager.PreparedStatement.executeUpdate();

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