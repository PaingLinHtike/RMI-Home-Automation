package server;

import common.EnergyService;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class EnergyServiceImpl extends UnicastRemoteObject implements EnergyService {

    private int totalEnergy = 0;
    private final double RATE = 0.12;   // price per unit

    protected EnergyServiceImpl() throws RemoteException {
        super(5001);
    }

    public synchronized void addUsage(String device) throws RemoteException {
        totalEnergy += 10;
        System.out.println(device + " added usage. Total = " + totalEnergy);
    }

    public int getTotalEnergy() throws RemoteException {
        return totalEnergy;
    }

    public double calculateBill() throws RemoteException {
        return totalEnergy * RATE;
    }
}
