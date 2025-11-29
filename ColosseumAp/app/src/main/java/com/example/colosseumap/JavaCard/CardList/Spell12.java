package com.example.colosseumap.JavaCard.CardList;

import java.util.Scanner;

import com.example.colosseumap.JavaCard.Player;
import com.example.colosseumap.R;

public class Spell12 extends Card{
    public Spell12(){
        setName("等価交換");
        setText("①相手のステップではない手札を１枚、\n自分の手札に加える");
        setImage(R.drawable.spell12);

    }


    @Override
    public Card clone(){
        return new Spell12();
    }

    @Override
    public void ability(Player user, Player enemy) {
        super.ability(user, enemy);
    }
}
