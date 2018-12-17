package com.sheygam.masa_2018_g1_05_12_18_hw.data;

import android.util.Log;

import com.google.gson.Gson;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.model.ContactDto;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.model.ContactListDto;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.model.ErrorDto;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.model.TokenResponseDto;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.model.UserDto;
import com.sheygam.masa_2018_g1_05_12_18_hw.presentation.model.Contact;
import com.sheygam.masa_2018_g1_05_12_18_hw.presentation.model.ContactList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpProvider {
    public static final String BASE_URL = "https://contacts-telran.herokuapp.com";
    private static final HttpProvider ourInstance = new HttpProvider();

    private OkHttpClient client;
    private Gson gson;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static HttpProvider getInstance() {
        return ourInstance;
    }

    private HttpProvider() {
        client = new OkHttpClient.Builder()
                .readTimeout(15,TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS)
                .build();
        gson = new Gson();
    }

    public String login(String email, String password) throws Exception {
        String json = gson.toJson(new UserDto(email,password));
        RequestBody body = RequestBody.create(JSON,json);

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/login")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            String responseJson = response.body().string();
            TokenResponseDto token = gson.fromJson(responseJson,TokenResponseDto.class);
            return token.getToken();
        }else{
            String responseJson = response.body().string();
            ErrorDto error = gson.fromJson(responseJson,ErrorDto.class);
            Log.d("MY_TAG", "login error: " + error);
            throw new Exception(error.getMessage());
        }
    }

    public String registration(String email, String password) throws Exception {
        String json = gson.toJson(new UserDto(email,password));
        RequestBody body = RequestBody.create(JSON,json);

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/registration")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            String responseJson = response.body().string();
            TokenResponseDto token = gson.fromJson(responseJson,TokenResponseDto.class);
            return token.getToken();
        }else{
            String responseJson = response.body().string();
            ErrorDto error = gson.fromJson(responseJson,ErrorDto.class);
            Log.d("MY_TAG", "registration error: " + error);
            throw new Exception(error.getMessage());
        }
    }

    public ContactList getContacts(String token) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/contact")
                .addHeader("Authorization",token)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            String json = response.body().string();
            ContactListDto listDto = gson.fromJson(json,ContactListDto.class);
            return map(listDto);
        }else{
            String json = response.body().string();
            Log.d("MY_TAG", "getContacts error: " + json);
            ErrorDto error = gson.fromJson(json,ErrorDto.class);
            throw new Exception(error.getMessage());
        }
    }

    private ContactList map(ContactListDto listDto) {
        ContactList contactList = new ContactList();
        List<Contact> list = new ArrayList<>();
        for(ContactDto cntc : listDto.getContacts()){
            list.add(new Contact(cntc.getName(),
                    cntc.getEmail(),
                    cntc.getPhone(),
                    cntc.getAddress(),
                    cntc.getDescription()
            ));
        }
        contactList.setContacts(list);
        return contactList;
    }
}
