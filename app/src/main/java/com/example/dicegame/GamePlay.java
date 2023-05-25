package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay extends AppCompatActivity {

    Player firstPlayer,secondPlayer;
    Button btnPlayer1, btnPlayer2,btnActivity1;
    ImageButton btn;
    ImageView imgDice1,imgDice2,imgDice3;
    TextView txtCurrentPot,txtPointsP1,txtPointsP2;



    int[] images={R.drawable.die1, R.drawable.die2,R.drawable.die3,R.drawable.die4,R.drawable.die5,R.drawable.die6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);


        ////retrieving the account object from main activity
        Account account=(Account)getIntent().getSerializableExtra("Account");


        //create a new player
        firstPlayer=new Player();
        secondPlayer=new Player();


        //Initialize and assign the buttons and textviews to specific UI elements
        btnPlayer1=(Button) findViewById(R.id.btnPlayer1);
        btnPlayer2=(Button) findViewById(R.id.btnPlayer2);
        btnActivity1=(Button) findViewById(R.id.btnActivity1);

        btn=(ImageButton) findViewById(R.id.btnDouble);


        txtCurrentPot=(TextView) findViewById(R.id.txtCurrentPot);
        txtPointsP1=(TextView) findViewById(R.id.txtPointsP1);
        txtPointsP2=(TextView) findViewById(R.id.txtPointsP2);

        imgDice1=(ImageView) findViewById(R.id.imgDice);
        //Die 2
        imgDice2=(ImageView) findViewById(R.id.imgDice2);
        //Die 3
        imgDice3=(ImageView) findViewById(R.id.imgDice3);

        //The pot is the same for both players.
        txtCurrentPot.setText("$"+Integer.toString(firstPlayer.getAmountPot()));

        btnPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame(firstPlayer);

                txtPointsP1.setText(Integer.toString(firstPlayer.getPoints()));
            }
        });

        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame(secondPlayer);

                txtPointsP2.setText(Integer.toString(secondPlayer.getPoints()));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPlayer.setAmountPot(firstPlayer.getAmountPot()*2);
                secondPlayer.setAmountPot(secondPlayer.getAmountPot()*2);
                UpdateDisplay();
            }
        });

        //Exit button, it goes back to main activity
        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(GamePlay.this,MainActivity.class);
                startActivity(inte);
            }
        });

    }

    private void playGame(Player player){

        int[]dice=rollDice();
        //calculate the sum of all 3 dices
        int totalPoints=dice[0]+dice[1]+dice[2];
        player.setPoints(totalPoints);
        //Chances played, after every button play chances increase by one
        player.setChancesPlayed(player.getChancesPlayed()+1);

        //compare the players after the number of chances get to 3
        if(firstPlayer.getChancesPlayed()==3 && secondPlayer.getChancesPlayed()==3){
            comparePlayers(firstPlayer,secondPlayer);


        }


    }

    private int[] rollDice(){
        int[]dice=new int[3];
        for(int i=0;i<3;i++){

            //use math random to get a random number between 1 and 6 (inclusive 6)
            dice[i]=(int)(Math.random()*6)+1;
        }
        //set the random dice to the picture to show the photo of the dice
        imgDice1.setImageResource(images[dice[0]-1]);
        imgDice2.setImageResource(images[dice[1]-1]);
        imgDice3.setImageResource(images[dice[2]-1]);

        return dice;

    }
    //compare players class
    private void comparePlayers(Player p1, Player p2){
        int player1Difference=100-p1.getPoints();
        int player2Difference=100-p2.getPoints();

        if(player2Difference>player1Difference){
            p1.setAmountWon(p2.getAmountPot());

            Toast.makeText(getApplicationContext(), "Congratulations, Player 1! You won $"+p1.getAmountWon()+" by now", Toast.LENGTH_SHORT).show();

        } else if (player1Difference>player2Difference) {
            p2.setAmountWon(p1.getAmountPot());

            Toast.makeText(getApplicationContext(), "Congratulations, Player 2! You won $"+p2.getAmountWon()+" by now", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Nobody won, pot increase by $50", Toast.LENGTH_SHORT).show();
        }

    }
    //update display class after someone win
    private void UpdateDisplay(){
        txtCurrentPot.setText("$"+Integer.toString(firstPlayer.getAmountPot()));
        firstPlayer.resetGame();
        secondPlayer.resetGame();
        txtPointsP1.setText(Integer.toString(firstPlayer.getPoints()));
        txtPointsP2.setText(Integer.toString(secondPlayer.getPoints()));

    }

}