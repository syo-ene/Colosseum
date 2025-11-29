package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell8 extends Card{
    public Spell8(){
        setName("ウィンドアクセル");
        setForward(3);
        setText("②3マス進む");
        setImage(R.drawable.spell8);

    }

    @Override
    public Card clone(){
        return new Spell8();
    }
}
