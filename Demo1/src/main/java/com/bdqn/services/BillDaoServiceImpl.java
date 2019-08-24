package com.bdqn.services;

import com.bdqn.conlletion.BillDao;
import com.bdqn.entity.Bill;
import com.bdqn.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("bds")
public class BillDaoServiceImpl implements BillDaoservice {
    private SqlSession session;
    @Autowired
    private BillDao billDao;
    @Override
    public List<Bill> getAll() {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao mapper = this.session.getMapper(BillDao.class);
        return billDao.getAll();
    }

    @Override
    public List<Bill> selectInfo(Bill bill) {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao mapper = session.getMapper(BillDao.class);
        return billDao.selectInfo(bill);
    }

    @Override
    public int addInfo(Bill bill) {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao mapper = session.getMapper(BillDao.class);
//        int i = mapper.addInfo(bill);
//        session.commit();
        return billDao.addInfo(bill);
    }

    @Override
    public Bill userInfo(int id) {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao u  = session.getMapper(BillDao.class);
        return billDao.userInfo(id);
    }

    @Override
    public int updateInfo(Bill bill) {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao u  = session.getMapper(BillDao.class);
//        int i = u.updateInfo(bill);
//        if (i>0){
//            session.commit();
//        }
        return billDao.updateInfo(bill);
    }

    @Override
    public int delInfo(int id) {
//        session = MybatisUtil.getInstance().getSession();
//        BillDao u  = session.getMapper(BillDao.class);
//        int i = u.delInfo(id);
//        if (i>0){
//            session.commit();
//        }
        return billDao.delInfo(id);
    }
}
