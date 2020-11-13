package com.masluck.task;

import com.masluck.task.model.User;
import com.masluck.task.service.RestTemplateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);

        RestTemplateService restTemplateService = new RestTemplateService();

        User user = new User(3L, "James", "Brown", (byte) 35);

        //получение Id
        restTemplateService.setSessionId();
        //пункт 3, добавление пользователя ("5ebfeb")
        restTemplateService.addUser(user);

        //пункт 4, обновление пользователя ("e7cb97")
        user.setName("Thomas");
        user.setLastName("Shelby");
        restTemplateService.updateUser(user);

        //пункт 5, удаление пользователя ("5dfcf9")
        restTemplateService.deleteUser(3L);
    }
}
