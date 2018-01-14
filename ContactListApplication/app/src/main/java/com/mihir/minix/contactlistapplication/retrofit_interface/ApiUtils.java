package com.mihir.minix.contactlistapplication.retrofit_interface;

/**
 * Created by minix on 1/13/2018.
 */

public class ApiUtils {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public static ApiInterface apiInterface() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
