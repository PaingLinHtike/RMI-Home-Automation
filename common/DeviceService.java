package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DeviceService extends Remote {

    // ------------------- STATUS -------------------
    String getRoomStatus(String room) throws RemoteException;

    // ------------------- LIGHT -------------------
    String turnLightOn(String room) throws RemoteException;
    String turnLightOff(String room) throws RemoteException;
    String setBrightness(String room, int value) throws RemoteException;
    String setLightMode(String room, String mode) throws RemoteException;

    // ------------------- TEMPERATURE -------------------
    String setTemperature(String room, int value) throws RemoteException;

    // ------------------- DOOR -------------------
    String lockDoor(String room) throws RemoteException;
    String unlockDoor(String room) throws RemoteException;

    // ------------------- LIVING ROOM -------------------
    String activateLivingMode(String mode) throws RemoteException;
    String openCurtain() throws RemoteException;
    String closeCurtain() throws RemoteException;

    // ------------------- ENERGY -------------------
    int getTotalEnergy() throws RemoteException;

    // ------------------- KITCHEN -------------------
    boolean isFireDetected() throws RemoteException;

    // ------------------- SCHEDULING -------------------
    String scheduleLight(String room, String fromTime, String toTime, boolean turnOn) throws RemoteException;
}