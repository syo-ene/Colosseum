package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.JavaCard.Player;
import com.example.colosseumap.R;

public class Spell25 extends Card{
    public Spell25(){
        setName("re do");
        setText("①初期位置に戻る\n①このゲーム中に使ったカードを全て手札に戻す");
        setImage(R.drawable.spell25);
    }

    @Override
    public Card clone(){
        return new Spell25();
    }

    @Override
    public void ability(Player user, Player enemy){
        user.move(-6);

        for (Card card : user.getRedoCard()) {
            user.addHand(card);
        }
    }
}