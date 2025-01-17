package com.goodvideo.upload.config.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;

@Configuration
@Getter
public class AWSClientConfig {

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretAccessKey}")
  private String secretAccessKey;

  @Value("${aws.bucketName}")
  private String bucketName;

  @Bean
  public AmazonS3 amazonS3() {
      BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);

      return AmazonS3ClientBuilder
              .standard()
              .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
              .withRegion("us-east-1")
              .build();
  }

  
}
