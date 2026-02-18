package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SecurityService extends Remote {
    String lockDoor() throws RemoteException;
    String unlockDoor() throws RemoteException;
    String getDoorStatus() throws RemoteException;
}
