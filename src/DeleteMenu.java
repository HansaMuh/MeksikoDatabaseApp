import javax.swing.*;
import java.awt.*;

public class DeleteMenu extends JPanel
{

    private JTextField CodeField;

    private JButton DeleteButton;

    public DeleteMenu()
    {
        initialize();
    }

    private void initialize()
    {
        this.setLayout(new GridLayout(2, 2));

        this.add(new JLabel("Code"));
        this.add(CodeField = new JTextField("PD-"));
        this.add(new JLabel(""));
        this.add(DeleteButton = new JButton("Delete"));

        DeleteButton.addActionListener(actionListener ->
        {
            int confirmCode = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this menu?",
                    "Confirmation for Delete Menu",
                    JOptionPane.YES_NO_OPTION);
            if (confirmCode == JOptionPane.YES_OPTION)
            {
                deleteData();
            }
        });
    }

    public void deleteData()
    {
        try
        {
            Main.Manager.PreparedStatement = Main.Manager.prepareStatement("DELETE FROM menu WHERE code_menu = ?");
            Main.Manager.PreparedStatement.setString(1, CodeField.getText());

            if (Main.Manager.PreparedStatement.executeUpdate() == 0)
            {
                JOptionPane.showMessageDialog(this, "Menu not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Menu deleted!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                CodeField.setText("PD-");
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error detected.\nDetails:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}