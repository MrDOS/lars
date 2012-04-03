package lars.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private Dimension dim;
    
    public ImagePanel(String file)
    {
        try
        {
            image = ImageIO.read(new File (file));
            dim = new Dimension(image.getWidth(), image.getHeight());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    @Override
    public Dimension getSize()
    {
        return dim;
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return dim;
    }
    
    @Override
    public Dimension getMaximumSize()
    {
        return dim;
    }
    
    @Override
    public Dimension getMinimumSize()
    {
        return dim;
    }
}