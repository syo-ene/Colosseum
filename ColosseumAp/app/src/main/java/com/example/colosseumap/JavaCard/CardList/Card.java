package com.example.colosseumap.JavaCard.CardList;

import java.util.ArrayList;

import com.example.colosseumap.JavaCard.Player;

public abstract class Card {
    private String name;
    private String text;
    private int textId;
    private int forward = 0;
    private ArrayList<Integer> attack = new ArrayList<>();
    private int image;

    // 名前のアクセッサー
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // テキストのアクセッサー
    public String getText() {
        return this.text;
    }
    public void setText(String newText) {
        this.text = newText;
    }
    //テキストidのアクセッサー
    public int getTextId() {
        return this.textId;
    }

    public void setTextId(int newText) {
        this.textId = newText;
    }

    // 進む値のアクセッサー
    public int getForward() {
        return this.forward;
    }

    public void setForward(int newForward) {
        this.forward = newForward;
    }

    // 攻撃座標のアクセッサー
    public ArrayList<Integer> getAttack() {
        return new ArrayList<>(this.attack);
    }

    public void addAttack(int attackPoint) {
        this.attack.add(attackPoint);
    }

    //画像のアクセッサー
    public  int  getImage(){return this.image;}
    public void setImage(int newImage){
        this.image = newImage;
    }

    //クローン
    public abstract Card clone ();


    // 固有能力
    public void ability(Player user, Player enemy) {
    }

    // 攻撃ヒット判定
    public boolean hit(Player user, Player enemy) {
        boolean hit = false;
        for (int attackPoint : user.getUseCard().get(0).getAttack()) {
            if (user.getLine() + attackPoint == enemy.getEnemyLine()) {
                hit = true;
            }
            if (hit) {
                break;
            }
        }
        return hit;
    }

}
