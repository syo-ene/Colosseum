package com.example.colosseumap.JavaCard.CardList;

import android.content.Context;

import com.example.colosseumap.R;

public class Move1 extends Card{

    public Move1(){
        setName("ステップ");
        setForward(1);
        setText("②1マス進む\n④手札に戻る");
        setImage(R.drawable.move1);
    }

    @Override
    public Card clone(){
        return new Move1();
    }
}
