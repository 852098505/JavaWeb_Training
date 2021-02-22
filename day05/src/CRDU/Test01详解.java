package CRDU;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class Test01详解 {



    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            //注册数据库驱动
            //DriverManager.registerDriver(new Driver());//注意⚠️：仔细查看Dirver()方法，会发现静态代码块中也会注册一遍，返回的对象再一次放回注册管理器中，二次注册
            Class.forName("com.mysql.cj.jdbc.Driver");//反射机制，去内存找有没有这个，有就返回类名，有且仅有一次加载
            //获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4", "root", "12345678");
            //获取传输器
            stat = conn.createStatement();
            //执行sql获取结果集
            rs = stat.executeQuery("select * from user");
            //遍历结果集
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name + "   " + age);
            }
        } catch (Exception e) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    rs = null;
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    stat = null;
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
