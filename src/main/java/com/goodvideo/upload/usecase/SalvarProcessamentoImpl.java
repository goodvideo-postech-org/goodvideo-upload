package com.goodvideo.upload.usecase;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.Status;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalvarProcessamentoImpl implements SalvarProcessamento {

  
  private final ProcessamentoDatabaseGateway processamentoDatabaseGateway;
  
  @Override
  public Processamento executar(final String idVideo, final ProcessamentoRequisicao processamentoRequisicao) {
    final Processamento processamento = new Processamento();
    processamento.setId(idVideo);
    processamento.setStatus(Status.PROCESSANDO);
    processamento.setIdUsuario(processamentoRequisicao.getIdUsuario());
    processamento.setEmail(processamentoRequisicao.getEmail());
    processamento.setDiretorio(String.format("%s/%s.mp4", processamentoRequisicao.getIdUsuario(), idVideo));
    processamento.setDataCriacao(LocalDateTime.now());
    return processamentoDatabaseGateway.salvar(processamento);
  }

}
