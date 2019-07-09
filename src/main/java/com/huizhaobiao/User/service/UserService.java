package com.huizhaobiao.User.service;

import com.huizhaobiao.User.common.IdWorker;
import com.huizhaobiao.User.dao.UserDao;
import com.huizhaobiao.User.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    /*
    * 查询全部标签
    * */
    public List<User> findAll()
    {

        return  userDao.findAll();
    }

    /*
     * 根据姓名查询
     * */
    public User findById(String id)
    {

        return  userDao.findById(id).get();
    }
    /*
     *添加标签
     * */
    public void add(User user)
    {
        user.setId(idWorker.nextId()+"");
        userDao.save(user);
    }
    /**
     * 修改标签
     *
     */
    public void update(User user){
        userDao.save(user);
    }
    /**
     * 删除标签
     *
     */
    public void deleteById(String id){
        userDao.deleteById(id);
    }

}
