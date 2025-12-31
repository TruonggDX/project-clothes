package com.t3h.projectclothes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectClothesApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProjectClothesApplication.class, args);
  }

}
