import java.awt.Graphics;
import java.awt.Color;

/**
 * Shape class that holds the data to be drawn and draw
 *
 * @author   Oyunomin Munkhkhurel
 * @version  3/3/2018
 */
public class Shape {
    private int x, y, width, height;
    private double orientation;
    private Color color;
    private static final int MINIMUM = 0;
    /*
     * Full constructor for a shape
     * 
     * @param   int         x coordinate of the shape
     * @param   int         y coordinate of the shape
     * @param   int         width of the shape
     * @param   int         height of the shape
     * @param   int         orientation of the shape in degrees
     * @param   Color       color of the shape 
     */
    public Shape(int x, int y, int width, int height, double orientation, Color color) {
        ensure(x);
        ensure(y);
        ensure(width);
        ensure(height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.orientation = orientation;
        this.color = color;
    }   
        
    /*
     * Draws the shape
     * 
     * @param   graphic object that draws
     * @throws  IllegalArgumentException
     */
    public void draw(Graphics g) {
        if (g == null) {
            throw new IllegalArgumentException("Graphics object is null");
        }
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
    
    //accessors
    /*
     * Retrieves the x coordinate of the shape
     * 
     * @return  x coordinate of the shape
     */
    public int getX() {
        return x;
    }
    
    /*
    /*
     * Retrieves the y coordinate of the shape
     * 
     * @return  y coordinate of the shape
     */
    public int getY() {
        return y;
    }
    
    /*
     * Retrieves the width of the shape
     * 
     * @return  the width of the shape
     */
    public int getWidth() {
        return width;
    }
    
    /*
     * Retrieves the height of the shape
     * 
     * @return  the height of the shape
     */
    public int getHeight() {
        return height;
    }
    
    /*
     * Retrieves the orientation radian of the shape
     * 
     * @return  orientation radian of the shape
     */
    public double getOrientation() {
        return orientation;
    }
    
    /*
     * Retrieves the color of this shape
     * 
     * @return  color of the shape
     */
    public Color getColor() {
        return color;
    }
    
    /*
     * Changes the color of this shape
     * 
     * @param   Color  color of the shape
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /*
     * Makes sure the data is non-zero
     * 
     * @param   data to be cheked
     * @throw   IllegalArgumentException when data is zero
     */
    private void ensure(int data) {
        if (data < MINIMUM) {
            throw new IllegalArgumentException("invalid data : " + data);
        }
    }
    
    /*
     * String state of this shape
     * 
     * @return   information about this shape
     */
    public String toString() {
        return x + ", " + y + ", " + width + ", " + height + ", " +orientation + ", " + color + "\n";
    }
}
