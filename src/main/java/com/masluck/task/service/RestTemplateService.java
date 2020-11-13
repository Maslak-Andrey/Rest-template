package com.masluck.task.service;

import com.masluck.task.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate = new RestTemplate();
    private String sessionId;
    private String code = "";
    private String url = "http://91.241.64.178:7081/api/users";
    private HttpHeaders headers = new HttpHeaders();

    public void setSessionId() {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        HttpHeaders headers = response.getHeaders();
        sessionId = headers.get(HttpHeaders.SET_COOKIE).get(0);
    }

    public void addUser(User user) {
        headers.add("Cookie", sessionId);
        HttpEntity<User> request = new HttpEntity(user, headers);
        code += restTemplate.exchange(url, HttpMethod.POST, request, String.class).getBody();
//        System.out.println(code);
    }

    public void updateUser(User user) {
        HttpEntity<User> request = new HttpEntity(user, headers);
        code += restTemplate.exchange(url, HttpMethod.PUT, request, String.class).getBody();
//        System.out.println(code);

    }

    public void deleteUser(long id) {
        HttpEntity<User> request = new HttpEntity(headers);
        code += restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, request, String.class).getBody();
        System.out.println(code);
    }
}