package com.glyfly.main.controller;

import com.glyfly.main.model.Response;
import com.glyfly.main.model.UserModel;
import com.jfinal.core.Controller;
import com.mysql.jdbc.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public class UserController extends Controller {

    public void get(){

        List<UserModel> list=UserModel.userModel.find("select * from USER");
        renderJson(list);
    }

    public void set(){
        String id = getPara("id");
        String name = getPara("name");
        String age = getPara("age");
        String phone = getPara("phone");
        UserModel userModel = new UserModel();
        userModel.set("id",id);
        userModel.set("name",name);
        userModel.set("age",age);
        userModel.set("phone",phone);
        userModel.save();
        renderJson("success");
    }

    public void login(){
        String username = getPara("username");
        String password = getPara("password");
        if (!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password)){
            List<UserModel> list = null;
            try {
                list = UserModel.userModel.find("select * from user where username = " + username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UserModel userModel;
            Response response = new Response();
            if (list == null || list.size() == 0) {
                response.setCode(1);
                response.setMsg("用户不存在！");
            } else {
                userModel = list.get(0);
                if (password.equals(userModel.get("password"))){
                    int login_times = userModel.get("login_times");
                    userModel.set("login_times",login_times + 1);
                    userModel.update();
                    response.setCode(0);
                    response.setMsg("登陆成功！");
                }else {
                    response.setCode(2);
                    response.setMsg("密码错误！");
                }
            }
                renderJson(response);
        }
    }

    public void register(){
        String username = getPara("username");
        String password = getPara("password");
        if (!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password)){
            List<UserModel> list = null;
            try {
                list = UserModel.userModel.find("select * from user where username = " + username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Response response = new Response();
            if (list == null || list.size() == 0) {
                UserModel userModel = new UserModel();
                userModel.set("username",username);
                userModel.set("password",password);
                userModel.save();
                renderJson("注册成功");
            } else {
                response.setCode(2);
                response.setMsg("用户已存在！");

            }
            renderJson(response);
        }
    }

}
