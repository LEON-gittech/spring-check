package com.example.springcheck.token;

import com.example.springcheck.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
* @Title: TokenService.java
* @author MRC
* @date 2019年5月27日 下午5:40:25 
* @version V1.0
 */
@Service("TokenService")
public class TokenService {

    public String getToken(User user) {
        String secret = "my1231231231231231231231312313112312313131312321312331";
        String token = Jwts.builder()
                // 添加自定义数据
                .claim("type", user.getType().toString())
                .claim("password",user.getPassword())
                // 添加默认数据
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//24小时有效时间
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        return token;
    }
}