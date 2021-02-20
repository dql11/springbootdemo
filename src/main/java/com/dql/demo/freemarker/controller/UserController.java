package com.dql.demo.freemarker.controller;

import cn.hutool.core.util.ObjectUtil;
import com.dql.demo.freemarker.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@Controller
//@RequestMapping("user")
//@Slf4j
public class UserController {

    @PostMapping("/login")
    public ModelAndView index(User user,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        mv.addObject(user);
        request.getSession().setAttribute("user",user);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }
}
