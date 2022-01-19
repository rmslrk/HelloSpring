package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.messaging.saaj.util.Base64;

import exception.BaseException;
import exception.ErrorMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import domain.UserDTO;
import org.springframework.stereotype.Service;
import repository.UserMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    @Autowired
    private UserMapper userMapper;

    // 토큰 생성
    public String createToken(Long uid, String nickname) {
        String key = userMapper.getSaltToUid(uid);

        // key가 비어있으면 오류 출력
        if (key == null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        // payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("uid", uid);
        payloads.put("nickname", nickname);

        // 24시간 유효시간 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        Date exp = calendar.getTime();

        // 토큰 Builder 후 return
        return Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("auth-user") // 토큰 용도
                .setExpiration(exp) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성
    }

    // 토큰 검증
    public Map<String, Object> isValid(String token) throws Exception {
        Map<String, Object> result = null;

        Map<String, Object> payloads = this.validateFormat(token);
        long uid = Long.parseLong(payloads.get("uid").toString());

        String key = userMapper.getSaltToUid(uid);

        token = token.substring(7); // "Bearer " 제거

        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes("UTF-8")) // Set Key
                .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                .getBody();

        result = claims;
        return result;
    }

    // 토큰의 Payload를 decode 해서 반환
    public Map<String, Object> validateFormat(String token) throws Exception {
        if (token.chars().filter(c -> c == '.').count() != 2)
            throw new Exception("유효하지 않은 토큰입니다.");

        Map<String, Object> map;
        map = new ObjectMapper().readValue(Base64.base64Decode(token.split("\\.")[1]), Map.class);
        if (map.get("uid") == null || map.get("nickname") == null)
            throw new Exception("유효하지 않은 토큰입니다.");

        return map;
    }
}

