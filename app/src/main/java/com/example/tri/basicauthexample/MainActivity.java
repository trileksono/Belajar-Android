package com.example.tri.basicauthexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.tri.basicauthexample.model.Buku;
import com.example.tri.basicauthexample.model.Pagging;
import com.example.tri.basicauthexample.service.API;
import com.example.tri.basicauthexample.service.Service;
import com.example.tri.basicauthexample.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void tes(){
        API service = new Service(this).getmApi();
        Call<Pagging<Buku>> call = service.getAllBuku();
        call.enqueue(new Callback<Pagging<Buku>>() {
            @Override
            public void onResponse(Call<Pagging<Buku>> call, Response<Pagging<Buku>> response) {
                for(int i = 0; i < response.body().getContent().size(); i++){
                    Log.i("RESPONSE", response.body().getContent().get(i).getNamaBuku());
                }
            }

            @Override
            public void onFailure(Call<Pagging<Buku>> call, Throwable t) {

            }
        });
    }
}
