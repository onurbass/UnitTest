package com.muhammet.springunit_integ_test.controller;

import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Repeat;

import java.io.IOException;

public class MusteriControllerTest {

    @Test
    void findAllTest() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url("http://localhost:9090/musteri/getall")
                .method("GET",null)
                .build();
       Response response =  client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    @Repeat(100)
    void saveTest() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,"{\n" +
                "    \"ad\": \"Canan HOCA\",\n" +
                "    \"adres\": \"İzmir da bir yer\",\n" +
                "    \"telefon\": \"0 555 999 8877\"\n" +
                "}");
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url("http://localhost:9090/musteri/save")
                .method("POST",requestBody)
                .build();
        Response response =  client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
