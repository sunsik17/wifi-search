<%@ page import="com.squareup.okhttp.OkHttpClient" %>
<%@ page import="com.squareup.okhttp.Request" %>
<%@ page import="com.squareup.okhttp.Response" %>
<%@ page import="DTO.Wifi_RowArrayData" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="DB.WifiDB_Use" %>
<%--
  Created by IntelliJ IDEA.
  User: nss
  Date: 2022/12/29
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi Info Download</title>
</head>
<body>

<%
    //디비에 api 정도 저장 약 19000개
    WifiDB_Use dbUse = new WifiDB_Use();
    dbUse.create();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    OkHttpClient client = new OkHttpClient();

//    if (dbUse.Create() == 0){
//        out.write("<h1>이미 와이파이 정보를 저장하였습니다.</h1>");
//        out.write("<p>" +
//                "    <a href=\"index.jsp\">Home으로 돌아가기</a>\n" +
//                "</p>");
//    } else {
    int total = 0;
    int startIdx = 1;
        for (int i = 1; i <= 20; i++) {
            try {
                Request req = new Request.Builder().url("http://openapi.seoul.go.kr:8088/504172795773756e3431677967434d/json/TbPublicWifiInfo/" + startIdx + "/" + i + "000/").get().build();
                startIdx += 1000;
                Response res = client.newCall(req).execute();//execute 마무리
                if (res.isSuccessful()) {
                    String str = res.body().string();
                    JsonObject object = new Gson().fromJson(str, JsonObject.class);
                    JsonObject jo = object.getAsJsonObject("TbPublicWifiInfo");
                    JsonArray row = jo.get("row").getAsJsonArray();

                    for (int j = 0; j < 1000; j++) {

                        JsonObject tmp = row.get(j).getAsJsonObject();
                        Wifi_RowArrayData rowArrayData = gson.fromJson(tmp, Wifi_RowArrayData.class);
                        dbUse.insert(rowArrayData);

                        total++;
                        if (total == jo.get("list_total_count").getAsInt()) {
                            break;
                        }
                    }
                    if (total == jo.get("list_total_count").getAsInt()) {
                        break;
                    }
                } else {
                    System.out.println("요청 실패");
                    System.out.println(res.code());
                    System.out.println(res.body());
                    System.out.println(res.message());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    dbUse.addData();
%>
<style>
    h1 {
        text-align: center;
    }

    p {
        text-align: center;
    }
</style>
<h1><%=total%>개의 와이파이정보를 정상적으로 저장 하였습니다.</h1>
<p>
    <a href="index.jsp">Home으로 돌아가기</a>
</p>
</body>
</html>
