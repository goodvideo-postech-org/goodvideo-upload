package com.goodvideo.upload.usecase;

import java.io.InputStream;

public interface SalvarArquivo {

  String executar(InputStream arquivo, String idUsuario);
  
}
