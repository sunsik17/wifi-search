package DB;


import DTO.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDB_Use {
    String user = "sunsik";
    String password = "zerobase";
    String jdbcUrl = "jdbc:mariadb://localhost:3306/mission1DB";
    public List<History> list = new ArrayList<>();

    public HistoryDB_Use() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }//jdbc 드라이버 사용

    public void select() {
        list.clear();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            preparedStatement = connection.prepareStatement("select * from history;");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String x = rs.getString("x");
                String y = rs.getString("y");
                String nowTime = rs.getString("nowTime");

                History history = new History();
                history.setX(x);
                history.setY(y);
                history.setNowTime(nowTime);

                list.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && connection.isClosed()) {
                    connection.close();
                }
                if (preparedStatement != null && preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//select 하면서 동시에 list 추가

    public void create() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "CREATE TABLE if not exists history(" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "PRIMARY KEY ( id )," +
                    "x VARCHAR(50)," +
                    "y VARCHAR(50)," +
                    "nowTime VARCHAR(50)" +
                    ")" +
                    " ENGINE=InnoDB DEFAULT CHARSET=utf8;";

            preparedStatement = connection.prepareStatement(q);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //테이블 생성

    public void insert(History history) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "insert into history" +
                    " (x,y,nowTime)" +
                    " values" +
                    " (?,?,?);";

            preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1, history.getX());
            preparedStatement.setString(2, history.getY());
            preparedStatement.setString(3, history.getNowTime());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //나의 위치, 검색시간 기록등을 저장

    public void delete() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "drop table history;";

            preparedStatement = connection.prepareStatement(q);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //구현만하고 사용하진 않음
}

