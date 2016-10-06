import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: This class provides a ComboUnit which contains a Fridge and a Freezer.
 * 
 * @author Ian, Micah, Kuo, David
 */
public class ComboUnit {

    private int              lowestRoomTemp;
    private int              highestRoomTemp;
    private static ComboUnit instance;
    private Fridge           fridge;
    private Freezer          freezer;
    private UI               display;
    private int              roomTemp               = 70;
    private int              fridgeCoolingCount     = 0;
    private int              freezerCoolingCount    = 0;
    private int              fridgeDoorOpenCount    = 0;
    private int              freezerDoorOpenCount   = 0;
    private int              fridgeDoorClosedCount  = 0;
    private int              freezerDoorClosedCount = 0;

    /**
     * Creates a Refrigerator according to the given configuration file
     */
    private ComboUnit() {

        fridge = Fridge.instance();
        freezer = Freezer.instance();
        display = new UI();
        display.setComboUnit(this);
        display.setFridgeTemp(roomTemp);
        display.setFreezerTemp(roomTemp);
        display.setFridge(fridge);
        display.setFreezer(freezer);
        display.turnFridgeLightOff();
        display.turnFreezerLightOff();
        fridge.setDesiredTemp(roomTemp);
        freezer.setDesiredTemp(roomTemp);

        String line;
        int ctr = 0;
        BufferedReader br = null;
        try {
            File file = new File("src/config.txt");
            FileInputStream fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                ctr++;
                switch (ctr) {
                case 1:
                    fridge.setLowestTemp(Integer.parseInt(line));
                    break;
                case 2:
                    fridge.setHighestTemp(Integer.parseInt(line));
                    break;
                case 3:
                    freezer.setLowestTemp(Integer.parseInt(line));
                    break;
                case 4:
                    freezer.setHighestTemp(Integer.parseInt(line));
                    break;
                case 5:
                    setLowestRoomTemp(Integer.parseInt(line));
                    break;
                case 6:
                    setHighestRoomTemp(Integer.parseInt(line));
                    break;
                case 7:
                    fridge.setMinutesTempRiseOneDegreeNoCoolingDoorClosed(Integer.parseInt(line));
                    break;
                case 8:
                    fridge.setMinutesTempRiseOneDegreeNoCoolingDoorOpen(Integer.parseInt(line));
                    break;
                case 9:
                    freezer.setMinutesTempRiseOneDegreeNoCoolingDoorClosed(Integer.parseInt(line));
                    break;
                case 10:
                    freezer.setMinutesTempRiseOneDegreeNoCoolingDoorOpen(Integer.parseInt(line));
                    break;
                case 11:
                    fridge.setTempDifferenceTriggerCooling(Integer.parseInt(line));
                    break;
                case 12:
                    freezer.setTempDifferenceTriggerCooling(Integer.parseInt(line));
                    break;
                case 13:
                    fridge.setMinutesToCoolOneDegree(Integer.parseInt(line));
                    break;
                case 14:
                    freezer.setMinutesToCoolOneDegree(Integer.parseInt(line));
                    break;
                } // end switch
            } // end while    
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    /**
     * @return a Singleton instance of the ComboUnit
     */
    public static ComboUnit instance() {
        if (instance == null) {
            return instance = new ComboUnit();
        }
        return instance;
    }

    /**
     * Sets the lowestRoomTemp
     * 
     * @param temp the temperature to set
     */
    public void setLowestRoomTemp(int temp) {
        lowestRoomTemp = temp;
    }

    /**
     * @return the lowestRoomTemp
     */
    public int getLowestRoomTemp() {
        return lowestRoomTemp;
    }

    /**
     * Sets the highestRoomTemp
     * 
     * @param temp the temperature to set
     */
    public void setHighestRoomTemp(int temp) {
        highestRoomTemp = temp;
    }

    /**
     * @return the highestRoomTemp
     */
    public int getHighestRoomTemp() {
        return highestRoomTemp;
    }

    /**
     * Sets the roomTemp
     * 
     * @param temp the temperature to set
     */
    public void setRoomTemp(int temp) {
        roomTemp = temp;
    }

    /**
     * @return the roomTemp
     */
    public int getRoomTemp() {
        return roomTemp;
    }

    /**
     * @return the Fridge of the ComboUnit
     */
    public Fridge getFridge() {
        return fridge;
    }

    /**
     * @return the Freezer of the ComboUnit
     */
    public Freezer getFreezer() {
        return freezer;
    }

    /**
     * Changes the state of the fridge depending on how the temperature changes over time
     */
    public void clockTickedFridge() {

        //If the temperature is less than or equal to the minimum temp
        //Set state as idle
        if (fridge.getCurrentTemp() <= fridge.getDesiredTemp()) {
            fridge.setCoolingState(Fridge.coolingStates.IDLE);
            display.setFridgeTemp(fridge.getCurrentTemp());
            display.fridgeNotCooling();
        }

        //If the temperature is greater than the minimum set temp
        //Set state to cooling
        else {
            fridgeCoolingCount++;
            if (fridgeCoolingCount >= fridge.getMinutesToCoolOneDegree()) { //Cools 1 degree every 2 seconds
                fridge.setCoolingState(Fridge.coolingStates.COOLING_STATE);
                fridge.setCurrentTemp(fridge.getCurrentTemp() - 1); //for Thread.sleep(1000)
                fridgeCoolingCount = 0;
            }
            display.setFridgeTemp(fridge.getCurrentTemp());
            display.startFridgeCooling();
        }

        //If the door is currently open, increase the temperature inside
        //the fridge by doorOpenCount amount every second
        if (fridge.getCurrentDoorState() == Fridge.doorStates.DOOR_OPEN_STATE) {
            fridgeDoorOpenCount++;
            if (fridge.getCurrentTemp() < roomTemp) {
                if (fridgeDoorOpenCount >= fridge.getMinutesTempRiseOneDegreeNoCoolingDoorOpen()) { //Warms 1 degree every x seconds
                    fridge.setCurrentTemp(fridge.getCurrentTemp() + 1);
                    fridgeDoorOpenCount = 0;
                }
            } else if (fridge.getCurrentTemp() > roomTemp) { //roomTemp colder than fridge
                if (fridgeDoorOpenCount >= fridge.getMinutesTempRiseOneDegreeNoCoolingDoorOpen()) { //Cools 1 degree every x seconds
                    fridge.setCurrentTemp(fridge.getCurrentTemp() - 1);
                    fridgeDoorOpenCount = 0;
                }
            }
            display.setFridgeTemp(fridge.getCurrentTemp());
        }

        //If the door is currently closed, increase the temperature inside
        //the fridge by doorClosedCount amount every second
        else {
            if (fridge.getCurrentCoolingState() == Fridge.coolingStates.IDLE) { //temp only rises if in Idle cooling state

                fridgeDoorClosedCount++;
                if (fridge.getCurrentTemp() < roomTemp) {
                    if (fridgeDoorClosedCount >= fridge
                            .getMinutesTempRiseOneDegreeNoCoolingDoorClosed()) { //Warms 1 degree every x seconds
                        fridge.setCurrentTemp(fridge.getCurrentTemp() + 1); //Placeholder
                        fridgeDoorClosedCount = 0;
                    }
                } else if (freezer.getCurrentTemp() > roomTemp) { //roomTemp colder than fridge
                    if (fridgeDoorClosedCount >= fridge
                            .getMinutesTempRiseOneDegreeNoCoolingDoorClosed()) { //Cools 1 degree every x seconds
                        fridge.setCurrentTemp(freezer.getCurrentTemp() - 1);
                        fridgeDoorClosedCount = 0;
                    }
                }
                display.setFridgeTemp(fridge.getCurrentTemp());
            }
        }
    }

    /**
     * Changes the state of the freezer depending on how the temperature changes over time
     */
    public void clockTickedFreezer() {
        //If the temperature is less than or equal to the minimum temp
        //Set state as idle
        if (freezer.getCurrentTemp() <= freezer.getDesiredTemp()) {
            freezer.setCoolingState(Freezer.coolingStates.IDLE);
            display.setFreezerTemp(freezer.getCurrentTemp());
            display.freezerNotCooling();
        }

        //If the temperature is greater than the minimum set temp
        //Set state to cooling
        else {
            freezer.setCoolingState(Freezer.coolingStates.COOLING_STATE);
            freezerCoolingCount++;
            if (freezerCoolingCount >= freezer.getMinutesToCoolOneDegree()) {
                freezer.setCurrentTemp(freezer.getCurrentTemp() - 1); //for Thread.sleep(1000)
                freezerCoolingCount = 0;
            }
            display.setFreezerTemp(freezer.getCurrentTemp());
            display.startFreezerCooling();
        }

        //If the door is currently open, increase the temperature inside
        //the fridge by doorOpenCount amount every second
        if (freezer.getCurrentDoorState() == Freezer.doorStates.DOOR_OPEN_STATE) {
            freezerDoorOpenCount++;
            if (freezer.getCurrentTemp() < roomTemp) {
                if (freezerDoorOpenCount >= freezer
                        .getMinutesTempRiseOneDegreeNoCoolingDoorOpen()) { //Warms 1 degree every x seconds
                    freezer.setCurrentTemp(freezer.getCurrentTemp() + 1);
                    freezerDoorOpenCount = 0;
                }
            } else if (freezer.getCurrentTemp() > roomTemp) { //roomTemp colder than freezer
                if (freezerDoorOpenCount >= freezer
                        .getMinutesTempRiseOneDegreeNoCoolingDoorOpen()) { //Cools 1 degree every x seconds
                    freezer.setCurrentTemp(freezer.getCurrentTemp() - 1);
                    freezerDoorOpenCount = 0;
                }
            }
            display.setFreezerTemp(freezer.getCurrentTemp());
        }

        //If the door is currently closed, increase the temperature inside
        //the fridge by doorClosedCount amount every second
        else {
            if (freezer.getCurrentCoolingState() == Freezer.coolingStates.IDLE) { //temp only rises in Idle cooling state.
                freezerDoorClosedCount++;
                if (freezer.getCurrentTemp() < roomTemp) {
                    if (freezerDoorClosedCount >= freezer
                            .getMinutesTempRiseOneDegreeNoCoolingDoorClosed()) { //Warms 1 degree every x seconds
                        freezer.setCurrentTemp(freezer.getCurrentTemp() + 1); //Placeholder
                        freezerDoorClosedCount = 0;
                    }
                } else if (freezer.getCurrentTemp() > roomTemp) { //roomTemp colder than freezer
                    if (freezerDoorClosedCount >= freezer
                            .getMinutesTempRiseOneDegreeNoCoolingDoorClosed()) { //Cools 1 degree every x seconds
                        freezer.setCurrentTemp(freezer.getCurrentTemp() - 1);
                        freezerDoorClosedCount = 0;
                    }
                }
                display.setFreezerTemp(freezer.getCurrentTemp());
            }
        }
    }
}
