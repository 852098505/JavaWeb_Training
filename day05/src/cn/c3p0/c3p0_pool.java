package cn.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class c3p0_pool {

    /*
    c3p0数据源
    * */

    //创建c3p0连接池
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            //配置驱动名称
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            //配置数据库地址
            dataSource.setJdbcUrl("jdbc:mysql:///mydb5");
            //数据库用户名
            dataSource.setUser("root");
            //数据库密码
            dataSource.setPassword("12345678");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

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
