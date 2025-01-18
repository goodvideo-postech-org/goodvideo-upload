package com.goodvideo.upload.gateways.api;

import java.io.IOException;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.usecase.ProcessamentoVideo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("processamento")
@RequiredArgsConstructor
public class ProcessamentoController {

  private final ProcessamentoVideo processamentoVideo;

  @PostMapping(path = "/upload",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> upload(
      @RequestHeader(name = "authorization") String auth,
      @RequestPart("file") MultipartFile file) throws IOException {
    return ResponseEntity.ok(
          processamentoVideo.execute(auth, ProcessamentoRequisicao.builder()
                .video(file)
                .fileName(file.getOriginalFilename())
                .email("bridi@bridi.com")
                .idUsuario(UUID.randomUUID().toString())
              .build()));
  }
}
