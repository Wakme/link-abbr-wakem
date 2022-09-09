package top.wakem.abbrlink.controller;

import freemarker.template.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.request.LinkRequest;
import top.wakem.abbrlink.common.response.BaseResponse;
import top.wakem.abbrlink.service.AbbrService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
public class AbbrController {

    public static final String PREFIX = "https://wakem.top/s/";

    @Resource
    AbbrService abbrService;

    @PostMapping("/link")
    @ResponseBody
    public BaseResponse<String> addLink(@RequestBody LinkRequest linkRequest) {
        String abbr = abbrService.addLink(linkRequest.getLink(), linkRequest.getExpireDays());
        return BaseResponse.success(abbr);
    }

    @GetMapping("/ss")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ss");
        return mv;
    }

    @GetMapping("/{abbr}")
    public void redirect(HttpServletResponse response, @PathVariable String abbr) {
        String link = abbrService.getLink(abbr);
        try {
            response.sendRedirect(link);
        } catch (IOException e) {
            throw new BizException(BizExceptionEnum.SYSTEM_ERROR);
        }
    }

}
