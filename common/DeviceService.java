package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DeviceService extends Remote {
    String turnOnDevice(String name) throws RemoteException;
    String turnOffDevice(String name) throws RemoteException;
    String getAllDeviceStatus() throws RemoteException;
    String getDeviceHistory() throws RemoteException;
    String activateAwayMode() throws RemoteException;
}
