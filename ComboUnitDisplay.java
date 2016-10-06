/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: This interface specifies the methods the ComboUnit must implement.
 * 
 * @author Ian, Micah, Kuo, David
 */
public interface ComboUnitDisplay {

    public void setComboUnit(ComboUnit comboUnit);

    public void setFridge(Fridge fridge);

    public void setFreezer(Freezer freezer);

    public void turnFridgeLightOn();

    public void turnFreezerLightOn();

    public void turnFridgeLightOff();

    public void turnFreezerLightOff();

    public void fridgeDoorClosed();

    public void freezerDoorClosed();

    public void fridgeDoorOpened();

    public void freezerDoorOpened();

    public void setFridgeTemp(int temperature);

    public void setFreezerTemp(int temperature);

    public void startFridgeCooling();

    public void startFreezerCooling();

    public void fridgeNotCooling();

    public void freezerNotCooling();

}
