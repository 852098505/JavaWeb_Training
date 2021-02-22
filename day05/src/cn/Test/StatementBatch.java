package cn.Test;

import org.junit.Test;

import java.sql.*;

public class StatementBatch {

    /*
    create database mydb5;

    use mydb5;

    create table user(
        id int,
        name varchar(20)
    );

    insert into user values (1,'aaa');
    insert into user values (2,'bbb');
    insert into user values (3,'ccc');
    * */


    @Test
    public void test() {

        Connection conn = null;
        Statement state = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4", "root", "12345678");
            state = conn.createStatement();
            state.addBatch("create database mydb5");
            state.addBatch("use mydb5");
            state.addBatch("create table user (id int,name varchar(20))");
            state.addBatch("insert into user values (1,'aaa')");
            state.addBatch("insert into user values (2,'bbb')");
            state.addBatch("insert into user values (3,'ccc')");

            int[] result = state.executeBatch();
            for (int i : result) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    state = null;
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
