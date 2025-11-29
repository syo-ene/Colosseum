package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.R;

public class Spell22 extends Card{
    public Spell22(){
        setName("スペルカウンター");
        setText("③相手が攻撃する時、\nゲームに勝利する");
        setImage(R.drawable.spell22);
    }

    @Override
    public Card clone(){
        return new Spell22();
    }

}
