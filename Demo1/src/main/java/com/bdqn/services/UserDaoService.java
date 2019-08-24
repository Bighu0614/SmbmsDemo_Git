package com.bdqn.services;

import com.bdqn.entity.Page;
import com.bdqn.entity.User;
import com.bdqn.entity.User2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDaoService {
    User checkCode(String name, String pwd);

    int checkName(String name);

    List<User2> getAll(Page p);

    List<User2> selectInfo(@Param("user") User2 user, @Param("page") int page);

    int getCount(@Param("user") User2 user, @Param("page") int page);

    int addInfo(User user);

    int updatePwd(String uname,String upwd);

    User2 userInfo(int id);

    int updateInfo(User user);

    int delInfo(int id);

    User2 userbyRole();
}
