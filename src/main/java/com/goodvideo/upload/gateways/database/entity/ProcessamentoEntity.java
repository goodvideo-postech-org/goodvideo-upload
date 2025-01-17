package com.goodvideo.upload.gateways.database.entity;

import com.goodvideo.upload.domains.Processamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime dataCriacao;

    public ProcessamentoEntity(final Processamento processamento){
        id = UUID.fromString(processamento.getId());
        idUsuario = processamento.getIdUsuario();
        email = processamento.getEmail();
        status = processamento.getStatus();
        diretorio = processamento.getDiretorio();
        dataCriacao = processamento.getDataCriacao();
    }

    public Processamento toDomain(){
        return Processamento.builder()
                .id(id.toString())
                .idUsuario(idUsuario)
                .email(email)
                .diretorio(diretorio)
                .status(status)
                .dataCriacao(dataCriacao)
            .build();
    }

}
