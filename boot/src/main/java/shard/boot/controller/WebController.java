package shard.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    @RequestMapping(value = "/api")
    public ModelAndView sysApi(HttpServletRequest request) {
        return new ModelAndView("api");
    }
}
