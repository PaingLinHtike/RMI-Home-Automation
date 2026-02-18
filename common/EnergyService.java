package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EnergyService extends Remote {
    void addUsage(String device) throws RemoteException;
    int getTotalEnergy() throws RemoteException;
    double calculateBill() throws RemoteException;
}
