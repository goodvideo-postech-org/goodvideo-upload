package com.goodvideo.upload.gateways.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goodvideo.upload.domains.Processamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListagemProcessamentoResponse {

  private String idProcessamento;
  private String linkDownload; 
  private String status;
  
  public ListagemProcessamentoResponse(final Processamento processamento) {
    this.idProcessamento = processamento.getId();
    this.linkDownload = processamento.getDiretorioZip();
    this.status = processamento.getStatus().toString();
  }
  
}
