package cn.tedu.domain;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Date;

public class User implements HttpSessionBindingListener {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private static Logger logger = Logger.getLogger(User.class);
    @Override
    public void valueBound(HttpSessionBindingEvent hsbe) {
        logger.info("用户["+this.username+"]登录！");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent hsbe) {
        logger.info("用户["+this.username+"]登出！");
    }
}
