package com.luck.dessert.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangtingting
 * @date 2023/11/16
 */
@RestController
public class DashboardController {

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello, big Clever";
    }
}
