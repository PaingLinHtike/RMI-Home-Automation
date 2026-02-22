package server;

import common.DeviceService;
import model.Room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class DeviceServiceImpl extends UnicastRemoteObject implements DeviceService {

    private Map<String, Room> rooms = new HashMap<>();
    private int totalEnergy = 0;

    // Schedule map: room -> [fromTime, toTime, on/off]
    private Map<String, List<String>> lightSchedule = new HashMap<>();

    protected DeviceServiceImpl() throws RemoteException {
        super();

        // Create Rooms
        rooms.put("Luke's Room", new Room("Luke's Room"));
        rooms.put("Alice's Room", new Room("Alice's Room"));
        rooms.put("Bob's Room", new Room("Bob's Room"));
        rooms.put("LivingRoom", new Room("LivingRoom"));
        rooms.put("Kitchen", new Room("Kitchen"));
        rooms.put("Garage", new Room("Garage"));

        startKitchenFireSimulation();
    }

    // ------------------- ENERGY -------------------
    private synchronized void addEnergyUsage(int amount) { totalEnergy += amount; }
    public int getTotalEnergy() throws RemoteException { return totalEnergy; }

    // ------------------- LIGHT -------------------
    public String turnLightOn(String room) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setLightOn(true);
        addEnergyUsage(5);
        return room + " light turned ON";
    }

    public String turnLightOff(String room) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setLightOn(false);
        return room + " light turned OFF";
    }

    public String setBrightness(String room, int value) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setBrightness(value);
        addEnergyUsage(value / 10);
        return room + " brightness set to " + value + "%";
    }

    public String setLightMode(String room, String mode) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";

        if (mode.equalsIgnoreCase("SLEEP")) {
            r.setBrightness(20);
            r.setTemperature(22);
            addEnergyUsage(5);
            return room + " Sleep Mode activated";
        }
        return "Mode not supported for this room";
    }

    // ------------------- TEMPERATURE -------------------
    public String setTemperature(String room, int value) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setTemperature(value);
        addEnergyUsage(3);
        return room + " temperature set to " + value + "°C";
    }

    // ------------------- DOOR -------------------
    public String lockDoor(String room) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setLocked(true);
        return room + " door locked";
    }

    public String unlockDoor(String room) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";
        r.setLocked(false);
        return room + " door unlocked";
    }

    // ------------------- LIVING ROOM -------------------
    public String activateLivingMode(String mode) throws RemoteException {
        Room r = rooms.get("LivingRoom");
        if (r == null) return "Living Room not found";

        switch (mode.toUpperCase()) {
            case "MOVE":
                r.setBrightness(75);
                r.setTemperature(24);
                break;
            case "PARTY":
                r.setBrightness(100);
                r.setTemperature(20);
                break;
            case "RELAX":
                r.setBrightness(40);
                r.setTemperature(23);
                break;
        }

        addEnergyUsage(10);
        return "Living Room " + mode + " mode activated";
    }

    public String openCurtain() throws RemoteException {
        Room r = rooms.get("LivingRoom");
        if (r == null) return "Living Room not found";
        r.setCurtainOpen(true);
        return "Living Room curtain opened";
    }

    public String closeCurtain() throws RemoteException {
        Room r = rooms.get("LivingRoom");
        if (r == null) return "Living Room not found";
        r.setCurtainOpen(false);
        return "Living Room curtain closed";
    }

    // ------------------- KITCHEN FIRE -------------------
    public boolean isFireDetected() throws RemoteException {
        return rooms.get("Kitchen").isFireDetected();
    }

    private void startKitchenFireSimulation() {
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(10000);
                    if (random.nextInt(10) == 5) {
                        rooms.get("Kitchen").setFireDetected(true);
                        System.out.println("🔥 FIRE DETECTED IN KITCHEN!");
                    }
                } catch (Exception e) { e.printStackTrace(); }
            }
        }).start();
    }

    // ------------------- ROOM STATUS -------------------
    public String getRoomStatus(String room) throws RemoteException {
        Room r = rooms.get(room);
        if (r == null) return "Room not found";

        return "Room: " + room +
                "\nLight: " + (r.isLightOn() ? "ON" : "OFF") +
                "\nBrightness: " + r.getBrightness() + "%" +
                "\nTemperature: " + r.getTemperature() + "°C" +
                "\nDoor: " + (r.isLocked() ? "Locked" : "Unlocked") +
                "\nCurtain: " + (r.isCurtainOpen() ? "Open" : "Closed") +
                "\nFire: " + (r.isFireDetected() ? "🔥 YES" : "Safe") +
                "\nTotal Energy Used: " + totalEnergy + " units";
    }

    // ------------------- LIGHT SCHEDULING -------------------
    public String scheduleLight(String room, String fromTime, String toTime, boolean turnOn) throws RemoteException {
        lightSchedule.put(room, Arrays.asList(fromTime, toTime, turnOn ? "ON" : "OFF"));
        return "Light schedule set for " + room + ": " + fromTime + " -> " + toTime + " (" + (turnOn ? "ON" : "OFF") + ")";
    }
}