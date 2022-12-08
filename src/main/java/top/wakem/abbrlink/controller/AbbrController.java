package top.wakem.abbrlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.request.LinkRequest;
import top.wakem.abbrlink.common.response.BaseResponse;
import top.wakem.abbrlink.common.response.ExtraResponse;
import top.wakem.abbrlink.service.AbbrService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
public class AbbrController {

    @Resource
    AbbrService abbrService;

    @PutMapping("/link")
    @ResponseBody
    public BaseResponse<String> addLink(@RequestBody LinkRequest linkRequest) {
        return abbrService.addLink(linkRequest.getLink(), linkRequest.getExpireDays());
    }

    @PostMapping("/qrcode")
    @ResponseBody
    public BaseResponse<String> qrCode(@RequestBody Map<String, String> mp) {
        return abbrService.getQRCode(mp.get("content"));
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
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

    @RequestMapping("/favicon.ico")
    String favicon() {
        return "forward:/static/favicon.ico";
    }


    }
