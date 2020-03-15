package cn.segema.cloud.sso.server.controller;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.constants.CommonConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.sso.server.domain.MobileCode;
import cn.segema.cloud.sso.server.repository.MobileCodeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "手机验证码登录")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/sso-server/mobile")
public class MobileController {
    private static Logger logger = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private MobileCodeRepository mobileCodeRepository;
    

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @PostMapping("/code")
    public ResponseEntity findByCode(@RequestBody MobileCode mobileCode) {
        String mobileNumber = mobileCode.getMobileNumber();
        String code = String.valueOf((int)(Math.random() * 1000000));
        System.out.println("mobile: "+mobileCode.getMobileNumber()+" code:"+code);
        //生产环境变为调用发送短信接口
        MobileCode oldMobileCode = mobileCodeRepository.findByMobileNumber(mobileCode.getMobileNumber());
        if (oldMobileCode == null) {
            oldMobileCode = new MobileCode();
            oldMobileCode.setMobileCodeId(IdGeneratorUtil.generateSnowFlakeId());
        }
        oldMobileCode.setSmsTempId(mobileCode.getSmsTempId());
        oldMobileCode.setMobileNumber(mobileCode.getMobileNumber());
        oldMobileCode.setCode(code);
        oldMobileCode.setStatus(CommonConstant.MAGIC_TWO);
        oldMobileCode.setCreateTime(DateTimeUtil.getCurrentUnixTime());
        oldMobileCode.setSmsTempId(new BigInteger("1001"));
        mobileCodeRepository.save(oldMobileCode);
        
        return new ResponseEntity(oldMobileCode, HttpStatus.OK);
    }

}
