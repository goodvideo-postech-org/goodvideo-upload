package com.goodvideo.upload.usecase;

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.goodvideo.upload.domains.UsuarioToken;
import com.google.gson.Gson;


@Component
public class ValidarTokenImpl implements ValidarToken {

  @Override
  public UsuarioToken executar(String token) {
    try {
      final String[] split = token.split("Bearer ");
      
      final Algorithm algorithm = Algorithm.HMAC256("secret");
      final String subject = JWT.require(algorithm).build().verify(split[1].trim()).getSubject();

      return new Gson().fromJson(subject, UsuarioToken.class);
    } catch (JWTVerificationException e) {
      throw new TokenException("Invalid token");
    }
  }

}
