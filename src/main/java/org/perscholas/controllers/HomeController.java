package org.perscholas.controllers;

import lombok.AllArgsConstructor;
import org.perscholas.util.InitializerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    /*
            - controllers should be separated e.g. @RequestMapping("admin"), @RequestMapping("student")
            - provide as much as possible e.g. get/post/put/delete mappings
     */

    public HomeController(InitializerUtil initializerUtil) {
        this.initializerUtil = initializerUtil;
    }

    InitializerUtil initializerUtil;
    boolean initializeData = true;

    @GetMapping("/")
    public String index() {
        if (initializeData) {
            initializerUtil.initializeData();
        }
        initializeData = false;
        System.out.println("inside index action");
        return "index";
    }

    @GetMapping("/template")
    public String template(){
        return "template";
    }

}
