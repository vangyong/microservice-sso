package cn.segema.cloud.sso.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/test/server")
    public String test1(){
        return "server";
    }
	

}
