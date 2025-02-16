package com.goodvideo.upload.gateways.database.repositories;

import com.goodvideo.upload.gateways.database.entity.ProcessamentoEntity;
import com.goodvideo.upload.gateways.database.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProcessamentoRepository extends JpaRepository<ProcessamentoEntity, UUID> {
  
  List<ProcessamentoEntity> getByIdUsuarioAndStatusNot(final String idUsuario, final Status status);
  
  List<ProcessamentoEntity> findByDataModificacaoLessThan(final LocalDateTime localDateTime);
  
}
