package com.bdqn.services;

import com.bdqn.conlletion.RoleDao;
import com.bdqn.entity.Role;
import com.bdqn.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("rs")
public class RoleServiceImpl implements RoleService {
    private SqlSession session = null;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> getAll() {
//        session = MybatisUtil.getInstance().getSession();
//        RoleDao mapper = session.getMapper(RoleDao.class);
        return roleDao.getAll();
    }
}
