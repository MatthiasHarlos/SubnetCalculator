import java.util.*;

public class SubnetCalculator{

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        IPAddress ip = checkUserIPInput();
        Subnetmask snm = checkUserSNMInput();
        System.out.println("Eingegebene IP: " + ip);
        System.out.println("Eingegebene SNM: " + snm + "\n");
        System.out.println("IP (bin) : " + ip.toBinaryString());
        System.out.println("SNM (bin): " + snm.toBinaryString() + "\n");
        IPAddress netID = calculateNetID(ip, snm);
        IPAddress broadcast = calculateBroadcastIp(netID, snm);
        int hosts = snm.getHosts();
        Networks networks = new Networks(netID, snm, broadcast, hosts);
        resultOutputForUser(netID, broadcast, hosts, networks, snm);
    }

    private static void resultOutputForUser(IPAddress netID, IPAddress broadcast, int hosts, Networks networks, Subnetmask snm) {
        System.out.println("Netz ID= " + netID);
        System.out.println("Broadcast= " + broadcast);
        System.out.println("Mögliche Hosts= " + hosts + "\n");
        System.out.println("Alle möglichen Netze mit der Subnetzmaske " + snm  + " sind:\n");
        for (List<String> allNetworks : networks.getNetworks()) {
            System.out.println(allNetworks);
        }
    }

    public static IPAddress calculateBroadcastIp(IPAddress netId, Subnetmask subnetmask) {
        Objects.requireNonNull(netId);
        Objects.requireNonNull(subnetmask);
        IPAddress invertedSnm = subnetmask.invert();
        int first = netId.getFirst() + invertedSnm.getFirst();
        int second = netId.getSecond() + invertedSnm.getSecond();
        int third = netId.getThird() + invertedSnm.getThird();
        int fourth = netId.getFourth() + invertedSnm.getFourth();
        return new IPAddress(first, second, third, fourth);
    }

    private static IPAddress calculateNetID(IPAddress ip, Subnetmask snm) {
        Objects.requireNonNull(ip);
        Objects.requireNonNull(snm);
        return ip.logicalAnd(snm);
    }

    private static IPAddress checkUserIPInput() {
        System.out.println("Bitte gib eine IP im Format: 1.1.1.1 ein!");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
        try {
            return new IPAddress(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Ungültige Eingabe, bitte wiederholen!");
            return checkUserIPInput();
        }
    }

    private static Subnetmask checkUserSNMInput() {
     System.out.println("Bitte gib eine Subnetzmaske ein!");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        try {
            return new Subnetmask(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Ungültige Eingabe, bitte wiederholen!");
            return checkUserSNMInput();
        }
    }
}