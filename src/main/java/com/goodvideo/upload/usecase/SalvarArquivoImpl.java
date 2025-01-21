package com.goodvideo.upload.usecase;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalvarArquivoImpl implements SalvarArquivo {

  private final AmazonS3 amazonS3;

  @Value("${aws.bucket}")
  private String bucketName;

  @Override
  public String executar(final MultipartFile arquivo, final String idUsuario) {
    try {
      final String idVideo = UUID.randomUUID().toString();

      ObjectMetadata data = new ObjectMetadata();
      data.setContentType(arquivo.getContentType());
      data.setContentLength(arquivo.getSize());
      
      amazonS3.putObject(new PutObjectRequest(bucketName,
          String.format("%s/%s/%s", idUsuario, idVideo, arquivo.getOriginalFilename()), arquivo.getInputStream(), data));

      return idVideo;

    } catch (Exception e) {
      throw new SalvarArquivoException(
          String.format("Falha ao converter arquivo, %s", e.getMessage()));
    }
  }

}
