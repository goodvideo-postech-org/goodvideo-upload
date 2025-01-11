package com.goodvideo.upload.usecase;

public class TokenException  extends RuntimeException {

  private static final long serialVersionUID = 3808195470359109510L;

  public TokenException(String message) {
    super(message);
  }

  public TokenException(String message, Throwable e) {
    super(message, e);
  }
  
}
