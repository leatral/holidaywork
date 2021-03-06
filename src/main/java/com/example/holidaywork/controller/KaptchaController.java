package com.example.holidaywork.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@Api(tags="验证码控制器")
public class KaptchaController {
    @Autowired
    private Producer kaptchaProduce;

    @GetMapping("/checkCode")
    @ApiOperation("生成验证码")
    //生成验证码
    public void getKaptcha(HttpServletResponse response, HttpSession session) throws IOException { //返回给浏览器的是图片，不是html，需要用response
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String text = kaptchaProduce.createText();
        BufferedImage image = kaptchaProduce.createImage(text);

        //将验证码存入session
        session.setAttribute("KAPTCHA_SESSION_KEY",text);

        //向客户端写出
        BufferedImage bi = kaptchaProduce.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

    }
}
