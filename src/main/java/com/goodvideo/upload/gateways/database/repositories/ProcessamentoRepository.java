package com.goodvideo.upload.gateways.database.repositories;

import com.goodvideo.upload.gateways.database.entity.ProcessamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ProcessamentoRepository extends JpaRepository<ProcessamentoEntity, UUID> {
  
  List<ProcessamentoEntity> getByIdUsuario(final String idUsuario);
  
}
