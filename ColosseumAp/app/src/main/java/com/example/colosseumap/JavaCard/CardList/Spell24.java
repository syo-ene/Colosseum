package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell24 extends Card{
    public Spell24(){
        setName("空間圧縮");
        setForward(1);
        setText("②相手の進むマスを1増やす\n②1マス進む");
        setImage(R.drawable.spell24);
    }

    @Override
    public Card clone(){
        return new Spell24();
    }
    
}
