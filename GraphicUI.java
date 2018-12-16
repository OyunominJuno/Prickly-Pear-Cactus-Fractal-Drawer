import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import java.awt.Color;
import javax.swing.JLabel;
/**
 * Graphics User Interface class that handles the user input and visuals
 *
 * @author      Oyunomin Munkhkhurel
 * @version     2/28/2018
 */
public class GraphicUI extends JFrame {
    //source https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 800;
    private Toolkit toolkit;
    private Color colorCactus, colorPear;
    //for recursion depth JSlider
    static final int DEPTH_MAX = 10;
    static final int DEPTH_MIN = 2;
    static final int DEPTH_DEFAULT = 4;
    //for ratio slider
    static final int RATIO_MAX = 70;
    static final int RATIO_MIN = 40;
    static final int RATIO_DEFAULT = 50;
    //for angle slider
    static final int ANGLE_MAX = 80;
    static final int ANGLE_MIN = 20;
    static final int ANGLE_DEFAULT = 45;
    
    /*
     * Full constructor for this class
     * 
     * @param    data class that will process the collected datas
     */
    public GraphicUI(FractalData data) {
        setTitle("Cactus Drawing Application");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width / 2 - getWidth()), (screenSize.height - getHeight()) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        // JLabel background=new JLabel(new ImageIcon("guiBack.jpg"));
        // background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	// add(background);
	
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);
        
        //JSlider for depth
        JSlider depthSlide = new JSlider(JSlider.HORIZONTAL, DEPTH_MIN, DEPTH_MAX, DEPTH_DEFAULT);
        depthSlide.setName("Recursion Depth:");
        depthSlide.setMajorTickSpacing(1);
        depthSlide.setPaintTicks(true);
        depthSlide.setPaintLabels(true);
        depthSlide.setVisible(true);
        depthSlide.setBounds(50, 90, 300, 160);
        depthSlide.setValue(4);
        
        JLabel depthLabel = new JLabel(depthSlide.getName());
        depthLabel.setBounds(50, 40, 200, 45);
        depthLabel.setVisible(true);
        panel.add(depthLabel);
        panel.add(depthSlide);
        
        //Slider for ratio
        JSlider ratioSlide = new JSlider(JSlider.HORIZONTAL, RATIO_MIN, RATIO_MAX, RATIO_DEFAULT);
        ratioSlide.setName("Ratio of the big and small cirle:");
        ratioSlide.setMajorTickSpacing(10);
        ratioSlide.setMinorTickSpacing(1);
        ratioSlide.setPaintTicks(true);
        ratioSlide.setPaintLabels(true);
        ratioSlide.setBounds(50, 310, 300, 160);
        ratioSlide.setValue(50);
        
        JLabel ratioLabel = new JLabel(ratioSlide.getName());
        ratioLabel.setBounds(50, 260, 200, 45);
        //ratioLabel.setVisible(true);
        panel.add(ratioLabel);
        panel.add(ratioSlide, BorderLayout.CENTER);
        
        //Slider for angle 
        JSlider angleSlide = new JSlider(JSlider.HORIZONTAL, ANGLE_MIN, ANGLE_MAX, ANGLE_DEFAULT);
        angleSlide.setName("Angle from the parent's orientation:");
        angleSlide.setMajorTickSpacing(10);
        angleSlide.setMinorTickSpacing(1);
        angleSlide.setPaintTicks(true);
        angleSlide.setPaintLabels(true);
        angleSlide.setBounds(50, 520, 300, 160);
        angleSlide.setValue(45);
        
        JLabel angleLabel = new JLabel(angleSlide.getName());
        angleLabel.setBounds(50, 470, 200, 45);
        panel.add(angleLabel);
        panel.add(angleSlide, BorderLayout.PAGE_END);
        
        //Color for a cactus
        //Color preview
        JButton cactus = new JButton("Cactus Color");
        colorCactus = Color.GREEN;
        cactus.setBackground(Color.GREEN);
        cactus.setBounds(480, 100, 200, 40);
        
        panel.add(cactus);
        JButton colorChangerCactus = new JButton("Change");
        colorChangerCactus.setBounds(680, 100, 200, 40);
        colorChangerCactus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JColorChooser clr = new JColorChooser();
                    colorCactus = clr.showDialog(panel, "Choose Color", Color.GREEN);
                    cactus.setBackground(colorCactus);
                }
            });
        panel.add(colorChangerCactus);
        
        //Color for a pear
        //Color preview
        JButton pear = new JButton("Pear Color");
        pear.setBackground(Color.PINK);
        pear.setBounds(480, 360, 200, 40);
        
        panel.add(pear);
        JButton colorChangerPear = new JButton("Change");
        colorPear = Color.PINK;
        colorChangerPear.setBounds(680, 360, 200, 40);
        colorChangerPear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JColorChooser clr = new JColorChooser();
                    colorPear = clr.showDialog(panel, "Choose Color", Color.PINK);
                    pear.setBackground(colorPear);
                }
            });
        panel.add(colorChangerPear);
        
        //Fractal general data
        JButton draw = new JButton("DRAW!");
        draw.setBounds(500, 630, 100, 50);
        draw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //data.setData(3, 70, 50, Color.GREEN, Color.PINK);
                data.setData(depthSlide.getValue(), ratioSlide.getValue(), angleSlide.getValue(), colorCactus, colorPear);
            }
        });
        panel.add(draw);
        
        setVisible(true);
    }
}
