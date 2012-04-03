package lars.gui.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lars.LARS;

/**
 * Top-level administative interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class AdminFrame extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private static AdminFrame _instance = null;

    private JMenuItem quit;

    private JDesktopPane desktop;

    private AdminFrame()
    {
        super(LARS.name + " " + LARS.version + " Administation");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setMinimumSize(new Dimension(600, 400));

        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        quit = new JMenuItem("Quit");
        quit.setMnemonic('Q');
        fileMenu.add(quit);

        bar.add(fileMenu);

        this.setJMenuBar(bar);

        desktop = new JDesktopPane();
        desktop.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        desktop.setBackground(Color.GRAY);
        this.add(desktop);

        quit.addActionListener(this);
    }

    public static AdminFrame getInstance()
    {
        if (_instance == null)
            _instance = new AdminFrame();

        return _instance;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(quit))
            System.exit(0);
    }
}
