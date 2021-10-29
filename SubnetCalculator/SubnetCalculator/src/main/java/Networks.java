import java.util.ArrayList;
import java.util.List;

public class Networks {
    private List<String> iDs = new ArrayList<>();
    private List<String> bcs = new ArrayList<>();
    private List<List<String>> ips = new ArrayList<>();
    private List<List<String>> networks = new ArrayList<>();
    private int host;

    public Networks(IPAddress iD, Subnetmask snm, IPAddress broadcast, int hosts) {
        calculateIDs(iD, snm);
        calculateBCs(broadcast, iD, snm);
        calculateIPs(snm);
        setHost(hosts);
        calculateAllNets();
    }

    public void calculateAllNets() {
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i < iDs.size(); i++) {
            List<String> result = new ArrayList<>();
            result.add("Netz ID= " + iDs.get(i));
            result.add("Braodcast= " + bcs.get(i));
            result.add(ips.get(i).get(0));
            result.add(ips.get(i).get(1));
            result.add("Mögliche Hosts= " + getHost());
            resultLists.add(result);
        }
        setNetworks(resultLists);
    }

    private void calculateIPs(Subnetmask snm) {
        List<List<String>> resultList = new ArrayList<>();
        String snmBinary = snm.toBinaryString();
        for (int i = 0; i < iDs.size(); i++) {
            String[] iPs = new String[iDs.size()];
            String[] iPslast = new String[iDs.size()];
            List<String> result = new ArrayList<>();
            if ( snmBinary.lastIndexOf("1") >= 24) {
                int firstIPs = Integer.parseInt(iDs.get(i).substring(iDs.get(i).lastIndexOf(".")+1))+1;
                iPs[i] = "Erste IP = " + iDs.get(i).substring(0, iDs.get(i).lastIndexOf(".")+1)+firstIPs;
                result.add(iPs[i]);
            } else if ( snmBinary.lastIndexOf("1") < 24) {
                iPs[i] = "Erste IP = " + iDs.get(i).substring(0, iDs.get(i).lastIndexOf(".")+1)+"1";
                result.add(iPs[i]);
            }
            int lastIPs = Integer.parseInt(bcs.get(i).substring(bcs.get(i).lastIndexOf(".") + 1)) - 1;
            iPslast[i] = "Letzte IP = " + bcs.get(i).substring(0, bcs.get(i).lastIndexOf(".") + 1) + lastIPs;
            result.add(iPslast[i]);
            resultList.add(result);
            setIps(resultList);
        }
    }

    private void calculateBCs(IPAddress broadcast, IPAddress iD, Subnetmask snm) {
        List<String> bcsList = new ArrayList<>();
        bcsList.add(broadcast.toString());
        String snmBinary = snm.toBinaryString();
        IPAddress invertedSnm = snm.invert();
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            int bcsSize = 256 / Integer.parseInt(iDsFromSNM);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                Subnetmask[] bcs = new Subnetmask[bcsSize];
                bcs[counter] = new Subnetmask(iD.getFirst(), iD.getSecond(), iD.getThird(), i - 1);
                bcsList.add(bcs[counter].toString());
                counter++;
            }
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") < 23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            int bcsSize = 256 / Integer.parseInt(iDsFromSNM);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                Subnetmask[] bcs = new Subnetmask[bcsSize];
                bcs[counter] = new Subnetmask(iD.getFirst(), iD.getSecond(), i-1 , invertedSnm.getFourth());
                bcsList.add(bcs[counter].toString());
                counter++;
            }
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") < 15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            int bcsSize = 256 / Integer.parseInt(iDsFromSNM);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                Subnetmask[] bcs = new Subnetmask[bcsSize];
                bcs[counter] = new Subnetmask(iD.getFirst(), i-1, invertedSnm.getThird() , invertedSnm.getFourth());
                bcsList.add(bcs[counter].toString());
                counter++;
            }
        }
        setBcs(bcsList);
    }

    private void calculateIDs(IPAddress iD, Subnetmask snm) {
        List<String> iDsList = new ArrayList<>();
        String snmBinary = snm.toBinaryString();
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            int iDsSize = 256/Integer.parseInt(iDsFromSNM);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                IPAddress[] iDs = new IPAddress[iDsSize];
                iDs[counter] = new IPAddress(iD.getFirst(), iD.getSecond() , iD.getThird(), i);
                iDsList.add(iDs[counter].toString());
                counter++;
            }
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") < 23) {
            snmBinary = snmBinary.substring(16,24);
            String iDsFromSNM = calcSize(snmBinary);
            int iDsSize = 256/Integer.parseInt(iDsFromSNM);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                IPAddress[] iDs = new IPAddress[iDsSize];
                iDs[counter] = new IPAddress(iD.getFirst(), iD.getSecond() , i, iD.getFourth());
                iDsList.add(iDs[counter].toString());
                counter++;
            }
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") < 15) {
            snmBinary = snmBinary.substring(8,16);
            String iDsFromSNM = calcSize(snmBinary);
            int iDsSize = 256/Integer.parseInt(iDsFromSNM);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                int counter = 0;
                IPAddress[] iDs = new IPAddress[iDsSize];
                iDs[counter] = new IPAddress(iD.getFirst(), i , iD.getThird(), iD.getFourth());
                iDsList.add(iDs[counter].toString());
                counter++;
            }
        }
        setiDs(iDsList);
    }

    private String calcSize(String  snmBinary) {
        snmBinary = snmBinary.substring(snmBinary.lastIndexOf("1"));
        snmBinary = "0".repeat(Math.max(0, 8 - snmBinary.length())) + snmBinary;
        return Integer.parseInt(snmBinary, 2) + "";
    }

    private int getHost() {
        return host;
    }

    public List<List<String>> getNetworks() {
        return networks;
    }

    private void setNetworks(List<List<String>> networks) {
        this.networks = networks;
    }

    private void setBcs(List<String> bcs) {
        this.bcs = bcs;
    }

    private void setiDs(List<String> iDs) {
        this.iDs = iDs;
    }

    private void setIps(List<List<String>> ips) {
        this.ips = ips;
    }

    private void setHost(int host) {
        this.host = host;
    }
}
