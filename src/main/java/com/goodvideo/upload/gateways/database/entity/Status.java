package com.goodvideo.upload.gateways.database.entity;

import lombok.Getter;

@Getter
public enum Status {

    PROCESSANDO,
    CONCLUIDO,
    ERRO,
    EXPIRADO;

    public static Status getByString(final String status){
        return Status.valueOf(status);
    }

}
