package com.bdqn.services;

import com.bdqn.entity.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> getAll();

    List<Provider> selectInfo(String code,String name);

    int addInfo(Provider provider);

    Provider userInfo(int id);

    int updateInfo(Provider provider);

    int delInfo(int id);
}
