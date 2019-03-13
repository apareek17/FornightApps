package com.example.evanstrittmatter16.fornightapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;


public class AccountInfoActivity extends AppCompatActivity {

    private  String acc;
    private List<Stats> stats;
    private String wins;

    private TextView accountInfo;
    private TextView totalWins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfo);
        wireWidgets();

        Intent accountIntent = getIntent();
        acc = accountIntent.getStringExtra("account");
        Intent statIntent = getIntent();
        stats = statIntent.getParcelableArrayListExtra("stats");
        //Toast.makeText(this, "this works again" + acc, Toast.LENGTH_SHORT).show();



        ;
        wins = stats.get(9).getKey();


//        Stats is:
//                1 Top 5
//                2 Top 3
//                3 Top 6
//                4 Top 10
//                5 Top 12
//                6 Top 25
//                7 Score
//                8 Matches played
//                9 wins
//                10 Win%
//                11 Kills
//                12 k/d



        accountInfo.setText("Your Account ID is: " + acc);
        totalWins.setText("You have " + wins + " wins.");


    }



    private void wireWidgets() {
        accountInfo = findViewById(R.id.textview_accountinfo_accountid);
        totalWins = findViewById(R.id.textview_accountinfo_totalwins);
    }


}
