package CRDU;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class Test01 {

    public static void main(String[] args) throws SQLException {
        //注册数据库驱动
        DriverManager.registerDriver(new Driver());
        //获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4","root","12345678");
        //获取传输器
        Statement stat = conn.createStatement();
        //执行sql获取结果集
        ResultSet rs  = stat.executeQuery("select * from user");
        //遍历结果集
        while (rs.next()){
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(name + "   " + age);
        }
        rs.close();
        stat.close();
        conn.close();
    }

}
