import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

/**
 * Class: ICS 372-01
 * Last modified: 11/05/2015
 * Project Name: Project #2 Iteration #1
 * Description: UI for Fridge/Freezer combo.
 * 
 * @author Ian, Micah, Kuo, David
 */
public class UI implements ComboUnitDisplay {

    private ComboUnit  comboUnit;
    private Fridge     fridge;
    private Freezer    freezer;
    private JFrame     frame;
    private JLabel     lblFridgeLight;
    private JLabel     lblFreezerLight;
    private JLabel     lblFridgeTemp;
    private JLabel     lblFreezerTemp;
    private JLabel     lblFridgeState;
    private JLabel     lblFreezerState;
    private JTextField roomTempField;
    private JTextField fridgeTempField;
    private JTextField freezerTempField;

    /**
     * UI - manages interaction with user
     */
    public UI() {
        //setting up basics
        frame = new JFrame("Refrigerator");
        frame.getContentPane().setLayout(null);
        frame.setPreferredSize(new Dimension(440, 385));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setup();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Sets the fridge light to on/off depending on the input
     * 
     * @param light true for on, false for off
     */
    public void setFridgeLight(boolean light) {
        lblFridgeLight.setText("<html>Fridge light : " + (light
                ? "<font color='green'>on</font></html>" : "<font color='red'>off</font></html>"));
    }

    /**
     * Sets the freezer light to on/off depending on the input
     * 
     * @param light true for on, false for off
     */
    public void setFreezerLight(boolean light) {
        lblFreezerLight.setText("<html>Freezer light : " + (light
                ? "<font color='green'>on</font></html>" : "<font color='red'>off</font></html>"));
    }

    /**
     * Sets the temperature of the fridge to the input
     * 
     * @param temperature the temperature to set
     */
    @Override
    public void setFridgeTemp(int temperature) {
        lblFridgeTemp.setText("Fridge temp : " + temperature);
    }

    /**
     * Sets the temperature of the freezer to the input
     * 
     * @param temperature the temperature to set
     */
    @Override
    public void setFreezerTemp(int temperature) {
        lblFreezerTemp.setText("Freezer temp : " + temperature);
    }

    /**
     * Sets the state of the fridge to cooling/idle depending on the input
     * 
     * @param cooling true for cooling, false for idle
     */
    public void setFridgeState(boolean cooling) {
        lblFridgeState
                .setText("<html>Fridge : " + (cooling ? "<font color='blue'>cooling</font></html>"
                        : "<font color='red'>idle</font></html>"));
    }

    /**
     * Sets the state of the freezer to cooling/idle depending on the input
     * 
     * @param cooling true for cooling, false for idle
     */
    public void setFreezerState(boolean cooling) {
        lblFreezerState
                .setText("<html>Freezer : " + (cooling ? "<font color='blue'>cooling</font></html>"
                        : "<font color='red'>idle</font></html>"));
    }

    /**
     * Sets the ComboUnit of the UI
     * 
     * @param comboUnit the ComboUnit to set to
     */
    @Override
    public void setComboUnit(ComboUnit comboUnit) {
        this.comboUnit = comboUnit;
    }

    /**
     * Sets the Fridge of the UI
     * 
     * @param fridge the Fridge to set to
     */
    @Override
    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }

    /**
     * Sets the Freezer of the UI
     * 
     * @param freezer the Freezer to set to
     */
    @Override
    public void setFreezer(Freezer freezer) {
        this.freezer = freezer;
    }

    /**
     * Turns on the light for the fridge
     */
    @Override
    public void turnFridgeLightOn() {
        setFridgeLight(true);
    }

    /**
     * Turns on the light for the freezer
     */
    @Override
    public void turnFreezerLightOn() {
        setFreezerLight(true);
    }

    /**
     * Turns off the light for the fridge
     */
    @Override
    public void turnFridgeLightOff() {
        setFridgeLight(false);
    }

    /**
     * Turns off the light for the freezer
     */
    public void turnFreezerLightOff() {
        setFreezerLight(false);
    }

