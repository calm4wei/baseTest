package cn.mycat.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created on 2016/12/1
 *
 * @author feng.wei
 */
class DataSource {

    private static String url_mycat = "jdbc:mysql://192.168.1.205:8066/test";
    private static String user_mycat = "mycat";
    private static String password_mycat = "mycat";

    private static String url = "jdbc:mysql://192.168.1.205:3306/master_slave_test";
    private static String user = "root";
    private static String password = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getMycatConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url_mycat, user_mycat, password_mycat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
