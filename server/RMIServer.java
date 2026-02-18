package server;

import common.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            EnergyService energy = new EnergyServiceImpl();
            SecurityService security = new SecurityServiceImpl();
            DeviceService device = new DeviceServiceImpl(energy, security);

            registry.rebind("EnergyService", energy);
            registry.rebind("SecurityService", security);
            registry.rebind("DeviceService", device);

            System.out.println("Servers are running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
