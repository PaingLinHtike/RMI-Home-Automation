package model;

import java.io.Serializable;

public class Light implements Serializable {

    private boolean on = false;
    private int brightness = 50; // 0–100
    private String mode = "NORMAL"; // NORMAL, SLEEP, MOVE, PARTY

    public void turnOn() { on = true; }
    public void turnOff() { on = false; }

    public void setBrightness(int value) { brightness = value; }

    public void setMode(String mode) { this.mode = mode; }

    public boolean isOn() { return on; }
    public int getBrightness() { return brightness; }
    public String getMode() { return mode; }
}