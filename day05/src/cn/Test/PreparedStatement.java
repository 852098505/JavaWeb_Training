package cn.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatement {

    /*
    使用PreparedStatement防止sql注入影响结果
    相对于Statement，PreparedStatement的优点如下
        1。可以防止sql注入攻击
        2。由预编译机制，效率更高
        3。减少sql的拼接，是代码更加优雅

    * use mydb4;
    *
    * create table user2(
    *   id int,
    *   username varchar(20),
    *   password varchar(20)
    * );
    *
    * insert into user2 values (1,'aaa','12345');
    * insert into user2 values (2,'bbb','faewf');
    * insert into user2 values (3,'vvv','3fw3fse');
    *
    * */


    public static void main(String[] args) {
        //1.读取输入的用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        //2.查询数据库中的正确的用户名和密码
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4","root","12345678");
            //使用PrepareStatement预编译功能防止传入的参数影响sql语句
            ps = conn.prepareStatement("select * from user2 where username =  ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("用户名密码正确！");
            }else {
                System.out.println("用户名密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs !=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    rs = null;
                }
            }
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    ps = null;
                }
            }
            if(conn !=null){
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
