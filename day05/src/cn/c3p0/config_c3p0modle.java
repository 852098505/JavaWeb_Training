package cn.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    通过c3p0-config.xml文件方式配置连接池，可以在定义ComboPooledDataSource完成时，自动读取xml配置文件，同写代码一样的效果
* */

public class config_c3p0modle {

    private static ComboPooledDataSource dataSource  = new ComboPooledDataSource();

    @Test
    public void test() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("select * from user where id < ?");
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    rs = null;
                }
            }
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
