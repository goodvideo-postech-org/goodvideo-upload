package com.goodvideo.upload.domains;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessamentoRequisicao {

  public MultipartFile video;
  public String fileName;
  public String idUsuario;
  public String email;
  
  public void enriquecerUsuarioToken(UsuarioToken usuarioToken) {
    this.idUsuario = usuarioToken.getId();
    this.email = usuarioToken.getEmail();    
  }
  
}
