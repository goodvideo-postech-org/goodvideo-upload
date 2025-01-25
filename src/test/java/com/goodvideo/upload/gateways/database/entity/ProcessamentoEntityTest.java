package com.goodvideo.upload.gateways.database.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.Processamento;

@ExtendWith(MockitoExtension.class)
public class ProcessamentoEntityTest {

  @Test
  public void deveRetornarUmaEntity() {
    String id = UUID.randomUUID().toString();
    LocalDateTime dataCriacao = LocalDateTime.now();
    String diretorio = "/path/dir/video.mp4";
    String diretorioZip = "/path/dir/video.zip";
    String email = "a@a.com.br";
    Processamento p = Processamento.builder()
          .email(email)
          .id(id)
          .diretorio(diretorio)
          .diretorioZip(diretorioZip)
          .dataCriacao(dataCriacao)
          .status(Status.PROCESSANDO)        
        .build();
    
    ProcessamentoEntity processamentoEntity = new ProcessamentoEntity(p);
    assertEquals(email, processamentoEntity.getEmail());
    assertEquals(id, processamentoEntity.getId().toString());
    assertEquals(diretorio, processamentoEntity.getDiretorio());
    assertEquals(diretorioZip, processamentoEntity.getDiretorioZip());
    assertEquals(Status.PROCESSANDO, processamentoEntity.getStatus());
  }
  
  @Test
  public void deveRetornarDominio() {
    UUID id = UUID.randomUUID();
    String idUsuario = UUID.randomUUID().toString();
    String email = "a@a.com.br";
    LocalDateTime dataCriacao = LocalDateTime.now();
    String diretorio = "/path/dir/video.mp4";
    String diretorioZip = "/path/dir/video.zip";
    
    final ProcessamentoEntity processamentoEntity = new ProcessamentoEntity(id, idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    Processamento domain = processamentoEntity.toDomain();
    assertEquals(domain.getEmail(), email);
    assertEquals(domain.getDiretorio(), diretorio);
    assertEquals(domain.getDiretorioZip(), diretorioZip);
    assertEquals(domain.getIdUsuario(), idUsuario);
    assertEquals(domain.getId(), id.toString());
    assertEquals(domain.getStatus(), Status.PROCESSANDO);    
  }
  
  
}
