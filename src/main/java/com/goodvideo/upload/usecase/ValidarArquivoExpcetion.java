package com.goodvideo.upload.usecase;

public class ValidarArquivoExpcetion extends RuntimeException {

  private static final long serialVersionUID = -6657478328748116194L;
  
  public ValidarArquivoExpcetion(String message) {
    super(message);
  }

  public ValidarArquivoExpcetion(String message, Throwable e) {
    super(message, e);
  } 
  

}
