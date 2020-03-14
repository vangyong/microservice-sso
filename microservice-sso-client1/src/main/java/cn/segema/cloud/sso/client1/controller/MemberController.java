package cn.segema.cloud.sso.client1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.sso.client1.domain.Member;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/member")
public class MemberController {

    @ApiOperation(value = "会员列表", notes = "会员列表")
    @RequestMapping("/list")
    public ResponseEntity list() {
        Member member = new Member();
        member.setName("苏九儿");
        member.setCode("1000");
        member.setMobile("13112345678");
        member.setGender(1);
        Member member1 = new Member();
        member1.setName("郭双");
        member1.setCode("1001");
        member1.setMobile("15812346723");
        member1.setGender(1);
        List<Member> list = new ArrayList<>();
        list.add(member);
        list.add(member1);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    
    @PreAuthorize("hasAuthority('memberExport')")
    @ResponseBody
    @RequestMapping("/export")
    public List<Member> export() {
        Member member = new Member();
        member.setName("苏九儿");
        member.setCode("1000");
        member.setMobile("13112345678");
        member.setGender(1);
        Member member1 = new Member();
        member1.setName("郭双");
        member1.setCode("1001");
        member1.setMobile("15812346723");
        member1.setGender(1);
        List<Member> list = new ArrayList<>();
        list.add(member);
        list.add(member1);
        return list;
    }

}
