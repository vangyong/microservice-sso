package cn.segema.cloud.sso.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/sso-server/test")
public class TestController {
	
    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping("/test1")
    public ResponseEntity test1() {
        return new ResponseEntity("test test1 success", HttpStatus.OK);
    }

}
