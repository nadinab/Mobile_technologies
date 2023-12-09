package com.example.lab1_game;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private class GameArea{
        private class Card{
            public CardView view;
            public int id;
            public boolean isCardOpen = false;
            public void OpenCard(){
                view.setBackgroundResource(imagesIds[id]);
                isCardOpen = true;
            }

            public void CloseCard(){
                view.animate().setDuration(800)
                        .scaleX(1)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setBackgroundResource(R.drawable.card_back);
                                isPlayable = true;
                            }
                        })
                        .start();
                isCardOpen =false;
            }
        };
        public	Integer[] imagesIds = { R.drawable.npc_dota_hero_abaddon,R.drawable.npc_dota_hero_alchemist,R.drawable.npc_dota_hero_abyssal_underlord,R.drawable.npc_dota_hero_antimage,
                R.drawable.npc_dota_hero_ancient_apparition,R.drawable.npc_dota_hero_arc_warden,R.drawable.npc_dota_hero_armadillo,R.drawable.npc_dota_hero_axe,
                R.drawable.npc_dota_hero_abaddon,R.drawable.npc_dota_hero_alchemist,R.drawable.npc_dota_hero_abyssal_underlord,R.drawable.npc_dota_hero_antimage,
                R.drawable.npc_dota_hero_ancient_apparition,R.drawable.npc_dota_hero_arc_warden,R.drawable.npc_dota_hero_armadillo,R.drawable.npc_dota_hero_axe};
        public	Integer[] cardsIds = { R.id.card1, R.id.card2, R.id.card3, R.id.card4,
                R.id.card5, R.id.card6, R.id.card7, R.id.card8,
                R.id.card9, R.id.card10, R.id.card11, R.id.card12,
                R.id.card13, R.id.card14, R.id.card15, R.id.card16};
        boolean isSecondCard = false;
        public boolean isPlayable = true;
        private int firstCardId;
        private int firstImageId;

        private Card[] cards;
        public void CreateGameArea(){
            Collections.shuffle(Arrays.asList(imagesIds));
            cards = new Card[16];
            for(int i = 0; i<16; i++){
                cards[i] = new Card();
                cards[i].view = (CardView) findViewById(cardsIds[i]);
                cards[i].view.setBackgroundResource(R.drawable.card_back);
                cards[i].id = i;
                cards[i].isCardOpen = false;
                int finalI = i;
                cards[i].view.setOnClickListener(v -> {
                    if(isPlayable) {
                        if (!cards[finalI].isCardOpen) {
                            cards[finalI].OpenCard();
                            if (isSecondCard) {
                                isPlayable = false;
                                if (imagesIds[finalI] != firstImageId) {
                                    cards[finalI].CloseCard();
                                    cards[firstCardId].CloseCard();
                                } else {
                                    isPlayable = true;
                                    cards[finalI].isCardOpen = true;
                                    cards[firstCardId].isCardOpen = true;
                                }
                                isSecondCard = false;
                            } else {
                                firstImageId = imagesIds[finalI];
                                firstCardId = finalI;
                                isSecondCard = true;
                            }
                        }
                    }
                });
            }
        }
        public void GameRestart(){
            for(int i = 0; i<16; i++) {
                cards[i].CloseCard();
                cards[i].view.animate().cancel();
            }
            isSecondCard = false;
            isPlayable = true;
            CreateGameArea();
        }
    };
    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameArea gameArea = new GameArea();
        gameArea.CreateGameArea();
        buttonRestart = (Button) findViewById(R.id.restart_game);
        buttonRestart.setOnClickListener(v -> {
            gameArea.GameRestart();
        });
    }
    public void MyInfo(View view){
        Intent intent = new Intent(this, MyInfoActivity.class);
        startActivity(intent);
    }
}