package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell31 extends Card{
    public Spell31(){
        setName("");
        setText("");
        setImage(R.drawable.spell31);
    }

    @Override
    public Card clone(){
        return new Spell31();
    }
}
