package cn.segema.cloud.sso.server.controller;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.sso.server.domain.User;
import cn.segema.cloud.sso.server.domain.OauthUserConnection;
import cn.segema.cloud.sso.server.repository.UserConnectionRepository;
import cn.segema.cloud.sso.server.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;

@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/sso-server/oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OauthController {
    
    private static Logger logger = LoggerFactory.getLogger(OauthController.class);
    
    private final AuthRequestFactory factory;
    
    @Autowired
    private UserConnectionRepository userConnectionRepository;
    
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "oauth登录", notes = "oauth登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "第三方类型", required = true, paramType = "path")})
    @GetMapping("/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @ApiOperation(value = "回调接口", notes = "回调接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "第三方类型", required = true, paramType = "path")})
    @RequestMapping("/callback/{type}")
    public AuthResponse login(@PathVariable String type, AuthCallback callback) {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        if(response!=null&& response.getCode()==2000) {
            AuthUser authUser = (AuthUser)response.getData();
            String providerId = String.valueOf(authUser.getSource());
            AuthToken token = (AuthToken)authUser.getToken();
            //将信息写入本地userConnection中
            OauthUserConnection userConnection = userConnectionRepository.findByOpenId(providerId,String.valueOf(token.getOpenId()));
            if(userConnection!=null) {
                userConnection.setAccessToken(String.valueOf(token.getAccessToken()));
                userConnection.setRefreshToken(String.valueOf(token.getRefreshToken()));
                userConnection.setExpireTime(new BigInteger(String.valueOf(token.getExpireIn())));
                userConnection.setNickName(String.valueOf(authUser.getNickname()));
                userConnection.setAvatarUrl(String.valueOf(authUser.getAvatar()));
                userConnectionRepository.save(userConnection);
            }else {
                userConnection = new OauthUserConnection();
                userConnection.setUserConnectionId(IdGeneratorUtil.generateSnowFlakeId());
                userConnection.setProviderId(providerId);
                userConnection.setOpenId(String.valueOf(token.getOpenId()));
                userConnection.setAccessToken(String.valueOf(token.getAccessToken()));
                userConnection.setRefreshToken(String.valueOf(token.getRefreshToken()));
                userConnection.setExpireTime(new BigInteger(String.valueOf(token.getExpireIn())));
                userConnection.setNickName(String.valueOf(authUser.getNickname()));
                userConnection.setAvatarUrl(String.valueOf(authUser.getAvatar()));
                
                User user = new User();
                user.setUserId(IdGeneratorUtil.generateSnowFlakeId());
                user.setNickName(String.valueOf(authUser.getNickname()));
                userConnection.setUserId(user.getUserId());
                userConnectionRepository.save(userConnection);
                userRepository.save(user);
            }
        }
        logger.info("【response】= {}", JSON.toJSONString(response));
        
        
        return response;
    }

}
