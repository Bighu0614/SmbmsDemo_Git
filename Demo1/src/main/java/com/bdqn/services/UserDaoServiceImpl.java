package com.bdqn.services;

import com.bdqn.conlletion.UserDao;
import com.bdqn.entity.Page;
import com.bdqn.entity.User;
import com.bdqn.entity.User2;
import com.bdqn.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("uds")
public class UserDaoServiceImpl implements UserDaoService {
    @Autowired
    private UserDao userDao;
    @Override
    public User checkCode(String name, String pwd) {
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
        return userDao.checkCode(name, pwd);
    }

    @Override
    public int checkName(String name) {
        return userDao.checkName(name);
    }

    @Override
    public  List<User2> getAll(Page p){
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
        return userDao.getAll(p);
    }

    @Override
    public  int getCount(User2 user, int page){
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
        return userDao.getCount(user,page);
    }

    @Override
    public int addInfo(User user) {
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
//        int i = u.addInfo(user);
//        if (i>0){
//            session.commit();
//        }
        return userDao.addInfo(user);
    }

    @Override
    public    int updatePwd(String uname,String upwd){
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
//        int i = u.updatePwd(uname,upwd);
//        if (i>0){
//            session.commit();
//        }
        return userDao.updatePwd(uname,upwd);
    }

    @Override
    public List<User2> selectInfo(User2 user2,int page){
//        session = MybatisUtil.getInstance().getSession();
////        UserDao u  = session.getMapper(UserDao.class);
        page = (page-1)*5;
        return userDao.selectInfo(user2,page);
    }
    @Override
    public  User2 userInfo(int id){
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
        return userDao.userInfo(id);
    }

    @Override
    public int updateInfo(User user) {
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
//        int i = u.updateInfo(user);
//        if (i>0){
//            session.commit();
//        }
        return userDao.updateInfo(user);
    }

    @Override
    public int delInfo(int id) {
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
//        int i = u.delInfo(id);
//        if (i>0){
//            session.commit();
//        }
        return userDao.delInfo(id);
    }

    @Override
    public User2 userbyRole() {
//        session = MybatisUtil.getInstance().getSession();
//        UserDao u  = session.getMapper(UserDao.class);
        return userDao.userbyRole();
    }

    public void setUserDao(String userDao) {
    }
}
