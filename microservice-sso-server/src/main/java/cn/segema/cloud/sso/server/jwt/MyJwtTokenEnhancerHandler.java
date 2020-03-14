package cn.segema.cloud.sso.server.jwt;

import java.util.HashMap;

import org.springframework.stereotype.Service;

/**
 * 拓展jwt token里面的信息
 */
@Service("jwtTokenEnhancerHandler")
public class MyJwtTokenEnhancerHandler implements JwtTokenEnhancerHandler {

    public HashMap<String, Object> getInfoToToken() {
        HashMap<String, Object> info = new HashMap<String, Object>();
        info.put("author", "wangyong");
        info.put("company", "www.segema.cn");
        return info;
    }
}
