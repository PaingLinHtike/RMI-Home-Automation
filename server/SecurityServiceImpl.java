package server;

import common.SecurityService;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Date;

public class SecurityServiceImpl extends UnicastRemoteObject implements SecurityService {

    private boolean locked = false;
    private boolean alarmOn = false;
    private String lastVisitor = "No visitors yet";

    protected SecurityServiceImpl() throws RemoteException {
        super(5002);
    }

    public String lockDoor() throws RemoteException {
        if (locked) return "Door already locked";
        locked = true;
        return "Door locked";
    }

    public String unlockDoor() throws RemoteException {
        if (!locked) return "Door already unlocked";
        locked = false;
        return "Door unlocked";
    }

    public String getDoorStatus() throws RemoteException {
        return locked ? "Locked" : "Unlocked";
    }

    // Smart Doorbell

    public synchronized String ringDoorbell() throws RemoteException {
        lastVisitor = "Visitor detected at " + new Date();
        System.out.println("🔔 Doorbell rang!");
        return "🔔 Someone is at the door!";
    }

    public synchronized String getLastVisitor() throws RemoteException {
        return lastVisitor;
    }

    // Alarm System

    public synchronized String triggerAlarm() throws RemoteException {
        alarmOn = true;
        System.out.println("🚨 SECURITY ALERT!");
        return "🚨 Alarm triggered!";
    }

    public synchronized String resetAlarm() throws RemoteException {
        alarmOn = false;
        return "Alarm reset";
    }

    // Remote monitoring

    public synchronized String getSecurityStatus() throws RemoteException {
        return "Door: " + (locked ? "Locked" : "Unlocked") +
                "\nAlarm: " + (alarmOn ? "ACTIVE 🚨" : "Normal") +
                "\nLast Visitor: " + lastVisitor;
    }
}
