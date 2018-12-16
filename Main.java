import java.awt.Color;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        FractalData data = new FractalData();
        GraphicUI ui = new GraphicUI(data);
        FractalDraw draw = new FractalDraw(data);
    }
}
