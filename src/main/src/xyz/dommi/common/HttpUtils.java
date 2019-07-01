package xyz.dommi.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class HttpUtils {
    public static String TOKEN_COOKIE_NAME = "token";

    public static String getUserIdFromJwt(HttpServletRequest request) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(TOKEN_COOKIE_NAME))
                .findFirst();

        if(!cookie.isPresent())
            return null;

        // Get value of cookie
        String token = cookie.get().getValue();

        // Setup JWT
        Algorithm algorithm = Algorithm.HMAC256(EnvironmentConfig.getSecretKey());
        JWTVerifier verifier = JWT.require(algorithm)
                .withAudience("redhead")
                .build();

        // Decode JWT and return subject (= user ID)
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
}
