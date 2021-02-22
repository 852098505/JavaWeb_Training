package cn.Test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementBatch {

    @Test
    public void test() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb5", "root", "12345678");
            ps = conn.prepareStatement("insert into user values (?,?)");
            for (int i = 1; i < 1000; i++) {
                ps.setInt(1, i);
                ps.setString(2, "w" + i);
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    ps = null;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    conn = null;
                }
            }
        }

    }
}
