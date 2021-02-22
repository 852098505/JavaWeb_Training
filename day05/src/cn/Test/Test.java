package cn.Test;

import java.sql.*;
import java.util.Scanner;

public class Test {

    /*
    会导致sql注入影响结果

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
        String passowrd = scanner.nextLine();

        //2.查询数据库中的正确的用户名和密码
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4","root","12345678");
            state = conn.createStatement();
            //会导致sql注入攻击
            rs = state.executeQuery("select * from user2 where username = '"+username+"' and password = '"+passowrd+"'");
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
            if(state !=null){
                try {
                    state.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    state = null;
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
