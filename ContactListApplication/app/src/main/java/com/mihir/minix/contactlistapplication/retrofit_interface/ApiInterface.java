package com.mihir.minix.contactlistapplication.retrofit_interface;
import com.mihir.minix.contactlistapplication.model.Contact;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiInterface {
    @GET("/users")
   Call<Object>getContact();


}
