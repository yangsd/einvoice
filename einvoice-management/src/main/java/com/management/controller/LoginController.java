package com.management.controller;

import com.management.commons.base.BaseController;
import com.management.commons.utils.DigestUtils;
import com.management.commons.utils.StringUtils;
import com.management.util.RandomKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 * @description：登录退出
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/index";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        return "index";
    }

    /**
     * GET 登录
     * @return {String}
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        LOGGER.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String username, String password) {
        LOGGER.info("POST请求登录");

        if (StringUtils.isBlank(username)) {
            return renderError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("密码不能为空");
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        // 默认设置为记住密码，你可以自己在表单中加一个参数来控制
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            LOGGER.error("账号不存在！", e);
            return renderError("账号不存在");
        } catch (DisabledAccountException e) {
            LOGGER.error("账号未启用！", e);
            return renderError("账号未启用");
        } catch (IncorrectCredentialsException e) {
            LOGGER.error("密码错误！", e);
            return renderError("密码错误");
        } catch (RuntimeException e) {
            LOGGER.error("未知错误,请联系管理员！", e);
            return renderError("未知错误,请联系管理员");
        }
        return renderSuccess();
    }

    /**
     * 未授权
     * @return {String}
     */
    @RequestMapping(value = "unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 退出
     * @return {Result}
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public Object logout() {
        LOGGER.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return renderSuccess();
    }


    @RequestMapping(value = "/randomnum", method = RequestMethod.GET)
    public @ResponseBody
    String randomid(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int width = 80;

        int height = 20;

        int codeCount = 4;

        int fontHeight = height - 2;

        int xx = width / (codeCount + 1);

        int codeY = height - 4;

        String randomCode = RandomKey.getRandomKey(4);

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D gra = image.createGraphics();

        gra.setColor(Color.WHITE);
        gra.fillRect(0, 0, width, height);

        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);

        gra.setFont(font);

        gra.setColor(Color.BLACK);

        Random random = new Random();

        gra.setColor(Color.BLACK);
        int red = 0, green = 0, blue = 0;
        char c;

        float colorDeep = 2;
        int rgbColorMax = Float.valueOf(255 / colorDeep).intValue();
        for (int i = 0; i < 4; i++) {
            c = randomCode.charAt(i);
            red = random.nextInt(rgbColorMax);
            green = random.nextInt(rgbColorMax);
            blue = random.nextInt(rgbColorMax);

            gra.setColor(new Color(red, green, blue));
            gra.drawString("" + c, (i + 1) * xx, codeY);
        }

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.close();

        return null;
    }

}
