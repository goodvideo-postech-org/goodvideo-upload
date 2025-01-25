package com.goodvideo.upload.gateways.database;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.database.entity.ProcessamentoEntity;
import com.goodvideo.upload.gateways.database.entity.Status;
import com.goodvideo.upload.gateways.database.repositories.ProcessamentoRepository;

@ExtendWith(MockitoExtension.class)
public class ProcessamentoDatabaseGatewayImplTest {

  @InjectMocks
  private ProcessamentoDatabaseGatewayImpl provider;
  
  @Mock
  private ProcessamentoRepository processamentoRepository;
  
  @Test
  public void deveObterProcessamentoPorUsuario() {
    UUID id = UUID.randomUUID();
    String idUsuario = UUID.randomUUID().toString();
    String email = "a@a.com.br";
    String diretorio = "/path/dir/video.mp4";
    String diretorioZip = "/path/dir/video.zip";
    
    final ProcessamentoEntity processamentoEntity = new ProcessamentoEntity(id, idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    
    when(processamentoRepository.getByIdUsuarioAndStatusNot(idUsuario, Status.EXPIRADO)).thenReturn(List.of(processamentoEntity));
    
    List<Processamento> obterProcessamentosPorIdUsuario = provider.obterProcessamentosPorIdUsuario(idUsuario);
    
    assertEquals(1, obterProcessamentosPorIdUsuario.size());
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getEmail(), email);
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getDiretorio(), diretorio);
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getDiretorioZip(), diretorioZip);
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getIdUsuario(), idUsuario);
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getId(), id.toString());
    assertEquals(obterProcessamentosPorIdUsuario.get(0).getStatus(), Status.PROCESSANDO);    
  }
  
  @Test
  public void deveSalvar() {
    UUID idDomain = UUID.randomUUID();
    String idUsuario = UUID.randomUUID().toString();
    String email = "a@a.com.br";
    String diretorio = "/path/dir/video.mp4";
    String diretorioZip = "/path/dir/video.zip";
    
    final Processamento processamento = new Processamento(idDomain.toString(), idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    
    final ProcessamentoEntity processamentoEntity = new ProcessamentoEntity(idDomain, idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    
    when(processamentoRepository.save(any())).thenReturn(processamentoEntity);
    
    provider.salvar(processamento);
    
    verify(processamentoRepository).save(any(ProcessamentoEntity.class));
  }
  
  @Test
  public void deveObterPorId() {
    UUID idDomain = UUID.randomUUID();
    String idUsuario = UUID.randomUUID().toString();
    String email = "a@a.com.br";
    String diretorio = "/path/dir/video.mp4";
    String diretorioZip = "/path/dir/video.zip";
    
    final Processamento processamento = new Processamento(idDomain.toString(), idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    
    final ProcessamentoEntity processamentoEntity = new ProcessamentoEntity(idDomain, idUsuario, email, diretorio, diretorioZip, Status.PROCESSANDO, LocalDateTime.now(), LocalDateTime.now());
    
    when(processamentoRepository.findById(idDomain)).thenReturn(Optional.of(processamentoEntity));
    
    provider.obterPorId(idDomain.toString());
    
    verify(processamentoRepository).findById(idDomain);
  }
  
  @Test
  public void deveObterPorIdENaoEncontrar() {
    UUID randomUUID = UUID.randomUUID();
    when(processamentoRepository.findById(randomUUID)).thenReturn(Optional.empty());
    
    Processamento obterPorId = provider.obterPorId(randomUUID.toString());
    
    verify(processamentoRepository).findById(any());
    assertNull(obterPorId);
  }
  
}
