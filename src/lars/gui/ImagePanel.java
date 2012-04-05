package lars.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * An automatically-sizing image display component.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-02
 */
public class ImagePanel extends JComponent
{
    private static final long serialVersionUID = 1L;

    private BufferedImage image;
    private Dimension size;

    /**
     * Instantiate the component.
     * 
     * @param file
     *            the path at which the image is located
     * @throws IOException
     */
    public ImagePanel(String file) throws IOException
    {
        image = ImageIO.read(new File(file));
        size = new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getSize()
    {
        return size;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return size;
    }

    @Override
    public Dimension getMaximumSize()
    {
        return size;
    }

    @Override
    public Dimension getMinimumSize()
    {
        return size;
    }
}