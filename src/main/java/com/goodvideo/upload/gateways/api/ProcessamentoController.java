package com.goodvideo.upload.gateways.api;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.gateways.api.response.ListagemProcessamentoResponse;
import com.goodvideo.upload.usecase.ListarProcessamentoVideo;
import com.goodvideo.upload.usecase.ProcessamentoVideo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("processamento")
@RequiredArgsConstructor
public class ProcessamentoController {

  private final ProcessamentoVideo processamentoVideo;
  private final ListarProcessamentoVideo listarProcessamentoVideo;

  @PostMapping(path = "/upload",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> upload(
      @RequestHeader(name = "authorization") String auth,
      @RequestPart("file") MultipartFile file) throws IOException {
    return ResponseEntity.ok(
          processamentoVideo.execute(auth, ProcessamentoRequisicao.builder()
                .video(file)
                .fileName(file.getOriginalFilename())
                .idUsuario(UUID.randomUUID().toString())
              .build()));
  }
  
  @PostMapping(path = "/listar")
  public ResponseEntity<List<ListagemProcessamentoResponse>> listar(
      @RequestHeader(name = "authorization") String auth) throws IOException {
    return ResponseEntity.ok( 
          listarProcessamentoVideo.listar(auth).stream().map(ListagemProcessamentoResponse::new).collect(Collectors.toList()));
  }
}
