import javax.swing.*;
import java.awt.*;

public class UpdateMenu extends JPanel
{

    private JLabel CodeLabel;
    private JLabel PriceLabel;
    private JLabel StockQuantityLabel;

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

        this.add(CodeLabel = new JLabel("Code"));
        this.add(CodeField = new JTextField());
        this.add(PriceLabel = new JLabel("Price"));
        this.add(PriceField = new JTextField());
        this.add(StockQuantityLabel = new JLabel("Stock Quantity"));
        this.add(StockQuantityField = new JTextField());
        this.add(new JLabel(""));
        this.add(UpdateButton = new JButton("Update"));

        UpdateButton.addActionListener(actionListener ->
        {
            updateData();
        });
    }

    public void updateData()
    {
        try
        {
            MeksikoDatabaseApp.Manager.PreparedStatement = MeksikoDatabaseApp.Manager.prepareStatement("UPDATE menu SET price_menu = ?, stockQuantity_menu = ? WHERE code_menu = ?");
            MeksikoDatabaseApp.Manager.PreparedStatement.setLong(1, Long.parseLong(PriceField.getText()));
            MeksikoDatabaseApp.Manager.PreparedStatement.setLong(2, Long.parseLong(StockQuantityField.getText()));
            MeksikoDatabaseApp.Manager.PreparedStatement.setString(3, CodeField.getText());

            if (MeksikoDatabaseApp.Manager.PreparedStatement.executeUpdate() == 0)
            {
                JOptionPane.showMessageDialog(this, "Menu not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Menu updated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}