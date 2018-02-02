package com.commons.utils;

import com.commons.model.Wrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class JsonClient<E> {

    public ListenableFuture<ResponseEntity<Wrapper<E>>> asyncClient(ParameterizedTypeReference<Wrapper<E>> type, String url) throws IOException {

        AsyncRestTemplate asyncRest = new AsyncRestTemplate();

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
