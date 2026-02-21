package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SecurityService extends Remote {
    String lockDoor() throws RemoteException;
    String unlockDoor() throws RemoteException;
    String getDoorStatus() throws RemoteException;

    // smart doorbell
    String ringDoorbell() throws RemoteException;
    String getLastVisitor() throws RemoteException;

    // alarm system
    String triggerAlarm() throws RemoteException;
    String resetAlarm() throws RemoteException;
    String getSecurityStatus() throws RemoteException;
}
