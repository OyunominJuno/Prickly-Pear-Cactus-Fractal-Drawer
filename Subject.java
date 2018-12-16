import java.awt.Color;

/**
 * Subject class for observers 
 *
 * @author   Oyunomin Munkhkhurel
 * @version  3/3/2018
 */
public interface Subject {
    /*
     * Regiters observer to this subject
     *
     * @param   Observer    registering observer who also impelement Observer interface
     */
    public void registerObserver(Observer observer);
    
    /*
     * Removes observer to this subject
     *
     * @param   Observer    removing observer who also impelement Observer interface
     */
    public void removeObserver(Observer observer);
    
    /*
     * Notifies the news that new data came to the observers
     */
    public void notifyObservers();
    
    /*
     * Sets the data 
     * 
     * @param   int     depth of the resursion
     * @param   int     ratio of the parent and a child
     * @param   int     angle from the orientation
     * @param   Color   colorCactus color of the cactus main body
     * @param   Color   colorPear color of the cactus's pear
     */
    public void setData(int depth, int ratio, int angle, Color colorCactus, Color colorPear);
    
    public ArrayList<Shape> getData(int x, int y);
}
