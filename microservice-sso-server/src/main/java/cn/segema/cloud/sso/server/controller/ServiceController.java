package cn.segema.cloud.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.sso.server.domain.OauthClientDetails;
import cn.segema.cloud.sso.server.repository.OauthClientDetailsRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepository;

	@ApiOperation(value = "应用注册", notes = "应用注册")
	@ApiImplicitParams({@ApiImplicitParam(name = "client_id", value = "客户端id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
		@ApiImplicitParam(name = "webRedirectUrl", value = "重定向URL", required = true, paramType = "query")})
	@PostMapping("/register")
    public OauthClientDetails register(@RequestParam String client_id, 
			@RequestParam String password, 
			@RequestParam String webRedirectUrl){
		OauthClientDetails oauthClientDetails = new OauthClientDetails();
		oauthClientDetails.setClient_id(client_id);
		oauthClientDetails.setResource_ids(null);
		oauthClientDetails.setClient_secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password));
		//oauthClientDetails.setClient_secret(new BCryptPasswordEncoder().encode(password));
		oauthClientDetails.setScope("app");
		oauthClientDetails.setAuthorized_grant_types("authorization_code");
		oauthClientDetails.setWeb_server_redirect_uri(webRedirectUrl);
		oauthClientDetails.setAuthorities(null);
		oauthClientDetails.setAccess_token_validity(null);
		oauthClientDetails.setRefresh_token_validity(null);
		oauthClientDetails.setAdditional_information(null);
		oauthClientDetails.setAutoapprove(null);
		oauthClientDetails.setAutoapprove("true");
		oauthClientDetailsRepository.save(oauthClientDetails);
        return oauthClientDetails;
    }
}