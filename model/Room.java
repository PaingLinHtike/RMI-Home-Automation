package model;

import java.io.Serializable;

public class Room implements Serializable {

    private String name;
    private boolean lightOn;
    private int brightness;
    private int temperature;
    private boolean locked;

    private boolean fireDetected; // kitchen only
    private boolean curtainOpen;  // living room only

    public Room(String name) {
        this.name = name;
        this.lightOn = false;
        this.brightness = 50;
        this.temperature = 24;
        this.locked = false;
        this.fireDetected = false;
        this.curtainOpen = true;
    }

    // ------------------- Common -------------------
    public String getName() { return name; }

    public boolean isLightOn() { return lightOn; }
    public void setLightOn(boolean value) { lightOn = value; }

    public int getBrightness() { return brightness; }
    public void setBrightness(int value) { brightness = value; }

    public int getTemperature() { return temperature; }
    public void setTemperature(int value) { temperature = value; }

    public boolean isLocked() { return locked; }
    public void setLocked(boolean value) { locked = value; }

    // ------------------- Kitchen -------------------
    public boolean isFireDetected() { return fireDetected; }
    public void setFireDetected(boolean value) { fireDetected = value; }

    // ------------------- Living Room -------------------
    public boolean isCurtainOpen() { return curtainOpen; }
    public void setCurtainOpen(boolean value) { curtainOpen = value; }
}