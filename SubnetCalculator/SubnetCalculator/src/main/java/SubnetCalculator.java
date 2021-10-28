import java.util.*;

public class SubnetCalculator{

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        IPAddress ip = checkUserIPInput();
        Subnetmask snm = checkUserSNMInput();

        System.out.println("Eingegebene IP: " + ip.toString());
        System.out.println("Eingegebene SNM: " + snm.toString());
        System.out.println("");
        System.out.println("IP (bin) : " + ip.toBinaryString());
        System.out.println("SNM (bin): " + snm.toBinaryString());
        System.out.println("");
        IPAddress netID = calculateNetID(ip, snm);
        IPAddress broadcast = calculateBroadcastIp(netID, snm);
        int hosts = snm.getHosts();
        //Network network = new Network(netID, snm);
        resultOutputForUser(netID, broadcast, hosts);
    }

    private static void resultOutputForUser(IPAddress netID, IPAddress broadcast, int hosts) {
        System.out.println("Netz ID= " + netID);
        System.out.println("Broadcast= " + broadcast);
        System.out.println("Mögliche Hosts= " + hosts);
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

  /*  public static void resultOutputForUser(String iDs, String bCs, List<List<String>> iPs, int hosts) {
        List<String> iDList = Arrays.asList(iDs.split("\\|"));
        List<String> bcList = Arrays.asList(bCs.split("\\|"));
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i < iDList.size(); i++) {
            List<String> result = new ArrayList<>();
            result.add(iDList.get(i));
            result.add(bcList.get(i));
            result.add(iPs.get(i).get(0));
            result.add(iPs.get(i).get(1));
            result.add("Mögliche Hosts= " + hosts);
            resultLists.add(result);
        }
        for (List<String> resultList : resultLists) {
            System.out.println(resultList);
        }
    }*/
}