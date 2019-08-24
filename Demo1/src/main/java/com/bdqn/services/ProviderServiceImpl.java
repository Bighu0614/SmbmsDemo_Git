package com.bdqn.services;

import com.bdqn.conlletion.ProviderDao;
import com.bdqn.entity.Provider;
import com.bdqn.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ps")
public class ProviderServiceImpl implements ProviderService {
    private SqlSession session;
    @Autowired
    private ProviderDao prd;
    @Override
    public List<Provider> getAll() {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
        return prd.getAll();
    }

    @Override
    public     List<Provider> selectInfo(String code,String name) {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
        return prd.selectInfo(code,name);
    }

    @Override
    public int addInfo(Provider provider) {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
//        int i = mapper.addInfo(provider);
//        if (i>0){
//            session.commit();
//        }
        return prd.addInfo(provider);
    }

    @Override
    public Provider userInfo(int id) {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
        return prd.userInfo(id);
    }

    @Override
    public int updateInfo(Provider provider) {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
//        int i = mapper.updateInfo(provider);
//        if (i>0){
//            session.commit();
//        }
        return prd.updateInfo(provider);
    }

    @Override
    public int delInfo(int id) {
//        session = MybatisUtil.getInstance().getSession();
//        ProviderDao mapper = session.getMapper(ProviderDao.class);
//        int i = mapper.delInfo(id);
//        if (i>0){
//            session.commit();
//        }
        return prd.delInfo(id);
    }
}
