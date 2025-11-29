package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Move2 extends Card{
    public Move2(){
        setName("ダッシュ");
        setForward(2);
        setText("②2マス進む");
        setImage(R.drawable.move2);
    }

    @Override
    public Card clone(){
        return new Move2();
    }
}