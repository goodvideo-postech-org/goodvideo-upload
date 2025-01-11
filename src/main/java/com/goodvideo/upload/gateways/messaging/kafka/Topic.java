package com.goodvideo.upload.gateways.messaging.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {

  PROCESSAMENTO_VIDEO("com.goodvideo.upload.processamento-video.v1");

  public String description;
}
