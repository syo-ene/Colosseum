package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell4 extends Card{
    public Spell4(){
        setName("ナチュラルコントロール");
        addAttack(3);
        setText("③3マス目を攻撃");
        setImage(R.drawable.spell4);
    }

    @Override
    public Card clone(){
        return new Spell4();
    }
}
