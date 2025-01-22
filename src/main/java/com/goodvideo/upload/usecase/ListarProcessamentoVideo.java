package com.goodvideo.upload.usecase;

import java.util.List;
import com.goodvideo.upload.domains.Processamento;

public interface ListarProcessamentoVideo {

  List<Processamento> listar(String auth);
  
}
