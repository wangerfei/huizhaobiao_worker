package com.huizhaobiao.User.contorller;

import com.huizhaobiao.User.common.Result;
import com.huizhaobiao.User.common.StatusCode;
import com.huizhaobiao.User.pojo.User;
import com.huizhaobiao.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserService userService;

    /**
     * 查询全部列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll(){
        return new Result<>(true,StatusCode.OK,"查询成功",userService.findAll() );
    }
    /**
     * 根据ID查询标签
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result<User> findById(@PathVariable String id){
        return new Result<User>(true, StatusCode.OK,"查询成功",userService.findById(id));
    }
    /**
     * 增加标签
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add( @RequestBody User user){
        userService.add(user);
        return new Result(true,StatusCode.OK,"增加成功");
    }
    /**
     * 修改标签
     */
    @RequestMapping(value="/{id}" ,method = RequestMethod.PUT)
    public Result update( @RequestBody User user,@PathVariable String
            id){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 删除标签
     */
    @RequestMapping(value="/{id}" ,method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


}
