package com.goodvideo.upload.domains;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessamentoStatus implements Serializable {

  private static final long serialVersionUID = 5674549495425097318L;
  private String idVideo;
  private String diretorioZip;
  private String status;
  
}
