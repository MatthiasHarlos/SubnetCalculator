import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Network {
    private List<String> iDs = new ArrayList<>();
    private List<String> bcs = new ArrayList<>();
    private List<String> ips = new ArrayList<>();
    private int host;

    public Network(IPAddress iD, Subnetmask snm) {
    }

    public void setBcs(List<String> bcs) {
        this.bcs = bcs;
    }

    public void setiDs(List<String> iDs) {
        this.iDs = iDs;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public void setHost(int host) {
        this.host = host;
    }
}
