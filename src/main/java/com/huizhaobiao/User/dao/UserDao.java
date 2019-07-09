package com.huizhaobiao.User.dao;

import com.huizhaobiao.User.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {


    public  User findByUsername(String username);
    public  User deleteByUsername(String username);
}
