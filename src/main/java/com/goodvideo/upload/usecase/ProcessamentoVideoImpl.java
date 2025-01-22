package com.goodvideo.upload.usecase;

import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.domains.UsuarioToken;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessamentoVideoImpl implements ProcessamentoVideo {

  private final SalvarProcessamento salvarProcessamento;
  private final SalvarArquivo salvarArquivo;
  private final EnviarParaProcessamento enviarParaProcessamento;
  private final ValidarToken validarToken;
  private final ValidarFormatoTamanho validarFormatoTamanho;

  @Override
  public String execute(final String auth, final ProcessamentoRequisicao processamentoRequisicao) {
    validarFormatoTamanho.validate(processamentoRequisicao.getVideo());
    
    final UsuarioToken usuario = validarToken.executar(auth);
    
    processamentoRequisicao.enriquecerUsuarioToken(usuario);
    
    final String idVideo = salvarArquivo.executar(processamentoRequisicao.getVideo(),
        processamentoRequisicao.getIdUsuario());
    final Processamento processamento =
        salvarProcessamento.executar(idVideo, processamentoRequisicao);
    enviarParaProcessamento.executar(processamento);
    return processamento.getId();
  }

}
