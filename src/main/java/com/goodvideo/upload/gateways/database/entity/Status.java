package com.goodvideo.upload.gateways.database.entity;

import lombok.Getter;

@Getter
public enum Status {

    PROCESSANDO,
    CONCLUIDO,
    ERRO;

    public Status getByString(final String status){
        return Status.valueOf(status);
    }

}
