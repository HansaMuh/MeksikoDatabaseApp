import javax.swing.*;
import java.awt.*;

public class UpdateMenu extends JPanel
{

    private JTextField CodeField;
    private JTextField PriceField;
    private JTextField StockQuantityField;

    private JButton UpdateButton;

    public UpdateMenu()
    {
        initialize();
    }

    private void initialize()
    {
        this.setLayout(new GridLayout(4, 2));

        this.add(new JLabel("Code"));
        this.add(CodeField = new JTextField("PD-"));
        this.add(new JLabel("Price"));
        this.add(PriceField = new JTextField());
        this.add(new JLabel("Stock Quantity"));
        this.add(StockQuantityField = new JTextField());
        this.add(new JLabel(""));
        this.add(UpdateButton = new JButton("Update"));

        UpdateButton.addActionListener(actionListener -> updateData());
    }

    public void updateData()
    {
        try
        {
            Main.Manager.PreparedStatement = Main.Manager.prepareStatement("UPDATE menu SET price_menu = ?, stockQuantity_menu = ? WHERE code_menu = ?");
            Main.Manager.PreparedStatement.setLong(1, Long.parseLong(PriceField.getText()));
            Main.Manager.PreparedStatement.setLong(2, Long.parseLong(StockQuantityField.getText()));
            Main.Manager.PreparedStatement.setString(3, CodeField.getText());

            if (Main.Manager.PreparedStatement.executeUpdate() == 0)
            {
                JOptionPane.showMessageDialog(this, "Menu not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Menu updated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                CodeField.setText("PD-");
                PriceField.setText("");
                StockQuantityField.setText("");
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}