package lars;

import javax.swing.JFrame;

import lars.gui.KioskFrame;

public class LARS
{
    public static final String name = "LARS";
    public static final String version = "1.0.0";

    public static void main(String[] args)
    {
        if (args.length > 0 && args[0].equals("-admin"))
        {
            // TODO: administrative terminal startup.
        }
        else
        {
            JFrame frame = new KioskFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}
