package server;

import common.SecurityService;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SecurityServiceImpl extends UnicastRemoteObject implements SecurityService {

    private boolean locked = false;

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
}
