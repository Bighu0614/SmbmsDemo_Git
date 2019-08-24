package com.bdqn.conlletion;

import com.bdqn.entity.Provider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProviderDao {
    List<Provider> getAll();

    List<Provider> selectInfo(@Param("code") String code,@Param("name") String name);

    int addInfo(Provider provider);

    Provider userInfo(int id);

    int updateInfo(Provider provider);

    int delInfo(int id);
}
