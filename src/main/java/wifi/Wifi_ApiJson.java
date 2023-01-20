package wifi;

import DTO.Wifi_RowArrayData;
import wifi.Wifi_ResultData;

import java.util.List;

public class Wifi_ApiJson {
    public int getList_total_count() {
        return list_total_count;
    }

    public void setList_total_count(int list_total_count) {
        this.list_total_count = list_total_count;
    }

    public List<Wifi_ResultData> getResultData() {
        return resultData;
    }

    public void setResultData(List<Wifi_ResultData> resultData) {
        this.resultData = resultData;
    }

    public List<Wifi_RowArrayData> getRowArrayData() {
        return rowArrayData;
    }

    public void setRowArrayData(List<Wifi_RowArrayData> rowArrayData) {
        this.rowArrayData = rowArrayData;
    }

    private int list_total_count;
    private List<Wifi_ResultData> resultData;
    private List<Wifi_RowArrayData> rowArrayData;
}