    /**
     * Opens the door for the fridge
     */
    @Override
    public void fridgeDoorOpened() {
        fridge.processDoorOpen();
    }

    /**
     * Closes the door for the fridge
     */
    @Override
    public void fridgeDoorClosed() {
        fridge.processDoorClose();
    }

    /**
     * Opens the door for the freezer
     */
    @Override
    public void freezerDoorOpened() {
        freezer.processDoorOpen();
    }

    /**
     * Closes the door for the freezer
     */
    @Override
    public void freezerDoorClosed() {
        freezer.processDoorClose();

    }

    /**
     * Starts the cooling for the fridge
     */
    @Override
    public void startFridgeCooling() {
        setFridgeState(true);
    }

    /**
     * Starts the cooling for the freezer
     */
    @Override
    public void startFreezerCooling() {
        setFreezerState(true);
    }

    /**
     * Idles the cooling for the fridge
     */
    @Override
    public void fridgeNotCooling() {
        setFridgeState(false);
    }

    /**
     * Idles the cooling for the freezer
     */
    @Override
    public void freezerNotCooling() {
        setFreezerState(false);
    }

    /**
     * Used to setup all the components of the UI
     */
    public void setup() {
        //// Setup input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(13, 13, 410, 100);
        inputPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        frame.getContentPane().add(inputPanel);

        // Room temp label
        JLabel lblRoomTemp = new JLabel("Room temp :");
        lblRoomTemp.setBounds(64, 13, 75, 16);
        inputPanel.add(lblRoomTemp);

        // Desired fridge temp label
        JLabel lblDesiredFridgeTemp = new JLabel("Desired fridge temp :");
        lblDesiredFridgeTemp.setBounds(16, 42, 122, 16);
        inputPanel.add(lblDesiredFridgeTemp);

        // Desired freezer temp label
        JLabel lblDesiredFreezerTemp = new JLabel("Desired freezer temp :");
        lblDesiredFreezerTemp.setBounds(8, 71, 130, 16);
        inputPanel.add(lblDesiredFreezerTemp);

        // Room temp text field
        roomTempField = new JTextField();
        roomTempField.setBounds(146, 10, 116, 22);
        inputPanel.add(roomTempField);
        roomTempField.setColumns(10);

        // Desired fridge temp text field
        fridgeTempField = new JTextField();
        fridgeTempField.setBounds(146, 39, 116, 22);
        inputPanel.add(fridgeTempField);
        fridgeTempField.setColumns(10);

        // Desired freezer temp text field
        freezerTempField = new JTextField();
        freezerTempField.setBounds(146, 68, 116, 22);
        inputPanel.add(freezerTempField);
        freezerTempField.setColumns(10);

        // Set room temp button
        JButton btnSetRoomTemp = new JButton("Set room temp");
        btnSetRoomTemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!roomTempField.getText().isEmpty()) {
                    try {
                        int temp = Integer.parseInt(roomTempField.getText());
                        if (comboUnit.getLowestRoomTemp() <= temp
                                && temp <= comboUnit.getHighestRoomTemp()) {
                            comboUnit.setRoomTemp(temp);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Value must be between " + comboUnit.getLowestRoomTemp()
                                            + " and " + comboUnit.getHighestRoomTemp());
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Enter a numerical value.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a temperature value first.");
                }
            }
        });
        btnSetRoomTemp.setBounds(274, 9, 129, 25);
        inputPanel.add(btnSetRoomTemp);

        // Set fridge temp button
        JButton btnSetFridgeTemp = new JButton("Set fridge temp");
        btnSetFridgeTemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fridgeTempField.getText().isEmpty()) {
                    try {
                        int temp = Integer.parseInt(fridgeTempField.getText());
                        if (fridge.getLowestTemp() <= temp && temp <= fridge.getHighestTemp()) {
                            fridge.setDesiredTemp(temp);
                        } else {
                            JOptionPane.showMessageDialog(null, "Value must be between "
                                    + fridge.getLowestTemp() + " and " + fridge.getHighestTemp());
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Enter a numerical value.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a temperature value first.");
                }
            }
        });
        btnSetFridgeTemp.setBounds(274, 38, 129, 25);
        inputPanel.add(btnSetFridgeTemp);

        // Set freezer temp button
        JButton btnSetFreezerTemp = new JButton("Set freezer temp");
        btnSetFreezerTemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!freezerTempField.getText().isEmpty()) {
                    try {
                        int temp = Integer.parseInt(freezerTempField.getText());
                        if (freezer.getLowestTemp() <= temp && temp <= freezer.getHighestTemp()) {
                            freezer.setDesiredTemp(temp);
                        } else {
                            JOptionPane.showMessageDialog(null, "Value must be between "
                                    + freezer.getLowestTemp() + " and " + freezer.getHighestTemp());
                        }

                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Enter a numerical value.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a temperature value first.");
                }
            }
        });
        btnSetFreezerTemp.setBounds(274, 67, 129, 25);
        inputPanel.add(btnSetFreezerTemp);

        //// Setup door button panel
        JPanel doorButtonPanel = new JPanel();
        doorButtonPanel.setLayout(null);
        doorButtonPanel.setBounds(13, 126, 410, 105);
        doorButtonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        frame.getContentPane().add(doorButtonPanel);

        // Open fridge door button
        JButton btnOpenFridgeDoor = new JButton("Open fridge door");
        btnOpenFridgeDoor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setFridgeLight(true);
                fridgeDoorOpened();
            }
        });
        btnOpenFridgeDoor.setBounds(12, 13, 190, 35);
        doorButtonPanel.add(btnOpenFridgeDoor);

        // Close fridge door button
        JButton btnCloseFridgeDoor = new JButton("Close fridge door");
        btnCloseFridgeDoor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFridgeLight(false);
                fridgeDoorClosed();
            }
        });
        btnCloseFridgeDoor.setBounds(208, 13, 190, 35);
        doorButtonPanel.add(btnCloseFridgeDoor);

        // Open freezer door button
        JButton btnOpenFreezerDoor = new JButton("Open freezer door");
        btnOpenFreezerDoor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFreezerLight(true);
                freezerDoorOpened();
            }
        });
        btnOpenFreezerDoor.setBounds(12, 56, 190, 35);
        doorButtonPanel.add(btnOpenFreezerDoor);

        // Close freezer door button
        JButton btnCloseFreezerDoor = new JButton("Close freezer door");
        btnCloseFreezerDoor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                freezerDoorClosed();
                setFreezerLight(false);
            }
        });
        btnCloseFreezerDoor.setBounds(208, 56, 190, 35);
        doorButtonPanel.add(btnCloseFreezerDoor);

        //// Setup status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(null);
        statusPanel.setBounds(13, 239, 410, 100);
        statusPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        frame.getContentPane().add(statusPanel);

        // Status label
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(12, 6, 43, 16);
        statusPanel.add(lblStatus);

        // Fridge light label
        lblFridgeLight = new JLabel();
        lblFridgeLight.setBounds(75, 20, 109, 16);
        setFridgeLight(false);
        statusPanel.add(lblFridgeLight);

        // Freezer light label
        lblFreezerLight = new JLabel();
        lblFreezerLight.setBounds(196, 20, 128, 16);
        setFreezerLight(false);
        statusPanel.add(lblFreezerLight);

        // Fridge temp label
        lblFridgeTemp = new JLabel();
        lblFridgeTemp.setBounds(75, 45, 109, 16);
        statusPanel.add(lblFridgeTemp);

        // Freezer temp label
        lblFreezerTemp = new JLabel();
        lblFreezerTemp.setBounds(196, 45, 128, 16);
        statusPanel.add(lblFreezerTemp);

        // Fridge state label
        lblFridgeState = new JLabel();
        lblFridgeState.setBounds(75, 70, 107, 16);
        setFridgeState(false);
        statusPanel.add(lblFridgeState);

        // Freezer state label
        lblFreezerState = new JLabel();
        lblFreezerState.setBounds(196, 70, 130, 16);
        setFreezerState(false);
        statusPanel.add(lblFreezerState);
    }
}
