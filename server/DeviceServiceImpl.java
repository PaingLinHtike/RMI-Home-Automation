package server;

import common.DeviceService;
import common.EnergyService;
import common.SecurityService;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

public class DeviceServiceImpl extends UnicastRemoteObject implements DeviceService {

    private Map<String, Boolean> devices = new HashMap<>();
    private List<String> history = new ArrayList<>();

    private EnergyService energy;
    private SecurityService security;

    protected DeviceServiceImpl(EnergyService energy, SecurityService security) throws RemoteException {
        super();
        this.energy = energy;
        this.security = security;

        devices.put("Light", false);
        devices.put("Fan", false);
        devices.put("AC", false);
    }

    public String turnOnDevice(String name) throws RemoteException {
        if (!devices.containsKey(name)) return "Device not found";
        if (devices.get(name)) return name + " already ON";

        devices.put(name, true);
        energy.addUsage(name);

        String log = new Date() + " -> " + name + " ON";
        history.add(log);

        return name + " turned ON";
    }

    public String turnOffDevice(String name) throws RemoteException {
        if (!devices.containsKey(name)) return "Device not found";
        if (!devices.get(name)) return name + " already OFF";

        devices.put(name, false);

        String log = new Date() + " -> " + name + " OFF";
        history.add(log);

        return name + " turned OFF";
    }

    public String getAllDeviceStatus() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        for (String d : devices.keySet()) {
            sb.append(d).append(" : ").append(devices.get(d) ? "ON" : "OFF").append("\n");
        }
        sb.append("Door : ").append(security.getDoorStatus()).append("\n");
        return sb.toString();
    }

    public String getDeviceHistory() throws RemoteException {
        if (history.isEmpty()) return "No history available";

        StringBuilder sb = new StringBuilder();
        for (String h : history) sb.append(h).append("\n");
        return sb.toString();
    }

    public String activateAwayMode() throws RemoteException {
        for (String d : devices.keySet()) {
            devices.put(d, false);
            history.add(new Date() + " -> " + d + " OFF (Away Mode)");
        }

        return security.lockDoor();
    }
}
