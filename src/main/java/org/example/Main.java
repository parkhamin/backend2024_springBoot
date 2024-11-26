package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Decorater 패턴-> 마치 원래 대상인 것처럼 행동하지만 실제로 그걸 받아가지고
// 보이지 않는 작업 후에 원래 대상에게 넘겨주는 패턴
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}