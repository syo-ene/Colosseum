package com.example.colosseumap.JavaCard.CardList;

import com.example.colosseumap.JavaCard.Player;
import com.example.colosseumap.R;

public class Spell21 extends Card{
    public Spell21(){
        setName("マーシャルカウンター");
        setText("②両方のプレイヤーが同じマスに止まる\nまたは通りすぎる時、ゲームに勝利する");
        setImage(R.drawable.spell21);
    }

    @Override
    public Card clone(){
        return new Spell21();
    }

}
