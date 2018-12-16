import java.awt.Color;
import java.awt.Graphics;

/**
 * FractalData class recieves raw data from the GUI and processes into shape data
 *
 * @author      Oyunomin Munkhkhurel
 * @version     3/3/2018
 */
public class FractalData implements Subject {
    private ArrayList<Observer> observers;
    private ArrayList<Shape> shapes;
    private int depth;
    private double ratio, angle;
    private Color colorCactus, colorPear;
    private static final int DEFAULT_DIAMETER = 200;
    
    /*
     * Constructor of this class
     */
    public FractalData() {
        observers = new ArrayList<Observer>();
        shapes = new ArrayList<Shape>();
    }
    
    /*
     * Sets necessary data
     * 
     * @param   int     depth of the shape to be drawn
     * @param   int     ratio of the parent and the child
     * @param   int     angle from the parent's orientation
     * @param   Color   color of the cactus, main body
     * @param   Color   color of the pear, flower
     */
    public void setData(int depth, int ratio, int angle, Color colorCactus, Color colorPear) {
        //assuming GUI handles the validity of the data
        this.depth = depth;
        this.ratio = Double.valueOf(ratio) / 100;
        this.angle = angle * Math.PI / 180;
        this.colorCactus = colorCactus;
        this.colorPear = colorPear; 
        notifyObservers();
    }
    
    /*
     * Calculates the parent and it's children's coordinate and the radius
     * 
     * @param   int     x coordinate of the shape
     * @param   int     y coordinate of the shape
     * @param   int     width of the shape
     * @param   int     height of the shape
     * @param   double  orientation of the shape
     */
    private Shape calculate(int x, int y, int width, int height, double orientation) {
        Shape circle = new Shape(x, y, width, height, orientation, colorCactus);
        
        if (width <= DEFAULT_DIAMETER * Math.pow(ratio, depth - 2) - 1) {
            circle.setColor(colorPear);
        }
            
            if (width / 2 < 1 || width <= DEFAULT_DIAMETER  * Math.pow(ratio, depth - 1) - 1) {
                return null;
            } else {
            shapes.add(circle);
            
            double radius = circle.getWidth() / 2;
            
            double centerX = circle.getX() + radius;
            double centerY = circle.getY() + radius;
            double rightAngle = circle.getOrientation() - angle;
            double leftAngle = circle.getOrientation() + angle;
            
            double rightTangentX = centerX + Math.sin(rightAngle) * radius;
            double rightTangentY = centerY - Math.cos(rightAngle) * radius;
            
            double leftTangentX = centerX - Math.sin(leftAngle) * radius;
            double leftTangentY = centerY + Math.cos(leftAngle) * radius;
            
            int rightChildX = (int)Math.round(rightTangentX + Math.sin(rightAngle) * radius * ratio - radius * ratio);
            int rightChildY = (int)Math.round(rightTangentY - Math.cos(rightAngle) * radius * ratio - radius * ratio);
            
            int leftChildX = (int)Math.round(leftTangentX - Math.sin(leftAngle) * radius * ratio - radius * ratio);
            int leftChildY = (int)Math.round(leftTangentY + Math.cos(leftAngle) * radius * ratio - radius * ratio);
            
            calculate(rightChildX, rightChildY, (int)Math.round(width * ratio), (int)Math.round(height * ratio), leftAngle);
            calculate(leftChildX, leftChildY, (int)Math.round(width * ratio), (int)Math.round(height * ratio), rightAngle);
        }
        return circle;
    }
    
    /*
     * Retrieves the arraylist full of shape information and gets the initial x, y coordinate
     * 
     * @param int the biggest shape's x coordinate
     * @param int the biggest shape's y coordinate
     * @return array list calculated based on the parameters
     */
    public ArrayList<Shape> getData(int x, int y) {
        calculate(x, y, DEFAULT_DIAMETER, DEFAULT_DIAMETER, Math.PI / 2);
        return shapes;
    }
    
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observers.indexOf(observer));
    }
    
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
            shapes.clear();
        }
    }
    
}
