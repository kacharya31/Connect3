package com.example.connect3;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 1;
    boolean game = true;

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int tieCheck = 0;

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;
        int num = Integer.parseInt(counter.getTag().toString());


        if (game && gameState[num] == 0) {

            gameState[num] = player;
            tieCheck++;

            if (player == 1) {
                    counter.setImageResource(R.drawable.yellow);
                    player = 2;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 1;
            }

            counter.setTranslationY(-2000);
            counter.animate().rotation(3600).translationYBy(2000).setDuration(500);

            for (int[] a : winningPositions) {
                TextView textView = (TextView) findViewById(R.id.scoreTextView);
                if (gameState[a[0]] == 1 && gameState[a[1]] == 1 && gameState[a[2]] == 1) {
                    textView.setText("YELLOW WINS!");
                    game = false;
                } else if (gameState[a[0]] == 2 && gameState[a[1]] == 2 && gameState[a[2]] == 2) {
                    textView.setText("RED WINS!");
                    game = false;
                } else if (tieCheck == 9 && game) {
                    textView.setText("TIE!");
                    game = false;
                }

                if (game == false) {
                    Button playAgainButton = (Button) findViewById(R.id.button);
                    textView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {

        TextView textView = (TextView) findViewById(R.id.scoreTextView);

        Button playAgainButton = (Button) findViewById(R.id.button);

        textView.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        player = 1;

        game = true;

        tieCheck = 0;

        for (int a = 0 ; a < gameState.length; a++) {
            gameState[a] = 0;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}