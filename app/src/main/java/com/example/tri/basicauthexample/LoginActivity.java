package com.example.tri.basicauthexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tri.basicauthexample.service.API;
import com.example.tri.basicauthexample.util.Constant;
import com.example.tri.basicauthexample.util.SecurePreferences;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tri on 5/15/16.
 */
public class LoginActivity extends AppCompatActivity{

    String TAG = this.getClass().getSimpleName();

    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.txt_username)
    TextView txtUsername;
    @Bind(R.id.txt_password)
    TextView txtPassword;
    Retrofit mRetrofit;
    API request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void doLogin(){
        mRetrofit = new Retrofit
                .Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptLogin(txtUsername.getText().toString(),txtPassword.getText().toString()))
                .build();
        request = mRetrofit.create(API.class);

        Call<Object> loginCall = request.basicLogin();
        loginCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.code() != 200){
                    Toast.makeText(getApplicationContext(),"Username atau password salah", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private OkHttpClient interceptLogin(String user,String pass){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String credentials = user + ":" + pass;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        SecurePreferences pref = new SecurePreferences(LoginActivity.this,Constant.prefName,Constant.prefPass,true);
        pref.put("basic",basic);
        return httpClient.build();
    }
}
