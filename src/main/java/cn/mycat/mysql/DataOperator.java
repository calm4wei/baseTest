package cn.mycat.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created on 2016/12/1
 *
 * @author feng.wei
 */
public class DataOperator {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Connection connection = DataSource.getMycatConnect();
        String sql = "insert into user(id, name) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        int i = 1101;
        int len = chs.length();
        Random random = new Random();

        while (i < 11000) {
            i++;
            // statement.clearParameters();
            statement.setInt(1, i);
            statement.setString(2, String.valueOf(chs.charAt(random.nextInt(len))));
            statement.execute();
        }

        statement.close();
        connection.close();
    }

}
