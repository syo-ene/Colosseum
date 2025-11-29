package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell3 extends Card{
    public Spell3(){
        setName("エクスプロージョン");
        addAttack(2);
        setText("③2マス目を攻撃");
        setImage(R.drawable.spell3);
    }

    @Override
    public Card clone(){
        return new Spell3();
    }
}
