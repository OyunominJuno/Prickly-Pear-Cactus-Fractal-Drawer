import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

/**
 * FractalDraw class recieves shape data from the fractal data and fraws
 *
 * @author      Oyunomin Munkhkhurel
 * @version     3/3/2018
 */
public class FractalDraw extends JFrame implements Observer {
    private Subject data;
    private MyPanel panel;
    private ArrayList<Shape> temp;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    
    /*
     * Full constructor of this class
     * 
     * @param    Subject    subject class to register
     */
    public FractalDraw(Subject data) {
        this.data = data;
        data.registerObserver(this);
        setTitle("Cactus Drawing Application Result");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation(screenSize.width / 2, (screenSize.height - getHeight()) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        
        
        panel = new MyPanel();
        getContentPane().add(panel);
        
    }
    
    /*
     * If there is any new information this method retrieves the data and repaints
     */
    public void update() {
        panel.repaint();
        setVisible(true);
        JLabel background=new JLabel(new ImageIcon("backG.jpg"));
        background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        add(background);
    }
    
    /**
     * Private class MyPanel overrides paint component
     */
    private class MyPanel extends JPanel {
        /*
         * Full constructor
         */
        public MyPanel() {
            
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (Shape shape : data.getData(FRAME_WIDTH / 2, FRAME_HEIGHT / 6 * 4)) {
                shape.draw(g);
            }
        }
    }
}
