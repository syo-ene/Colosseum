package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.JavaCard.Player;
import com.example.colosseumap.R;

public class Spell1 extends Card {
    public Spell1(){
        setName("サマーソルト");
        addAttack(1);
        setText("①1マス戻る\n③1マス目を攻撃");
        setImage(R.drawable.spell1);
    }

    @Override
    public Card clone(){
        return new Spell1();
    }

    @Override
    public void ability(Player user, Player enemy){
        user.move(-1);
    }

}
