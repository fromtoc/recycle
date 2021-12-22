package com.coderman.controller.system;

import com.coderman.common.response.ActiveUser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TokenController {

    @Value("${jwtKey:215a0f92-c206-4bd9-9004-e7b9e295f1f3}")
    private String key;

    @Value("${jwtUrl:http://211.23.139.11:8081}")
    private String headerUrl;
    //签名密钥：从衡石系统JWT认证方式中生成并保持一致
//    private static final String key = "215a0f92-c206-4bd9-9004-e7b9e295f1f3";

    @RequestMapping("/getTokenUrl")
    public Map<String,Object> token() throws Exception{
        Map<String,Object> map = null;
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            String username = activeUser.getUser().getUsername();
            //根据不同角色信息分配对应权限
            List<String> roles = new ArrayList<>();
//            if ("admin".equals(username)) {
//                roles.add("system admin");//系统管理员
//                roles.add("data admin");//数据管理员
//                roles.add("data analyst");//数据分析员
//                roles.add("api admin");//API管理员
//            }
            roles.add("system admin");//系统管理员
            roles.add("data admin");//数据管理员
            roles.add("data analyst");//数据分析员
            roles.add("api admin");//API管理员
            roles.add("data viewer");//数据查看员

            //生成时间段，用于指定token过期时间
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.HOUR, 60);
            //
            JWSSigner signer = new MACSigner(key);
            //payload部分
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer("https://c2id.com")
                    .claim("roles", roles)
                    .claim("loginName", username)
                    .expirationTime(instance.getTime()) //指定令牌过期时间
                    .build();
            //设置header部分，算法与后台验签算法一致
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            //signature部分
            signedJWT.sign(signer);

            /**
             * 拼接url
             * http://{host}:{port}?activeAuth=jwt-param&jwtParam=通过JWT生成的token
             */
//            String headerUrl = "http://219.142.241.218:20588";
            String token = signedJWT.serialize();
//            String url = headerUrl + "?activeAuth=jwt-param&jwtParam=" + token;
            System.out.println(token);
            map = new HashMap<>();
            map.put("state",true);
            map.put("token",token);
            map.put("headerUrl",headerUrl);
        } catch (JOSEException e) {
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
