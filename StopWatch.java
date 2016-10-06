/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: The StopWatch class instantiates the ComboUnit instance, and starts the
 * thread to call the clockTicked methods from the ComboUnit.
 *
 * @author Ian, Micah, Kuo, David
 */
public class StopWatch implements Runnable {
    private static ComboUnit comboUnit;

    /**
     * Creates the ComboUnit instance, and starts the thread
     */
    public StopWatch() {
        comboUnit = ComboUnit.instance();
        new Thread(this).start();
    }

    /**
     * Keep ticking every second and call the fridge and freezer's clockTicked method
     */
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                comboUnit.clockTickedFridge();
                comboUnit.clockTickedFreezer();
            }
        } catch (InterruptedException ie) {
        }
    }
}
