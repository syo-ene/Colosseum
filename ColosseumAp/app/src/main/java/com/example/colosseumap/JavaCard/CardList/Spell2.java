package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell2 extends Card{
    public Spell2(){
        setName("アクアスラッシュ");
        addAttack(1);
        setText("③1マス目を攻撃");
        setImage(R.drawable.spell2);

    }

    @Override
    public Card clone(){
        return new Spell2();
    }
}
