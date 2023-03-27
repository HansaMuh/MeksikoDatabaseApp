import javax.swing.*;

public class DatabaseInterface extends JFrame
{

    private JTabbedPane TabbedPane;
    private InsertMenu InsertMenu;
    private ViewMenu ViewMenu;
    private UpdateMenu UpdateMenu;
    private DeleteMenu DeleteMenu;

    public DatabaseInterface(String companyName)
    {
        super(companyName + " Database Manager");
        initialize();
    }

    private void initialize()
    {
        TabbedPane = new JTabbedPane();
        InsertMenu = new InsertMenu();
        ViewMenu = new ViewMenu();
        UpdateMenu = new UpdateMenu();
        DeleteMenu = new DeleteMenu();

        TabbedPane.addTab("Insert Menu", InsertMenu);
        TabbedPane.addTab("View Menu", ViewMenu);
        TabbedPane.addTab("Update Menu", UpdateMenu);
        TabbedPane.addTab("Delete Menu", DeleteMenu);
        TabbedPane.addChangeListener(evt ->
        {
            switch (TabbedPane.getSelectedIndex())
            {
                // Insert Menu
                case 0:
                    this.setSize(500, 195);
                    break;
                // View Menu
                case 1:
                    this.setSize(500, 200);
                    ViewMenu.viewTable();
                    break;
                // Update Menu
                case 2:
                    this.setSize(500, 170);
                    break;
                // Delete Menu
                case 3:
                    this.setSize(500, 117);
                    break;
            }
        });
        this.add(TabbedPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
