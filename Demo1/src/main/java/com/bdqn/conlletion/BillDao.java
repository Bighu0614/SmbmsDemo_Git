package com.bdqn.conlletion;

import com.bdqn.entity.Bill;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BillDao {
    List<Bill> getAll();

    List<Bill> selectInfo(Bill bill);

    int addInfo(Bill bill);

    Bill userInfo(int id);

    int updateInfo(Bill bill);

    int delInfo(int id);

}
