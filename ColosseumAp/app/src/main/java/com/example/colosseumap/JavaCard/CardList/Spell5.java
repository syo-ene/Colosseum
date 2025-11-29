package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell5 extends Card{
    public Spell5(){
        setName("パワーアタック");
        addAttack(4);
        setText("③4マス目を攻撃");
        setImage(R.drawable.spell5);
    }

    @Override
    public Card clone(){
        return new Spell5();
    }
}
