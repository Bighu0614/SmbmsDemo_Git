package com.bdqn.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static MybatisUtil myb;
    private static SqlSession session;
    private MybatisUtil(){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybats-cfg.xml");
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
            session = sf.openSession();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static MybatisUtil getInstance(){
        if (myb==null){
            myb = new MybatisUtil();
        }
        return myb;
    }

    public SqlSession getSession(){
        return session;
    }
}
