package com.e_wayalerts.WebService;

import com.e_wayalerts.Utility.Utility;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.e_wayalerts.Utility.Utility.getToken;

import android.util.Log;


public class ApiClient {
	
	private static Retrofit retrofit = null;
	
	public static Retrofit getClient() {
		
	/*	HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/
		
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request.Builder ongoing = chain.request().newBuilder();
				Log.e("TAG", "token 33: " + String.valueOf(getToken()));

				ongoing.addHeader("token", getToken());
				return chain.proceed(ongoing.build());
			}
		}).build();
		
	
		
		
		retrofit = new Retrofit.Builder()
				.baseUrl("https://api.ewayalerts.com/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
		
		
		
		return retrofit;
	}
	
	
}
