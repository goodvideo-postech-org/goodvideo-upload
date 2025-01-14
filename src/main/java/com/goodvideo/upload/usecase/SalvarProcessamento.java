package com.goodvideo.upload.usecase;

import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;

public interface SalvarProcessamento {

  Processamento executar(final String idVideo, final ProcessamentoRequisicao processamentoRequisicao);
}
