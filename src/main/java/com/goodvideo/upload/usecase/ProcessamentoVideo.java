package com.goodvideo.upload.usecase;

import com.goodvideo.upload.domains.ProcessamentoRequisicao;

public interface ProcessamentoVideo {

  String execute(final String auth, final ProcessamentoRequisicao processamenoRequisicao);
  
}
