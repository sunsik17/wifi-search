package wifi;

import java.util.List;

public class WifiInfo {
    public List<Wifi_ApiJson> getList() {
        return list;
    }

    public void setList(List<Wifi_ApiJson> list) {
        this.list = list;
    }

    private List<Wifi_ApiJson> list;
}
