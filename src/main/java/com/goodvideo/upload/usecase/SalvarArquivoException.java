package com.goodvideo.upload.usecase;

public class SalvarArquivoException extends RuntimeException {

  private static final long serialVersionUID = -3491980340387469069L;

  public SalvarArquivoException(String message) {
    super(message);
  }

  public SalvarArquivoException(String message, Throwable e) {
    super(message, e);
  }

  
}
