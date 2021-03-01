package cn.tedu.dao;

import cn.tedu.domain.User;
import cn.tedu.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    /**
     * 根据用户名查询用户
     */
    public User queryUserByUsername(String username){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //1.注册数据库驱动
            //2.获取数据库连接
            conn = JDBCUtils.getConn();
            //3.获取传输器
            ps = conn.prepareStatement("select * from user where username = ?");
            ps.setString(1,username);
            //4.传输sql执行获取结果集
            rs = ps.executeQuery();
            //5.获取结果
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                return user;
            }else{
                return null;
            }
            //6.关闭资源
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }

    /**
     * 新增用户
     */
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            //1.注册数据库驱动
            //2.获取数据库连接
            conn = JDBCUtils.getConn();
            //3.获取传输器
            ps = conn.prepareStatement("insert into user values (null,?,?,?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNickname());
            ps.setString(4,user.getEmail());
            //4.执行sql获取结果
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //5.关闭资源
            JDBCUtils.close(conn,ps,null);
        }
    }

    /**
     * 根据用户名密码查找用户
     */
    public User queryUserByUsernameAndPsw(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //1.注册数据库驱动
            //2.获取数据库连接
            conn = JDBCUtils.getConn();
            //3.获取传输器
            ps = conn.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            //4.传输sql执行获取结果集
            rs = ps.executeQuery();
            //5.处理结果
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                return user;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //6.关闭资源
            JDBCUtils.close(conn,ps,rs);
        }
    }
}
