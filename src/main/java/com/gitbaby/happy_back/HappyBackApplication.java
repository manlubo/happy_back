package com.gitbaby.happy_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class HappyBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(HappyBackApplication.class, args);
  }

}
