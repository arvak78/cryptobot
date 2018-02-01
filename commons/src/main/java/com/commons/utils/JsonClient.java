package com.commons.utils;

import com.commons.model.Wrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class JsonClient<E> {

    public ListenableFuture<ResponseEntity<Wrapper<E>>> client(ParameterizedTypeReference<Wrapper<E>> type, String url) throws IOException {

        AsyncRestTemplate asyncRest = new AsyncRestTemplate();
        RestTemplate temp = new RestTemplate();

//        ListenableFuture<ResponseEntity<E>> future = asyncRest.getForEntity(url, object);

        ResponseEntity<Wrapper<E>> exchange = temp.exchange(
                url,
                HttpMethod.GET,
                null,
                type);

        ListenableFuture<ResponseEntity<Wrapper<E>>> future = asyncRest.exchange(
                url,
                HttpMethod.GET,
                null,
                type);


        future.addCallback(new ListenableFutureCallback<ResponseEntity>() {
            @Override
            public void onSuccess(ResponseEntity result) {
                if (result.getStatusCodeValue() == 200) {
                    System.out.println("Success!!");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Error: " + t);
            }
        });

        return future;

    }

}
