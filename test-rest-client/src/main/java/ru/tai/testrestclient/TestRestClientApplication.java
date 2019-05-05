package ru.tai.testrestclient;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.tai.testrestclient.model.User;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TestRestClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestRestClientApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8, MediaType.ALL));
//        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);

        String helloUrl = "http://localhost:8080/rest/hello";
        Hello responseHelloUrl = restTemplate.getForObject(helloUrl, Hello.class);
//        ResponseEntity<Hello> response = restTemplate.getForEntity(resourceUrl, Hello.class);
        System.out.println(responseHelloUrl.getContent());


        String usersUrl = "http://localhost:8080/rest/users";
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(usersUrl, User[].class);
        User[] users = responseEntity.getBody();


//        Arrays.stream(users).forEach(System.out::println);

        System.out.println("-----------------------------------------------");
        for (User u: users) {
            System.out.println("Пользовтель: " + u);
            System.out.println("Роли: " + u.getRoles());
            System.out.println("Сообщения: ");
            u.getMessages().stream().forEach(System.out::println);
            System.out.println("-----------------------------------------------");
        }

    }
}
