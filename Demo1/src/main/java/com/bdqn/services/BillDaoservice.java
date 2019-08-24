package com.bdqn.services;

import com.bdqn.entity.Bill;

import java.util.List;

public interface BillDaoservice {
    List<Bill> getAll();

    List<Bill> selectInfo(Bill bill);

    int addInfo(Bill bill);

    Bill userInfo(int id);

    int updateInfo(Bill bill);

    int delInfo(int id);

}
