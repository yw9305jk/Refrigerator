import java.awt.EventQueue;

/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: This class starts the ComboUnit refrigerator.
 * 
 * @author Ian, Micah, Kuo, David
 */
public class Run {
    /**
     * Starts the ComboUnit by starting the StopWatch. The ComboUnit itself is a
     * singleton, which will get instantiated by the StopWatch class.
     * 
     * @param s not used
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    @SuppressWarnings("unused")
                    StopWatch stopWatch = new StopWatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
