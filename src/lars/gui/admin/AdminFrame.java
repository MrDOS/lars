package lars.gui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import lars.Account;
import lars.LARS;

/**
 * Top-level administative interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class AdminFrame extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private static AdminFrame _instance = null;

    private static final Dimension SIZE = new Dimension(600, 400);

    private static final int MAX_FRAME_INITIAL_LOCATION[] = { 200, 200 };
    private static final int FRAME_LOCATION_INCREMENT[] = { 20, 20 };
    private int frameInitialLocation[] = { MAX_FRAME_INITIAL_LOCATION[0] + 1,
            MAX_FRAME_INITIAL_LOCATION[1] + 1 };

    private Account account = null;

    private JMenuItem menuLogout;
    private JMenuItem menuQuit;

    private JButton accounts;
    private JButton items;
    private JButton rentals;
    private JButton logout;

    private JDesktopPane desktop;

    private AdminFrame()
    {
        super(LARS.name + " " + LARS.version + " Administation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setMinimumSize(SIZE);

        this.getContentPane().setLayout(new BorderLayout());

        this.setJMenuBar(this.getMenu());
        this.add(this.getToolbar(), BorderLayout.PAGE_START);
        this.desktop = this.getDesktop();
        this.add(this.desktop, BorderLayout.CENTER);

        menuLogout.addActionListener(this);
        menuQuit.addActionListener(this);

        accounts.addActionListener(this);
        items.addActionListener(this);
        rentals.addActionListener(this);
        logout.addActionListener(this);
    }

    /**
     * Get the frame instance.
     * 
     * @return the frame instance
     */
    public static AdminFrame getInstance()
    {
        if (_instance == null)
            _instance = new AdminFrame();

        return _instance;
    }

    /**
     * Get the account currently logged into the interface.
     * 
     * @return the account currently logged into the interface
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * Set the account currently logged into the interface.
     * 
     * @param account
     *            the account currently logged into the interface
     */
    public void setAccount(Account account)
    {
        this.account = account;
    }

    private JMenuBar getMenu()
    {
        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        menuLogout = new JMenuItem("Logout");
        menuLogout.setMnemonic('L');
        fileMenu.add(menuLogout);

        fileMenu.add(new JSeparator());

        menuQuit = new JMenuItem("Quit");
        menuQuit.setMnemonic('Q');
        fileMenu.add(menuQuit);

        bar.add(fileMenu);

        return bar;
    }

    private JToolBar getToolbar()
    {
        JToolBar toolbar = new JToolBar("Data");
        toolbar.setFloatable(false);

        accounts = new JButton("Accounts");
        toolbar.add(accounts);

        items = new JButton("Items");
        toolbar.add(items);

        rentals = new JButton("Rentals");
        toolbar.add(rentals);

        toolbar.add(Box.createHorizontalGlue());

        logout = new JButton("Logout");
        toolbar.add(logout);

        return toolbar;
    }

    private JDesktopPane getDesktop()
    {
        JDesktopPane desktop = new JDesktopPane();
        desktop.setBorder(BorderFactory.createLoweredBevelBorder());
        desktop.setBackground(Color.GRAY);
        return desktop;
    }

    /**
     * Show the login dialog.
     */
    public void login()
    {
        JDialog login = new LoginDialog();
        login.setVisible(true);

        for (Component c : desktop.getComponents())
        {
            c.setVisible(true);
        }
    }

    /**
     * Log the current user out.
     */
    private void logout()
    {
        this.account = null;

        for (Component c : desktop.getComponents())
        {
            c.setVisible(false);
        }

        this.login();
    }

    private void showInternalFrame(JInternalFrame frame)
    {
        frame.setLocation(this.getNewFramePoint());
        desktop.add(frame);
        frame.setVisible(true);
    }

    private Point getNewFramePoint()
    {
        if (this.frameInitialLocation[0] <= MAX_FRAME_INITIAL_LOCATION[0]
                && this.frameInitialLocation[1] <= MAX_FRAME_INITIAL_LOCATION[1])
        {
            this.frameInitialLocation[0] = this.frameInitialLocation[0]
                    + FRAME_LOCATION_INCREMENT[0];
            this.frameInitialLocation[1] = this.frameInitialLocation[1]
                    + FRAME_LOCATION_INCREMENT[1];
        }
        else
        {
            this.frameInitialLocation[0] = 0;
            this.frameInitialLocation[1] = 0;
        }

        return new Point(this.frameInitialLocation[0],
                this.frameInitialLocation[1]);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(menuLogout) || e.getSource().equals(logout))
            this.logout();
        else if (e.getSource().equals(this.accounts))
            this.showInternalFrame(new AccountsFrame());
        else if (e.getSource().equals(this.items))
            this.showInternalFrame(new ItemsFrame());
        else if (e.getSource().equals(this.rentals))
            this.showInternalFrame(new RentalsFrame());
        else if (e.getSource().equals(menuQuit))
            System.exit(0);
    }
}
