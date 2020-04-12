package com.aws.codestar.projecttemplates.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping(path = "${base.url}")
public class HelloWorldController {

    private final String siteName;

    public HelloWorldController(final String siteName) {
        this.siteName = siteName;
    }

//    @GetMapping("/")
//    public ModelAndView helloWorld() {
//        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("siteName", this.siteName);
//        return mav;
//    }

    @GetMapping
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("index-test");
        mav.addObject("siteName", this.siteName);
        return mav;
    }

}
