package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell7 extends Card{
    public Spell7(){
        setName("ドリルショット");
        addAttack(1);
        addAttack(2);
        setText("③1~2マス目を攻撃");
        setImage(R.drawable.spell7);
    }

    @Override
    public Card clone(){
        return new Spell7();
    }
}
