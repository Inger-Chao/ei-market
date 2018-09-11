package com.inger.market.controller;

import com.inger.market.ResultEnum;
import com.inger.market.domain.Result;
import com.inger.market.domain.User;
import com.inger.market.repository.UserRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by IngerJo on 2018/9/5.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user-list/{page}")
    public Result<List<User>> getAllUserList(@PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page,12);
        Page<User> users = userRepository.findAll(pageable);
        return ResultUtil.success(ResultEnum.GET_USER_INFO_LIST,users.getContent());
    }

     // 通过用户名获取用户的所有信息，在用户登录时调用
    @GetMapping(value = "user/info/{username}")
    public Result<User> getUserInfo(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        return ResultUtil.success(ResultEnum.GET_ONE_USER_INFO,user);
    }

    // 获取用户的所有信息，本项目中的使用：在物品详情页获取卖家信息
    @GetMapping(value = "user/id/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return ResultUtil.success(ResultEnum.GET_ONE_USER_INFO,user);
    }

//    用户注册
    @PostMapping(value = "/user/sign-up")
    public Result singUp(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(ResultEnum.USER_PARAMS_ERROR);
        }
        if (null != userRepository.findByUsername(user.getUsername())) {
            return ResultUtil.error(ResultEnum.USER_DUPLICATE);
        }
        userRepository.save(user);
        return ResultUtil.success(ResultEnum.SIGN_UP);
    }

    //用户登录
    @PostMapping(value = "user/sign-in")
    public Result SignIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("power") Integer power){
        User user = userRepository.findByUsername(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        if (!user.getPassword().equals(password)){
            return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
        }
        if (!user.getPower().equals(power)){
            return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
        }
        return ResultUtil.success(ResultEnum.SIGN_IN);
    }

    //修改用户个人信息
    @PostMapping(value = "/user/update/info")
    public Result updateUserByName( User user) {

        User oldUser = userRepository.findById(user.getId()).get();

        user.setPassword(oldUser.getPassword());
        userRepository.saveAndFlush(user);
        return ResultUtil.success(ResultEnum.UPDATE_USER);
    }

    @Transactional
    @PostMapping(value = "/user/update/credit/{id}/{credit}")
    public Result updateUserCredit(@PathVariable("id") Integer id,
                                   @PathVariable("credit") Float credit){

        userRepository.updateCredit(id,credit);
        return ResultUtil.success(ResultEnum.UPDATE_USER);
    }

    //修改密码
    @Transactional
    @PostMapping(value = "/user/update/password")
    public Result updatePassword(@RequestParam("username") String username,
                                 @RequestParam("oldpd") String oldpd,
                                 @RequestParam("password") String password){
        User user = userRepository.findByUsername(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }else {
            if (!user.getPassword().equals(oldpd)){
                return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
            }
            if (password.equals(user.getPassword())){
                return ResultUtil.error(ResultEnum.PASSWORD_NOT_CHANGE);
            }
            userRepository.changePasswordByUsername(username,password);
            return ResultUtil.success(ResultEnum.PASSWORD_UPDATED);
        }
    }

    @Transactional
    @PostMapping(value = "/user/delete/{username}")
    public Result deleteUserByUsername(@PathVariable("username") String username){

        User user = userRepository.findByUsername(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        userRepository.deleteById(user.getId());
        return ResultUtil.success(ResultEnum.DELETE_USER);
    }

}
