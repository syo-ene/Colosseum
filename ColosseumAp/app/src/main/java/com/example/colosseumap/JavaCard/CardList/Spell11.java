package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.JavaCard.Player;
import com.example.colosseumap.R;

public class Spell11 extends Card{
    public Spell11(){
        setName("スパイダーウェブ");
        setText("①相手を2マス戻す");
        setImage(R.drawable.spell11);
    }

    @Override
    public Card clone(){
        return new Spell11();
    }

    @Override
    public void ability(Player user, Player enemy){
        enemy.move(-2);
    }
}
