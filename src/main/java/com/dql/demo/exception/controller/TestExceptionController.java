package com.dql.demo.exception.controller;

import com.dql.demo.exception.JsonException;
import com.dql.demo.exception.PageException;
import com.dql.demo.exception.constant.Status;
import com.dql.demo.exception.model.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestExceptionController {

    @GetMapping("json")
    @ResponseBody
    public ApiResponse getjson(){
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("page")
    public ModelAndView getPage(){
        throw new PageException(Status.UNKNOWN_ERROR);
    }
}
