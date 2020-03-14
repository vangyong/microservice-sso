package cn.segema.cloud.sso.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.utils.HttpClientUtil;
import cn.segema.cloud.sso.server.domain.User;
import cn.segema.cloud.sso.server.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "社交登录")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/sso-server/social")
public class SocialController {
    private static Logger logger = LoggerFactory.getLogger(SocialController.class);

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据code登录", notes = "根据code登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "thirdPartyType", value = "第三方类型", required = true, paramType = "path"),
        @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "path")})
    @GetMapping("/openid/{thirdPartyType}/{code}")
    public ResponseEntity findByCode(@PathVariable String thirdPartyType, @PathVariable String code) {
        String appid = "wxf28aa7e9713e376c";
        String appsecret = "def459d127a23d82c30f0e9bfd2b08bd";
        // appid+appsecret+code 获取openid
        User user = userRepository.findByOpenId(thirdPartyType, "weixin89");
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/openid/{code}")
    public ResponseEntity findMiniByCode(@PathVariable String code) {
        String appid = "wxf28aa7e9713e376c";
        String appsecret = "def459d127a23d82c30f0e9bfd2b08bd";
        // appid+appsecret+code 获取openid
        Object userMap = HttpClientUtil.getInstance().httpGet(
            "https://api.weixin.qq.com/sns/jscode2session?appid=wxf28aa7e9713e376c&secret=def459d127a23d82c30f0e9bfd2b08bd&js_code="
                + code + "&grant_type=authorization_code");

        User user = null; // userRepository.findByOpenId(thirdPartyType,"weixin89");
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
