package com.example.dhaval.diceface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Results extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        img=(ImageView)findViewById(R.id.imageView);

        Bundle b=getIntent().getExtras();

        if(b.getInt("winner")==0)
        {
            img.setImageDrawable(getDrawable(R.drawable.winner));
        }
        else
        {
            img.setImageDrawable(getDrawable(R.drawable.l));
        }

    }
}
