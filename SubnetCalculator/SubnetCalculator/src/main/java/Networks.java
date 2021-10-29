import java.util.ArrayList;
import java.util.List;

public class Networks {
    private List<IPAddress> iDs = new ArrayList<>();
    private List<IPAddress> bcs = new ArrayList<>();
    private List<List<String>> ips = new ArrayList<>();
    private List<List<String>> networks = new ArrayList<>();
    private int host;

    public Networks(IPAddress iD, Subnetmask snm, IPAddress broadcast, int hosts) {
        setPossibleIDs(iD, snm);
        setPossibleBCs(broadcast, iD, snm);
        setPossibleIPs(snm);
        setHost(hosts);
        calculateAllNets();
    }

    public List<List<String>> getNetworks() {
        return networks;
    }

    private void calculateAllNets() {
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

    private void setPossibleIPs(Subnetmask snm) {
        List<List<String>> resultList = new ArrayList<>();
        String snmBinary = snm.toBinaryString();
        for (int i = 0; i < iDs.size(); i++) {
            String[] iPs = new String[iDs.size()];
            String[] iPslast = new String[iDs.size()];
            List<String> result = new ArrayList<>();
            if ( snmBinary.lastIndexOf("1") >= 24) {
                int firstIPs = Integer.parseInt(iDs.get(i).toString().substring(iDs.get(i).toString().lastIndexOf(".")+1))+1;
                iPs[i] = "Erste IP = " + iDs.get(i).toString().substring(0, iDs.get(i).toString().lastIndexOf(".")+1)+firstIPs;
                result.add(iPs[i]);
            } else if ( snmBinary.lastIndexOf("1") < 24) {
                iPs[i] = "Erste IP = " + iDs.get(i).toString().substring(0, iDs.get(i).toString().lastIndexOf(".")+1)+"1";
                result.add(iPs[i]);
            }
            int lastIPs = Integer.parseInt(bcs.get(i).toString().substring(bcs.get(i).toString().lastIndexOf(".") + 1)) - 1;
            iPslast[i] = "Letzte IP = " + bcs.get(i).toString().substring(0, bcs.get(i).toString().lastIndexOf(".") + 1) + lastIPs;
            result.add(iPslast[i]);
            resultList.add(result);
            this.ips = resultList;
        }
    }

    private void setPossibleBCs(IPAddress broadcast, IPAddress iD, Subnetmask snm) {
        this.bcs.add(broadcast);
        String snmBinary = snm.toBinaryString();
        IPAddress invertedSnm = snm.invert();
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                this.bcs.add(new IPAddress(iD.getFirst(), iD.getSecond(), iD.getThird(), i - 1));
            }
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") < 23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                this.bcs.add(new IPAddress(iD.getFirst(), iD.getSecond(), i-1 , invertedSnm.getFourth()));
            }
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") < 15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM)*2; i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                this.bcs.add(new IPAddress(iD.getFirst(), i-1, invertedSnm.getThird() , invertedSnm.getFourth()));
            }
        }
    }

    private void setPossibleIDs(IPAddress iD, Subnetmask snm) {
        String snmBinary = snm.toBinaryString();
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                this.iDs.add(new IPAddress(iD.getFirst(), iD.getSecond() , iD.getThird(), i));
            }
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") < 23) {
            snmBinary = snmBinary.substring(16,24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                this.iDs.add(new IPAddress(iD.getFirst(), iD.getSecond() , i, iD.getFourth()));
            }
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") < 15) {
            snmBinary = snmBinary.substring(8,16);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = 0; i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                this.iDs.add(new IPAddress(iD.getFirst(), i , iD.getThird(), iD.getFourth()));
            }
        }
    }

    private String calcSize(String  snmBinary) {
        snmBinary = snmBinary.substring(snmBinary.lastIndexOf("1"));
        snmBinary = "0".repeat(Math.max(0, 8 - snmBinary.length())) + snmBinary;
        return Integer.parseInt(snmBinary, 2) + "";
    }

    private int getHost() {
        return host;
    }

    private void setNetworks(List<List<String>> networks) {
        this.networks = networks;
    }

    private void setHost(int host) {
        this.host = host;
    }
}
