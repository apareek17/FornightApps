package com.example.evanstrittmatter16.fornightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    private String accountId;
    private ArrayList<Stats> lifeTimeStat;
    private Button check;
    private EditText platform;
    private EditText username;
    public static final String EXTRA_ACCOUNT = "account";
    public static final String EXTRA_STATS = "stats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(platform.getText().toString().equals("") || username.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Fill in a username or platform", Toast.LENGTH_SHORT).show();
                }
                else{
                    getAccountData(platform.getText().toString(), username.getText().toString());
                }
            }
        });


    }

    private void getAccountData(String p, String u) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fortnitetracker.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FornightService service =
                retrofit.create(FornightService.class);

        Call<ItemResponse> itemResponseCall =
                service.searchByPlatfrom(p, u);

        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call,
                                   Response<ItemResponse> response) {


                if(response.body() == (null)) {

                    Toast.makeText(MainActivity.this, "You didn't enter a valid platform, make sure it's pc,xb1, or psn.", Toast.LENGTH_SHORT).show();
                }
                else if(response.body().getAccountId() == (null)){
                    Toast.makeText(MainActivity.this, "Your Username didn't seem to work, maybe you forgot a capital.", Toast.LENGTH_SHORT).show();
                }
                else{

                    accountId = response.body().getAccountId();
                    lifeTimeStat = response.body().getLifeTimeStats();

                    Intent intent = new Intent(MainActivity.this, AccountInfoActivity.class);

                    ArrayList<Stats> stats = new ArrayList<>();

                    intent.putExtra(EXTRA_ACCOUNT, accountId);
                    intent.putParcelableArrayListExtra(EXTRA_STATS, lifeTimeStat);
                    startActivity(intent);
                }



                // Log.d("ENQUEUE", "onResponse: " + accountId);

                //  Toast.makeText(MainActivity.this, "test toast" + accountId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ItemResponse> call,
                                  Throwable t) {
                Log.d("ENQUEUE", "onFailure: " + t.getMessage());
            }
        });

    }


    private void wireWidgets() {

        username = findViewById(R.id.edittext_main_username);
        platform = findViewById(R.id.edittext_main_platform);
        check = findViewById(R.id.button_main_check);

    }

    private void searchItems() {
    }
}