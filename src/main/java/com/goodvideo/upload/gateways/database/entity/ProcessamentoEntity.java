package com.goodvideo.upload.gateways.database.entity;

import com.goodvideo.upload.domains.Processamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "processamento")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessamentoEntity {

  @Id
  private UUID id;
  private String idUsuario;
  private String email;
  private String diretorio;
  private String diretorioZip;
  @Enumerated(EnumType.STRING)
  private Status status;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataModificacao;

  public ProcessamentoEntity(final Processamento processamento) {
    id = UUID.fromString(processamento.getId());
    idUsuario = processamento.getIdUsuario();
    email = processamento.getEmail();
    status = processamento.getStatus();
    diretorio = processamento.getDiretorio();
    diretorioZip = processamento.getDiretorioZip();
    dataCriacao = processamento.getDataCriacao();
    dataModificacao = processamento.getDataModificacao();
  }

  public Processamento toDomain() {
    return Processamento.builder().id(id.toString()).idUsuario(idUsuario).email(email)
        .diretorio(diretorio).diretorioZip(diretorioZip).status(status).dataCriacao(dataCriacao)
        .dataModificacao(dataModificacao)
        .build();
  }

}
