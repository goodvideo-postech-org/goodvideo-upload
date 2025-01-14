package com.goodvideo.upload.usecase;

import com.goodvideo.upload.domains.UsuarioToken;

public interface ValidarToken {

  UsuarioToken executar(String auth);
  
}
