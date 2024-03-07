package com.example.demo;

import com.example.demo.model.DevProfile;
import com.example.demo.model.ProductionProfile;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContainersTestApplication {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    @Container
    private static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
     void testDevContainer() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(DevProfile.msg, forEntity.getBody());
    }

    @Test
     void testProdContainer() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(ProductionProfile.msg, forEntity.getBody());
    }
}
