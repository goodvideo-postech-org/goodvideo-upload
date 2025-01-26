package com.goodvideo.upload.usecase;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoStatus;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.Status;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AtualizarStatusProcessamentoImpl implements AtualizarStatusProcessamento {

  private final EnviarEmail enviarEmail;
  private final ProcessamentoDatabaseGateway processamentoDatabaseGateway;

  @Value("${upload.baseUrl}")
  private String baseUrl;
  
  @Override
  public void atualizar(ProcessamentoStatus processamentoStatus) {
    final Processamento processamento = processamentoDatabaseGateway.obterPorId(processamentoStatus.getIdVideo());

    processamento.setStatus(Status.getByString(processamentoStatus.getStatus()));
    processamento.setDiretorioZip(String.format("%s/%s", baseUrl, processamentoStatus.getDiretorioZip()));
    processamento.setDataModificacao(LocalDateTime.now());
    
    processamentoDatabaseGateway.salvar(processamento);
      
    if ("ERRO".equalsIgnoreCase(processamentoStatus.getStatus())) {
      enviarEmail.enviar(processamento.getId(), processamento.getEmail());
    }
    
  }

}
