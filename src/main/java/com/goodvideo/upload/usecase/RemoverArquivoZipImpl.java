package com.goodvideo.upload.usecase;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.Status;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RemoverArquivoZipImpl implements RemoverArquivosZip {

  private final ProcessamentoDatabaseGateway processamentoDatabaseGateway;
  
  private final AmazonS3 amazonS3;

  @Value("${aws.bucket}")
  private String bucketName;
  
  @Scheduled(cron = "0 * * * * *") 
  @Override
  public void remover() {
    
    final List<Processamento> obterProcessamentosDataLimite = processamentoDatabaseGateway.obterProcessamentosDataLimite(LocalDateTime.now().minusMinutes(10));

    obterProcessamentosDataLimite.stream().forEach(processamento -> {
      amazonS3.deleteObject(new DeleteObjectRequest(bucketName, String.format("%s/%s/frames.zip", processamento.getIdUsuario(), processamento.getId())));
      
      processamento.setStatus(Status.EXPIRADO);
      processamentoDatabaseGateway.salvar(processamento);
    });
    
  }

}
