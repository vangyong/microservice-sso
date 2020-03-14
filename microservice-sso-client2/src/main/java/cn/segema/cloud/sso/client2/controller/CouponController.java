package cn.segema.cloud.sso.client2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/coupon")
public class CouponController {

    @ApiOperation(value = "优惠券列表", notes = "优惠券列表")
    @GetMapping("/list")
    public ResponseEntity test1() {
        List<Map> couponList = new ArrayList<Map>();
        Map<String, Object> coupon1 = new HashMap<String,Object>();
        coupon1.put("user1","value1");
        coupon1.put("user2","value2");
        couponList.add(coupon1);
        Map<String, Object> coupon2 = new HashMap<String,Object>();
        coupon2.put("test1","value1");
        coupon2.put("test2","value2");
        couponList.add(coupon2);
        return new ResponseEntity(couponList, HttpStatus.OK);
    }

}
