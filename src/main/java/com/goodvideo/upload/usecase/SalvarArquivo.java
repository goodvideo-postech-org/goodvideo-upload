package com.goodvideo.upload.usecase;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface SalvarArquivo {

  String executar(MultipartFile arquivo, String idUsuario);
  
}
