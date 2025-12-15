package com.t3h.projectclothes.utils;

import java.util.Random;

public class GenerateCode {

  public static String generateCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 7; i++) {
      sb.append(characters.charAt(random.nextInt(characters.length())));
    }
    return sb.toString();
  }
}
