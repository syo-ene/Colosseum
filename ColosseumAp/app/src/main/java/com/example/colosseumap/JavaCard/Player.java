package com.example.colosseumap.JavaCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.colosseumap.JavaCard.CardList.*;

public class Player {
    
    private int line = 0;

    //private Card useCard = new Move1();
    private ArrayList<Card> useCard = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> redoCard = new ArrayList<>();

    //ラインのアクセッサー
    public int getLine(){
        return this.line;
    }

    public int getEnemyLine(){
        return 6 - this.line;
    }

    public void setLine(int newLine){
        if(newLine < 0){
            this.line = 0;
        }
        else if(6 < newLine){
            this.line = 6;
        }
        else{
            this.line = newLine;
        }
    }

    public void move(int move){
        setLine(this.line += move);
    }


    //手札のアクセッサー
    public ArrayList<Card> getHand() {
        return new ArrayList<Card>(this.hand);
    }

    public void addHand(Card card){
        this.hand.add(card);
    }

        public void addHand(int index, Card card){
        this.hand.add(index, card);
    }

    public Card removeHand(int num){
        return this.hand.remove(num);
    }

    public void draw(ArrayList<Card> deck){
        addHand(deck.remove(new Random().nextInt(deck.size())));
    }

    public void numDraw(ArrayList<Card> deck, int num){
        for(int i = 0; i < num; i++)
        addHand(deck.remove(new Random().nextInt(deck.size())));
    }



    //使っているカードのアクセッサー
    public ArrayList<Card> getUseCard(){
        return new ArrayList<>(this.useCard);
    }

    public void addUseCard(Card card){
        this.useCard.add(card);
    }

    public Card removeUseCard(int num){
        return this.useCard.remove(num);
    }

    public void sendUseCard(List<Card> list){
        list.add(removeUseCard(0));
    }

    public void sendUseCard(List<Card> list, List<Card> list2){
        Card card = removeUseCard(0);
        list.add(card);
        list2.add(card);
    }


    //redo用のアクセッサー
    public ArrayList<Card> getRedoCard(){
        return new ArrayList<>(this.redoCard);
    }

    public void addRedoCard(Card card){
        this.redoCard.add(card);
    }

    public Card removeRedoCard(int num){
        return this.redoCard.remove(num);
    }


    //複合
    public void use(int num){
        addUseCard(removeHand(num));
    }

}
