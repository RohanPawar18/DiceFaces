package com.example.dhaval.diceface;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int userScore,userTurnScore,compScore,compTurnScore;
Button roll,hold,reset;
    ImageView diceImage;

    TextView userStackText,compStackText,userScoreText,compScoreText;

   public int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll=(Button)findViewById(R.id.roll);
        hold=(Button)findViewById(R.id.hold);
        reset=(Button)findViewById(R.id.reset);
        diceImage=(ImageView) findViewById(R.id.diceImage);

        userStackText=(TextView)findViewById(R.id.userStack);
        compStackText=(TextView)findViewById(R.id.compStack);
        userScoreText=(TextView)findViewById(R.id.userScore);
        compScoreText=(TextView)findViewById(R.id.compScore);
        userStackText.setBackgroundColor(Color.GREEN);
        userScoreText.setBackgroundColor(Color.GREEN);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateValue();
            }



        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFlag();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }

    public void updateValue()
    {
        int rollValue=(int)(Math.random()*100)%6;
        if(rollValue==0)
        {
            rollValue=6;
        }

        switch (rollValue)
        {
            case 1:diceImage.setImageDrawable(getDrawable(R.drawable.dice1));
                onOne();
                break;
            case 2:diceImage.setImageDrawable(getDrawable(R.drawable.dice2));
                break;
            case 3:diceImage.setImageDrawable(getDrawable(R.drawable.dice3));
                break;
            case 4:diceImage.setImageDrawable(getDrawable(R.drawable.dice4));
                break;
            case 5:diceImage.setImageDrawable(getDrawable(R.drawable.dice5));
                break;
            case 6:diceImage.setImageDrawable(getDrawable(R.drawable.dice6));
                break;

        }
        if( rollValue!=1) {
            if (flag == 0) {
                userTurnScore += rollValue;
                userStackText.setText("CurrentStack: " + userTurnScore);
            } else {

                compTurnScore += rollValue;
                compStackText.setText("CurrentStack: " + compTurnScore);
            }
        }
    }
    public void updateFlag(){
        if(flag==0){
            Toast.makeText(this, "User Turn", Toast.LENGTH_SHORT).show();
            userScore+=userTurnScore;
            if(userScore>=50)
            {

                Intent i=new Intent(this,Results.class);

                    i.putExtra("winner",0);
                resetValues();
                startActivity(i);


            }
            userStackText.setText("CurrentStack: 0");
            userScoreText.setText("User Score: "+userScore);
            compStackText.setBackgroundColor(Color.GREEN);
            compScoreText.setBackgroundColor(Color.GREEN);

            userStackText.setBackgroundColor(Color.WHITE);
            userScoreText.setBackgroundColor(Color.WHITE);
            flag=(++flag)%2;
            userTurnScore=0;
            compTurnScore=0;
            computerTurn();

        }
        else
        {
            compScore+=compTurnScore;
            compStackText.setText("CurrentStack: 0");

            if(compScore>=50)
            {
                Intent i=new Intent(this,Results.class);

                i.putExtra("winner",1);
                resetValues();
                startActivity(i);


            }

            userStackText.setBackgroundColor(Color.GREEN);
            userScoreText.setBackgroundColor(Color.GREEN);


            compStackText.setBackgroundColor(Color.WHITE);
            compScoreText.setBackgroundColor(Color.WHITE);

            compScoreText.setText("Comp Score: "+compScore);
            flag=(++flag)%2;
            userTurnScore=0;
            compTurnScore=0;

        }

    }
    public void onOne(){
        userTurnScore=0;
        compTurnScore=0;
        updateFlag();
    }
    public void resetValues(){
        userScore=0;
        userTurnScore=0;
        compScore=0;
        compTurnScore=0;
        userStackText.setText("CurrentStack: "+userTurnScore);
        compStackText.setText("CurrentStack: "+compTurnScore);
        userScoreText.setText("User Score: "+userScore);
        compScoreText.setText("Comp Score: "+compScore);
        flag=0;
        userStackText.setBackgroundColor(Color.GREEN);
        userScoreText.setBackgroundColor(Color.GREEN);
        compStackText.setBackgroundColor(Color.WHITE);
        compScoreText.setBackgroundColor(Color.WHITE);

    }
    public void computerTurn(){

        roll.setEnabled(false);
        hold.setEnabled(false);
        while(flag==1){
            updateValue();


            if(compTurnScore>=10){
                updateFlag();

                break;
            }
        }
        roll.setEnabled(true);
        hold.setEnabled(true);



    }
}
