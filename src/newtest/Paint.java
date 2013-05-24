package newtest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * opens a image file and makes the image transparent. uses
 * makeColorTransparent(im,color);
 *
 */
public class Paint extends JPanel {

    Image grass1 = GetTile(1, 1, "WorldTest");
    Image grass2 = GetTile(2, 1, "WorldTest");
    Image grass3 = GetTile(3, 1, "WorldTest");
    Image mounten = GetTile(1, 2, "WorldTest");
    Image archer = GetTile(2, 1, "UnitsTest");
    Image mage = GetTile(1, 1, "UnitsTest");

    public Paint() {
    }
    boolean showMoves = false;

    public Image GetTile(int imX, int imY, String file) {
        Image img;
        imX = (imX - 1) * 70;
        imY = (imY - 1) * 80;
        String filepath = Paint.class.getResource("images/" + file + ".png").toExternalForm();
        filepath = filepath.replace("file:/", "");
        filepath = filepath.replace("/", "\\");
        filepath = filepath.replace("%20", " ");
        File file1 = new File(filepath);
        BufferedImage image1;
        try {
            image1 = ImageIO.read(file1);
            image1 = image1.getSubimage(imX, imY, 70, 80);
            img = makeColorTransparent(image1, new Color(255, 0, 255), new Color(127, 0, 55));
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
            BufferedImage bi = new BufferedImage(70, 80, BufferedImage.TYPE_INT_RGB);
            bi.setRGB(255, 255, 255);
            img = bi.getSubimage(0, 0, 70, 80);
        }
        return img;
    }

    /**
     *
     * @param im as bufferdImage; this is the image you wand the transparent
     * color in
     * @param color as Color; this is the color you wand to make transparent
     * @return image with color replaced with transparency in im
     */
    private static Image makeColorTransparent(BufferedImage im, final Color color, final Color color2) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int transparentRGB = color.getRGB() | 0xFF000000;
            public int transparentRGB2 = color2.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == transparentRGB || ((rgb | 0xFF000000) == transparentRGB2)) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D test = (Graphics2D) g;
        drawWorld(g2d);
        Color color1 = new Color(255, 255,0);
        Color color2 = new Color(0,0,255);
        g2d.setColor(color1);
        test.setColor(color2);
        drawUnits(test);
        drawGUI(g2d);
        drawGUI2(test);
    }

    private Image TransformIdToImage(int id) {
        Image img = null;
        switch (id) {
            case 0:
                img = grass1;
                break;
            case 1:
                img = grass2;
                break;
            case 2:
                img = grass3;
                break;
            case 3:
                img = mounten;
                break;
            case 4:
                img = mage;
                break;
            case 5:
                img = archer;
                break;
            default:
                System.out.println("skipped tile with id: " + id);
        }
        return img;
    }

    private void drawWorld(Graphics2D g2d) {
        int i, j, xOut, yOut;
        xOut = GenerateWorld.GetWorldWidth();
        yOut = GenerateWorld.GetWorldHeight();
        Tile[][] r = GenerateWorld.GetWorld();
        for (j = 0; j < yOut; j++) {
            for (i = 0; i < xOut; i++) {
                g2d.drawImage(TransformIdToImage(r[i][j].getID()), i * 70 + j % 2 * 35, j * 60 + 35, null);
            }
        }
    }

    private void drawGUI(Graphics2D g2d) {
        String moneyPlayer1 = GUI.GetMoneyCount(1);
        String moneyPlayer2 = GUI.GetMoneyCount(2);
        String unitsPlayer1 = GUI.GetUnitsCount(1);
        String unitsPlayer2 = GUI.GetUnitsCount(2);
        int frameWidth = StartUp.getFrameWidth();
        g2d.drawString(moneyPlayer1, 20, 20);
        g2d.drawString(moneyPlayer2, 50, 20);
        g2d.drawString(unitsPlayer1, frameWidth - 70, 20);
        g2d.drawString(unitsPlayer2, frameWidth - 40, 20);
    }
    private void drawGUI2(Graphics2D g2d) {
        String moneyPlayer1 = GUI.GetMoneyCount(1);
        String moneyPlayer2 = GUI.GetMoneyCount(2);
        String unitsPlayer1 = GUI.GetUnitsCount(1);
        String unitsPlayer2 = GUI.GetUnitsCount(2);
        int frameWidth = StartUp.getFrameWidth();
        g2d.drawString(moneyPlayer1, 20, 40);
        g2d.drawString(moneyPlayer2, 50, 40);
        g2d.drawString(unitsPlayer1, frameWidth - 70, 40);
        g2d.drawString(unitsPlayer2, frameWidth - 40, 40);
    }
    

    private void drawUnits(Graphics2D g2d) {
        Player[] players = GenerateWorld.getPlayers();
        Player playerWithUnits = players[1];
        Units[] unitsToDraw = playerWithUnits.unitList();
        int arrayLenght = unitsToDraw.length;
        int i;
        for (i = 0; i < arrayLenght; i++) {
            Units unit = unitsToDraw[i];
            System.out.println(unit.getBigDamage());
            g2d.drawImage(TransformIdToImage(unit.getID()), unit.getXY().x * 70 + unit.getXY().y % 2 * 35, unit.getXY().y * 60 + 35, null);
        }
    
    }
}