package CRDU;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {

    /*
    * 删除Delete
    * */

    public static void main(String[] args) {
        Connection conn = null;
        Statement state = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取链接
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4","root","12345678");
            //获取传输器
            state = conn.createStatement();
            //执行sql
            int count = state.executeUpdate("delete from user where id = 6");
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    state = null;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    conn = null;
                }
            }
        }

    }
}
