package cn.ning.money.book.rest;

import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ThirdPartyServiceClient {
    private final static String BASE_URL = "http://localhost:3000/express";
    private final static RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<ExpressResponse> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, null, ExpressResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ExpressResponse body = responseEntity.getBody();
            System.out.println(JSON.toJSON(body));
        }

    }


}
