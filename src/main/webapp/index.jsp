<%@ page import="java.util.Collections" %>
<%@ page import="DB.WifiDB_Use" %>
<%@ page import="DTO.History" %>
<%@ page import="java.util.Date" %>
<%@ page import="DB.HistoryDB_Use" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>WifiInfo_Service</title>
</head>
<body>
<%!
    public double carDistance(double lat1, double lon1, double lat2, double lon2) {


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;

        return (dist);
    }

    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
%>
<script>
    const latLnt = (event) => {
        event.preventDefault();
        navigator.geolocation.getCurrentPosition((position) => {
            let latitude = position.coords.latitude;
            let longitude = position.coords.longitude;

            document.getElementById("LAT").value = latitude;
            document.getElementById("LNT").value = longitude;

        }, (error) => {

        })
    }
</script>
<style>
    h1 {
        font-weight: 900;
    }

    span {
        padding: 5px;
    }

    input {
        text-align: right;
    }

    table {
        width: 100%;
        border: 1px solid silver;
        border-collapse: collapse;
    }

    th {
        background-color: #00a869;
        color: white;
        font-size: 11px;
        height: 1.75rem;
        border-right: 1px solid white;
    }

    .onTable {
        text-align: center;
        height: 10rem;
        font-size: 10pt;
    }

    th:last-child {
        border-right: none;
    }

    td {
        border: 1px solid silver;
        font-size: 11px;
        height: 1.75rem;
        text-align: center;
        font-weight: bolder;
    }

</style>
<h1> 내 위치 근처 Wifi 정보 얻기 </h1>
<br>
<span>
    <a href="index.jsp">Home</a>
</span>
|
<span>
    <a href="history.jsp">Location History</a>
</span>
|
<span>
    <a href="wifi_info.jsp">Open Wifi Info Download</a>
</span>

<form method="get">
    <br/>
    LNT: <input id="LNT" type="number" name="lnt" step="any" value="0.00"/>
    LAT: <input id="LAT" type="number" name="lat" step="any" value="0.00"/>
    <button onclick="latLnt(event)">내 위치 불러오기</button>
    <button type="submit">근처 wifi 정보보기</button>
</form>
<br/>
<div>
    <table>
        <thead>
        <tr>
            <th>거리(km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>

            <%
    History history = new History();
    WifiDB_Use wifiDBUse = new WifiDB_Use();
    wifiDBUse.create();
    wifiDBUse.addData();

    if (request.getParameter("lat") != null) {

        double lnt = Double.parseDouble(request.getParameter("lnt"));
        double lat = Double.parseDouble(request.getParameter("lat"));
        Date now = new Date();
        history.setX(Double.toString(lnt));
        history.setY(Double.toString(lat));
        history.setNowTime(now.toString());
        HistoryDB_Use historyDBUse = new HistoryDB_Use();
        historyDBUse.create();
        historyDBUse.insert(history);

        for (int j = 0; j < WifiDB_Use.list.size(); j++) {
            if (WifiDB_Use.list.get(j) != null) {
                WifiDB_Use.list.get(j).distance =
                        carDistance(lnt,
                                lat,
                                Double.parseDouble(WifiDB_Use.list.get(j).getLAT()),
                                Double.parseDouble(WifiDB_Use.list.get(j).getLNT()));
            } else {
                continue;
            }
        }
        Collections.sort(WifiDB_Use.list, (x,y)-> Double.compare(x.distance,y.distance));
        for(int i = 0; i < 20; i++) {
            if (i % 2 != 0){
                out.write("<tr style=\"background-color: #e9e9e9\">");
            } else {
                out.write("<tr>");
            }
            out.write("<td>" + String.format("%.2f",WifiDB_Use.list.get(i).distance) + " km" + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_MGR_NO() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_WRDOFC() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_MAIN_NM() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_ADRES1() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_ADRES2() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_INSTL_FLOOR() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_INSTL_TY() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_INSTL_MBY() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_SVC_SE() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_CMCWR() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_CNSTC_YEAR() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_INOUT_DOOR() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getX_SWIFI_REMARS3() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getLNT() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getLAT() + "</td>");
            out.write("<td>" + WifiDB_Use.list.get(i).getWORK_DTTM() + "</td>");
            out.write("</tr>");
        }
        out.write("</table>\n" +"</div>");
    } else {
        out.write("<tr>\n" +
            "            <td class=\"onTable\" colspan=\"17\" ><b>위치정보를 입력한후에 조회해주세요</b></td>\n" +
            "        </tr>\n" +
            "    </table>" +
            "</div>");
    }
%>
</body>
</html>