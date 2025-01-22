package com.goodvideo.upload.gateways.database;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.ProcessamentoEntity;
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
    return CollectionUtils.emptyIfNull(processamentoRepository.getByIdUsuario(idUsuario)).stream()
        .map(ProcessamentoEntity::toDomain).collect(Collectors.toList());
  }

}
