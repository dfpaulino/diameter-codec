package org.example.diameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

public class ApplicationClient {

    public static void main(String[] args) throws IOException {

        EchoClient.start();
    }

}
