package com.example.holidaywork.controller;


import com.example.holidaywork.pojo.ResultInfo;
import com.example.holidaywork.pojo.User;
import com.example.holidaywork.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Api(tags="用户控制器")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public ResultInfo login(@ApiParam("登录信息") User user,
                            @ApiParam("验证码") String check,
                            HttpSession session){
        //封装结果集
        ResultInfo info = new ResultInfo();

        String checkCode = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
        //验证码正确，则开始验证用户名、密码
        if(check.equalsIgnoreCase(checkCode)){
            User login = userService.login(user);

            //返回null说明不存在该用户
            if(login == null){
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误!");
            }else{
                //判断账户是否激活
                if(login.getStatus().equals("Y")){
                    //账户已激活
                    info.setFlag(true);
                    //将账户信息存到session中
                    session.setAttribute("user",login);
                }else{
                    //账户未激活
                    info.setFlag(false);
                    info.setErrorMsg("用户未激活!");
                }
            }
        }else{
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
        }
        //销毁验证码，避免重复提交
        session.removeAttribute("KAPTCHA_SESSION_KEY");

        return info;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public ResultInfo register(@ApiParam("用户信息") User user,
                               @ApiParam("验证码") String check,
                               HttpSession session){
        //获取验证码
        String checkCode = (String) session.getAttribute("KAPTCHA_SESSION_KEY");

        //结果集
        ResultInfo info = new ResultInfo();
        //如果验证码正确
        if(check.equalsIgnoreCase(checkCode)){
            //尝试注册
            boolean flag = userService.register(user);
            info.setFlag(flag);
            if(!flag)info.setErrorMsg("注册失败！");
        }else{
            //如果验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
        }

        //销毁验证码，避免重复提交
        session.removeAttribute("KAPTCHA_SESSION_KEY");

        return info;
    }

    @GetMapping("/active/{code}")
    @ApiOperation("激活账号")
    public void active(@ApiParam("验证码")@PathVariable String code, HttpServletResponse response) throws IOException {
        //激活
        boolean flag = userService.active(code);

        String msg;
        response.setContentType("text/html;charset=utf-8");
        if(flag){
            msg = "激活成功，请<a href='http://localhost:8080/login.html'>登录</a>";
        }else{
            msg = "激活失败！请确认激活码";
        }
        response.getWriter().write(msg);
    }

    @GetMapping("/username")
    @ApiOperation("获取当前用户的用户名")
    public Object username(HttpSession session){
        return session.getAttribute("user");
    }

    @GetMapping("/logout")
    @ApiOperation("注销")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        System.out.println(request.getContextPath());
        response.sendRedirect("http://localhost:8080/login.html");
    }
}

