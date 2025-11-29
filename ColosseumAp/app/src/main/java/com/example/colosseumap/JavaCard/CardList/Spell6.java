package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell6 extends Card{
    public Spell6(){
        setName("ヘルフレア");
        addAttack(5);
        setText("③5マス目を攻撃");
        setImage(R.drawable.spell6);
    }

    @Override
    public Card clone(){
        return new Spell6();
    }
}
