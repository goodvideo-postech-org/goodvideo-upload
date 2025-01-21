package com.goodvideo.upload.usecase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.goodvideo.upload.config.cloud.AWSClientConfig;
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
      File localFile = convertMultipartFileToFile(arquivo);

      amazonS3.putObject(new PutObjectRequest(bucketName,
          String.format("%s/%s/%s", idUsuario, idVideo, arquivo.getOriginalFilename()), localFile));

      return idVideo;

    } catch (Exception e) {
      throw new SalvarArquivoException(
          String.format("Falha ao converter arquivo, %s", e.getMessage()));
    }
  }

  private File convertMultipartFileToFile(MultipartFile file) {
    File convertedFile = new File(file.getOriginalFilename()); //NOSONAR
    try {
      Files.copy(file.getInputStream(), convertedFile.toPath(),
          StandardCopyOption.REPLACE_EXISTING);  //NOSONAR
    } catch (IOException e) {
      throw new RuntimeException(e);  //NOSONAR
    }
    return convertedFile;
  }

}
