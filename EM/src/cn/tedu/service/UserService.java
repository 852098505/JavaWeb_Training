package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public class UserService {
    private UserDao userDao = new UserDao();
    /**
     * 注册用户
     */
    public void regist(User user){
        //1.检查用户名是否已经存在
        User findUser = userDao.queryUserByUsername(user.getUsername());
        //2.判断用户名是否存在
        if(findUser!=null){//用户名已存在
            throw new MsgException("用户名已经存在");
        }else{//用户名可用
            userDao.addUser(user);
        }
    }

    /**
     * 检查用户名是否已经存在
     */
    public boolean hasUsername(String username){
        User user = userDao.queryUserByUsername(username);
        return user != null;
    }

    /**
     * 登录用户
     */
    public User login(String username, String password) {
        User user = userDao.queryUserByUsernameAndPsw(username,password);
        if(user!=null){
            return user;
        }else{
            throw new MsgException("用户名密码不正确！");
        }
    }
}
