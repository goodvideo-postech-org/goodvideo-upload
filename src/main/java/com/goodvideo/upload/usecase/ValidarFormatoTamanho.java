package com.goodvideo.upload.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface ValidarFormatoTamanho {

  void validate(MultipartFile file);
  
  
}
