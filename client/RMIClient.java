//package client;
//
//import common.*;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.Scanner;
//
//public class RMIClient {
//
//    public static void main(String[] args) {
//        try {
//            Registry registry = LocateRegistry.getRegistry("34.177.96.217", 1099);
//
//            DeviceService device = (DeviceService) registry.lookup("DeviceService");
//            EnergyService energy = (EnergyService) registry.lookup("EnergyService");
//            SecurityService security = (SecurityService) registry.lookup("SecurityService");
//
//            Scanner sc = new Scanner(System.in);
//
//            while (true) {
//                System.out.println("\n1 ON Device. 2 OFF Device. 3 STATUS. 4 ENERGY. 5 BILL. 6 HISTORY. 7 AWAY MODE. 8 LOCK DOOR. 9 UNLOCK DOOR. 0 EXIT.");
//                int ch = sc.nextInt();
//                sc.nextLine();
//
//                if (ch == 1) {
//                    System.out.print("Device: ");
//                    System.out.println(device.turnOnDevice(sc.nextLine()));
//                }
//                else if (ch == 2) {
//                    System.out.print("Device: ");
//                    System.out.println(device.turnOffDevice(sc.nextLine()));
//                }
//                else if (ch == 3) {
//                    System.out.println(device.getAllDeviceStatus());
//                }
//                else if (ch == 4) {
//                    System.out.println("Total energy: " + energy.getTotalEnergy());
//                }
//                else if (ch == 5) {
//                    System.out.println("Bill: $" + energy.calculateBill());
//                }
//                else if (ch == 6) {
//                    System.out.println(device.getDeviceHistory());
//                }
//                else if (ch == 7) {
//                    System.out.println(device.activateAwayMode());
//                }
//                else if (ch == 8) {
//                    System.out.println(security.lockDoor());
//                }
//                else if (ch == 9) {
//                    System.out.println(security.unlockDoor());
//                }
//                else if (ch == 10) {
//                    System.out.println(security.getDoorStatus());
//                }
//                else {
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
