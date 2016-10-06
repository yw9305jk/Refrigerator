/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: This class provides a Freezer object
 * 
 * @author Ian, Micah, Kuo, David
 */
public class Freezer {

    /**
     * States of the freezer door
     */
    public enum doorStates {
        DOOR_CLOSED_STATE, DOOR_OPEN_STATE
    };

    /**
     * States of the cooling system
     */
    public enum coolingStates {
        COOLING_STATE, IDLE
    };

    private doorStates     currentDoorState;
    private coolingStates  currentCoolingState;
    private int            currentTemp;
    private int            lowestTemp;
    private int            highestTemp;
    private int            minutesTempRiseOneDegreeNoCoolingDoorClosed;
    private int            minutesTempRiseOneDegreeNoCoolingDoorOpen;
    private int            minutesToCoolOneDegree;
    private int            tempDifferenceTriggerCooling;
    private int            desiredTemp;
    private static Freezer instance;

    /**
     * Instantiates a Freezer
     */
    private Freezer() {
        currentDoorState = doorStates.DOOR_CLOSED_STATE;
        currentCoolingState = coolingStates.IDLE;
        currentTemp = 70; //Placeholder values
    }

    /**
     * Creates or returns the Freezer singleton
     * 
     * @return a singleton instance of a Freezer
     */
    public static Freezer instance() {
        if (instance == null) {
            return instance = new Freezer();
        }
        return instance;
    }

    /**
     * Sets the currentTemp
     * 
     * @param temp the temperature to set
     */
    public void setCurrentTemp(int temp) {
        currentTemp = temp;
    }

    /**
     * @return the currentTemp
     */
    public int getCurrentTemp() {
        return currentTemp;
    }

    /**
     * Sets the desiredTemp
     * 
     * @param temp the temperature to set
     */
    public void setDesiredTemp(int temp) {
        desiredTemp = temp;
    }

    /**
     * @return the desiredTemp
     */
    public int getDesiredTemp() {
        return desiredTemp;
    }

    /**
     * Sets the lowestTemp
     * 
     * @param temp the temperature to set
     */
    public void setLowestTemp(int temp) {
        lowestTemp = temp;
    }

    /**
     * @return the lowestTemp
     */
    public int getLowestTemp() {
        return lowestTemp;
    }

    /**
     * Sets the highestTemp
     * 
     * @param temp the temperature to set
     */
    public void setHighestTemp(int temp) {
        highestTemp = temp;
    }

    /**
     * @return the highestTemp
     */
    public int getHighestTemp() {
        return highestTemp;
    }

    /**
     * Sets the number of minutes for the temp to rise one degree when the cooling system
     * is off and the door closed
     * 
     * @param min the minutes to set
     */
    public void setMinutesTempRiseOneDegreeNoCoolingDoorClosed(int min) {
        minutesTempRiseOneDegreeNoCoolingDoorClosed = min;
    }

    /**
     * @return the number of minutes for the temp to rise one degree when the cooling
     *         system is off and the door closed
     */
    public int getMinutesTempRiseOneDegreeNoCoolingDoorClosed() {
        return minutesTempRiseOneDegreeNoCoolingDoorClosed;
    }

    /**
     * Sets the number of minutes for the temp to rise one degree when the cooling system
     * is off and the door open
     * 
     * @param min the minutes to set
     */
    public void setMinutesTempRiseOneDegreeNoCoolingDoorOpen(int min) {
        minutesTempRiseOneDegreeNoCoolingDoorOpen = min;
    }

    /**
     * @return the number of minutes for the temp to rise one degree when the cooling
     *         system is off and the door open
     */
    public int getMinutesTempRiseOneDegreeNoCoolingDoorOpen() {
        return minutesTempRiseOneDegreeNoCoolingDoorOpen;
    }

    /**
     * Sets the temperature difference between the current temp and desired temp such that
     * cooling is triggered
     * 
     * @param temp the temperature to set
     */
    public void setTempDifferenceTriggerCooling(int temp) {
        tempDifferenceTriggerCooling = temp;
    }

    /**
     * Sets number of minutes needed to cool the freezer one degree
     * 
     * @param min the minute to set
     */
    public void setMinutesToCoolOneDegree(int min) {
        minutesToCoolOneDegree = min;
    }

    /**
     * @return the number of minutes needed to cool freezer one degree
     */
    public int getMinutesToCoolOneDegree() {
        return minutesToCoolOneDegree;
    }

    /**
     * Sets the current door state to the open state
     */
    public void processDoorOpen() {
        currentDoorState = doorStates.DOOR_OPEN_STATE;
    }

    /**
     * Sets the current door state to the closed state
     */
    public void processDoorClose() {
        currentDoorState = doorStates.DOOR_CLOSED_STATE;
    }

    /**
     * Sets the current door state
     * 
     * @param state the door state to set
     */
    public void setDoorState(doorStates state) {
        currentDoorState = state;
    }

    /**
     * @return the currentDoorState
     */
    public doorStates getCurrentDoorState() {
        return currentDoorState;
    }

    /**
     * Sets the cooling state
     * 
     * @param state the cooling state to set
     */
    public void setCoolingState(coolingStates state) {
        currentCoolingState = state;
    }

    /**
     * @return the currentCoolingState
     */
    public coolingStates getCurrentCoolingState() {
        return currentCoolingState;
    }

}
