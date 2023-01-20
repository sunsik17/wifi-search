package DB;

import DTO.Wifi_RowArrayData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDB_Use {
    String user = "sunsik";
    String password = "zerobase";
    String jdbcUrl = "jdbc:mariadb://localhost:3306/mission1DB";

    public static List<Wifi_RowArrayData> list;
    public WifiDB_Use() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }// jdbc 드라이버 사용
    public void create () {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "CREATE TABLE if not exists wifiInfo(" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "PRIMARY KEY ( id )," +
                    "X_SWIFI_MGR_NO VARCHAR(100)," +
                    "X_SWIFI_WRDOFC VARCHAR(100)," +
                    "X_SWIFI_MAIN_NM VARCHAR(100)," +
                    "X_SWIFI_ADRES1 VARCHAR(100)," +
                    "X_SWIFI_ADRES2 VARCHAR(100)," +
                    "X_SWIFI_INSTL_FLOOR VARCHAR(100)," +
                    "X_SWIFI_INSTL_TY VARCHAR(100)," +
                    "X_SWIFI_INSTL_MBY VARCHAR(100)," +
                    "X_SWIFI_SVC_SE VARCHAR(100)," +
                    "X_SWIFI_CMCWR VARCHAR(100)," +
                    "X_SWIFI_CNSTC_YEAR VARCHAR(100)," +
                    "X_SWIFI_INOUT_DOOR VARCHAR(100)," +
                    "X_SWIFI_REMARS3 VARCHAR(100)," +
                    "LAT VARCHAR(100)," +
                    "LNT VARCHAR(100)," +
                    "WORK_DTTM VARCHAR(100)" +
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }// 테이블 생성
    public void addData () {

        list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            preparedStatement = connection.prepareStatement("select * from wifiInfo");
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                Wifi_RowArrayData wifiRowArrayData = new Wifi_RowArrayData();
                wifiRowArrayData.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                wifiRowArrayData.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                wifiRowArrayData.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                wifiRowArrayData.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                wifiRowArrayData.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                wifiRowArrayData.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                wifiRowArrayData.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                wifiRowArrayData.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                wifiRowArrayData.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                wifiRowArrayData.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                wifiRowArrayData.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                wifiRowArrayData.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                wifiRowArrayData.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                wifiRowArrayData.setLAT(LAT);
                wifiRowArrayData.setLNT(LNT);
                wifiRowArrayData.setWORK_DTTM(WORK_DTTM);

                list.add(wifiRowArrayData);
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }// select 하면서 정보들을 list 에 추가
    public void insert (Wifi_RowArrayData wifiRowArrayData) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "insert into wifiInfo"
                    + " (x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm)"
                    + " values"
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";

            preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1,wifiRowArrayData.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2,wifiRowArrayData.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3,wifiRowArrayData.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4,wifiRowArrayData.getX_SWIFI_ADRES1());
            preparedStatement.setString(5,wifiRowArrayData.getX_SWIFI_ADRES2());
            preparedStatement.setString(6,wifiRowArrayData.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7,wifiRowArrayData.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8,wifiRowArrayData.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9,wifiRowArrayData.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10,wifiRowArrayData.getX_SWIFI_CMCWR());
            preparedStatement.setString(11,wifiRowArrayData.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12,wifiRowArrayData.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13,wifiRowArrayData.getX_SWIFI_REMARS3());
            preparedStatement.setString(14,wifiRowArrayData.getLAT());
            preparedStatement.setString(15,wifiRowArrayData.getLNT());
            preparedStatement.setString(16,wifiRowArrayData.getWORK_DTTM());


            int affectedRow = preparedStatement.executeUpdate();


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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    } //파싱한 json 데이터 테이블에 추가
    public static void Delete () {
        String user = "sunsik";
        String password = "zerobase";
        String jdbcUrl = "jdbc:mariadb://localhost:3306/wifiInfo";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

            String q = "drop table wifiInfo;";

            preparedStatement = connection.prepareStatement(q);

            int affectedRow = preparedStatement.executeUpdate();
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
    } //구현만해놓고 사용하진않음
}
