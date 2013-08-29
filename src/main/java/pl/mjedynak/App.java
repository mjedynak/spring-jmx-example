package pl.mjedynak;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext(AppConfig.class);
        System.in.read();
    }
}
