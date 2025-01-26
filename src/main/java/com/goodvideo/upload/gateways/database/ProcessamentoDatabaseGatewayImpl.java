package com.goodvideo.upload.gateways.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.ProcessamentoEntity;
import com.goodvideo.upload.gateways.database.entity.Status;
import com.goodvideo.upload.gateways.database.repositories.ProcessamentoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessamentoDatabaseGatewayImpl implements ProcessamentoDatabaseGateway {

  private final ProcessamentoRepository processamentoRepository;

  @Override
  public Processamento salvar(Processamento processamento) {
    return processamentoRepository.save(new ProcessamentoEntity(processamento)).toDomain();
  }

  @Override
  public List<Processamento> obterProcessamentosPorIdUsuario(String idUsuario) {
    return CollectionUtils.emptyIfNull(processamentoRepository.getByIdUsuarioAndStatusNot(idUsuario, Status.EXPIRADO)).stream()
        .map(ProcessamentoEntity::toDomain).collect(Collectors.toList());
  }

  @Override
  public Processamento obterPorId(String idProcessamento) {
    Optional<ProcessamentoEntity> byId =
        processamentoRepository.findById(UUID.fromString(idProcessamento));

    if (byId.isPresent()) {
      return byId.get().toDomain();
    }
    return null;
  }

  @Override
  public List<Processamento> obterProcessamentosDataLimite(LocalDateTime localDateTime) {
    final List<ProcessamentoEntity> byDataModificacaoLessThen = processamentoRepository.findByDataModificacaoLessThan(localDateTime);
    return byDataModificacaoLessThen.stream().map(ProcessamentoEntity::toDomain).toList();
  }

}
