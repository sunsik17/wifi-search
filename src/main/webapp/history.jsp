<%@ page import="DTO.History" %>
<%@ page import="java.util.Collections" %>
<%@ page import="DB.HistoryDB_Use" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: nss
  Date: 2022/12/29
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location History</title>
</head>
<body>
<%!
    HistoryDB_Use historyDBUse = new HistoryDB_Use();

%>
<style>
    h1 {
        display: block;
        font-size: 2em;
        margin-block-start: 0.67em;
        margin-block-end: 0.67em;
        font-weight: 900;
    }

    span {
        padding: 5px;
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

<h1>저장된 Location History 목록</h1>
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

<table id="dT">
    <thead>
    <th>ID</th>
    <th>X 좌표</th>
    <th>Y 좌표</th>
    <th>조회일자</th>
    <th>비고</th>
    </thead>
    <tbody>
    <%
        historyDBUse.select();
        for (int i = historyDBUse.list.size() - 1; i >= 0; i--) {
            if (i % 2 != 0) {
                out.write("<tr style=\"background-color: #e9e9e9\">");
            } else {
                out.write("<tr>");
            }
            out.write("<td>" + (i + 1) + "</td>");
            out.write("<td>" + historyDBUse.list.get(i).getX() + "</td>");
            out.write("<td>" + historyDBUse.list.get(i).getY() + "</td>");
            out.write("<td>" + historyDBUse.list.get(i).getNowTime() + "</td>");
            out.write("<td>" + " <button type=\"button\" class=\"btn_delete\">Delete</button>" + "</td>");
            out.write("</tr>");

        }
    %>
    </tbody>
</table>
</body>
</html>
