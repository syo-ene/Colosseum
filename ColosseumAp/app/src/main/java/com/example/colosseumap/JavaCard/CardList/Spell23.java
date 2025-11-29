package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell23 extends Card{
    public Spell23(){
        setName("リバーススタート");
        setText("②相手が2マス以上進む時、\nかわりに自分が同じ数進む");
        setImage(R.drawable.spell23);
    }

    @Override
    public Card clone(){
        return new Spell23();
    }
}
