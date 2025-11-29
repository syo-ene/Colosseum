package com.example.colosseumap.JavaCard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.colosseumap.JavaCard.CardList.*;

public class Main {
    public Player player1 = new Player();
    public Player player2 = new Player();
    public int firstMaxHand = 3;
    public int secondMaxHand = 4;
    public Player first;
    public Player second;
    public ArrayList<Card> moveDeck = new ArrayList<>();
    public ArrayList<Card> deck;


    public Main() {

        //移動カードを置くための除外ゾーン生成
        this.moveDeck.add(new Move1());
        this.moveDeck.add(new Move1());
        this.moveDeck.add(new Move2());
        this.moveDeck.add(new Move2());


        //山札生成
        this.deck = createDeck();


        //先攻後攻決め

//        if(new Random().nextInt(2) == 0){
//            first  = player1;
//            second = player2;
//        }
//        else{
//            first  = player2;
//            second = player1;
//        }

        //先攻後攻固定
        first  = player1;
        second = player2;


        
        //手札采配
        moveHand(first, moveDeck);
        first.numDraw(deck, firstMaxHand);

        moveHand(second, moveDeck);
        second.numDraw(deck, secondMaxHand);





        //ーーーーーーーーーーーーーーーーーーーーーーーーーーーターンの開始時点
//        do{
//            //初期化が必要な変数の初期化
//
//
//            //出すカードを選ぶ
//            showBoard(player1, player2);
//            System.out.println("出すカードを選べ");
//            player1.use(new Scanner(System.in).nextInt());
//            player2.use(new Scanner(System.in).nextInt());
//
//            showBoard(player1, player2);
//            System.out.print("next");
//            new Scanner(System.in).nextLine();
//
//
//
//            //固有能力の処理
//            ability(first, second);
//
//            //進む処理
//            end = move(first, second);
//
//            //攻撃の処理
//            if(!end){
//                end = attack(first, second);
//            }
//
//            showBoard(player1, player2);
//            System.out.print("next");
//            new Scanner(System.in).nextLine();
//
//            //使っているカードのを戻す
//            resetUseCard(first, deck, moveDeck);
//            resetUseCard(second, deck, moveDeck);
//
//        }while(!end);

    }

    //redoリスト初期化

    /////////////////////////////////////////////////////////ここから関数
    

    //ゲームの流れに必要な関数

    //山札生成
    public ArrayList<Card> createDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new Spell1());
        deck.add(new Spell2());
        deck.add(new Spell3());
        deck.add(new Spell4());
        deck.add(new Spell5());
        deck.add(new Spell6());
        deck.add(new Spell7());
        deck.add(new Spell8());
        deck.add(new Spell11());
        deck.add(new Spell12());
        deck.add(new Spell21());
        deck.add(new Spell22());
        deck.add(new Spell23());
        deck.add(new Spell24());
        deck.add(new Spell25());

        return deck;
    }

    //移動カードを手札へ
    public void moveHand(Player player, ArrayList<Card> moveDeck){
        boolean move1Check = false;
        boolean move2Check = false;
        for(Card card : player.getHand()){
            if(card.getName().equals(new Move1().getName())){
                move1Check = true;
            }
            else if(card.getName().equals(new Move2().getName())){
                move2Check = true;
            }
        }
        if(!move1Check){
            player.addHand(moveDeck.remove(0));
        }
        if(!move2Check){
            player.addHand(moveDeck.remove(moveDeck.size() - 1));
        }
    }



    //固有能力の処理
//    public void ability(){
//        first.getUseCard().get(0).ability(first, second);
//        second.getUseCard().get(0).ability(second, first);
//    }

    //アンドロイドスタジオ用固有能力の処理
    public void abilityFirst(){
        first.getUseCard().get(0).ability(first, second);
    }
    public void abilityPlayer1(int index){
        player1.addHand(player2.removeHand(index));
    }

    public void abilitySecond(){
        second.getUseCard().get(0).ability(second, first);
    }
    public void abilityPlayer2(int index){
        player2.addHand(player1.removeHand(index));
    }


    //進む処理
    public int move(){
        int end = 0;
        int firstForward = first.getUseCard().get(0).getForward();
        int secondForward = second.getUseCard().get(0).getForward();



        //２３の処理
        if(first.getUseCard().get(0).getName().equals(new Spell23().getName())  &&
            2 <= secondForward){
                firstForward = secondForward;
                secondForward = 0;
        }
        else if (second.getUseCard().get(0).getName().equals(new Spell23().getName()) &&
            2 <= firstForward){
                secondForward = firstForward;
                firstForward = 0;
        }

        //24の処理
        if(first.getUseCard().get(0).getName().equals(new Spell24().getName()) ){
            secondForward += 1;
        }
        else if(second.getUseCard().get(0).getName().equals(new Spell24().getName()) ){
            firstForward += 1;
        }


        
        if (5 < first.getLine() + firstForward + second.getLine() + secondForward){
            //System.out.println("近接戦闘");
            if(!second.getUseCard().get(0).getName().equals(new Spell21().getName())  &&(
                first.getUseCard().get(0).getName().equals(new Spell21().getName()) ||
                secondForward == 0 ||
                (0 < firstForward &&
                firstForward < secondForward))){
                    //System.out.println("先攻の勝利");
                end = 1;
            }
            else if(second.getUseCard().get(0).getName().equals(new Spell21().getName()) ||
                firstForward == 0 ||
                (0 < secondForward &&
                secondForward < firstForward)){
                //System.out.println("後攻の勝ち");
                end = 2;
            }
            else{
                end = judgeLine(first, second);
            }
        }
        else{
            first.move(firstForward);
            second.move(secondForward);
        }
        return end;
    }

    // public static boolean jugeMuve(Player pA, Player pB){
    //     boolean win = false;
    //     if(pB.getUseCard().get(0).getForward() == 0 ||
    //     (0 < pA.getUseCard().get(0).getForward() &&
    //     pA.getUseCard().get(0).getForward() < pB.getUseCard().get(0).getForward()) ||
    //     pA.getUseCard().get(0).getName() == new Spell21().getName()){
    //         System.out.println("先攻の勝利");
    //     }
    //     return win;
    // }


    //攻撃の処理
    public int attack(){
        if(first.getUseCard().get(0).getAttack().size() == 0 && second.getUseCard().get(0).getAttack().size() == 0){
            //return false;
            return 0;
        }
        if (!second.getUseCard().get(0).getName().equals(new Spell22().getName())  &&(
            first.getUseCard().get(0).getName().equals(new Spell22().getName()) ||
            first.getUseCard().get(0).hit(first, second) && !second.getUseCard().get(0).hit(second, first))){
            //System.out.println("先攻の勝ち");
            return 1;
        }
        else if(second.getUseCard().get(0).getName().equals(new Spell22().getName()) ||
            !first.getUseCard().get(0).hit(first, second) && second.getUseCard().get(0).hit(second, first)){
            //System.out.println("後攻の勝ち");
            return 2;
        }
        else if(first.getUseCard().get(0).hit(first, second) && second.getUseCard().get(0).hit(second, first)){
            return judgeLine(first, second);
        }
        else{
            //return false;
            return 0;
        }
    }

    //ライン判定
    public int judgeLine(Player first, Player second){
        int end;
        //System.out.println("ライン判定");
        boolean firstWin = false;
        if(second.getLine() <= first.getLine()){
            firstWin = true;
        }

        if (firstWin) {
            //System.out.println("先攻の勝ち");
            end = 11;
        }
        else{
            //System.out.println("後攻の勝ち");
            end = 12;
        }

        return end;
    }

    //使っているカードを対応した山札に返す

    public void resetUseCard(Player player, ArrayList<Card> deck, ArrayList<Card> moveDeck){
        if(player.getUseCard().get(0).getName().equals(new Move1().getName())){
            player.addHand(0, player.removeUseCard(0));
        }
        else if(player.getUseCard().get(0).getName().equals(new Move2().getName())){
            Card usedCard = player.removeUseCard(0);
            moveDeck.add(usedCard);
            player.addRedoCard(usedCard);
        }
        else{
            Card usedCard = player.removeUseCard(0);
            deck.add(usedCard);
            player.addRedoCard(usedCard);
        }
    }

    //アンドロイドスタジオ用メソッド
    public void resetUseCard(){
        resetUseCard(first, deck, moveDeck);
        resetUseCard(second, deck, moveDeck);
    }

    /////////////////////////////////////////////////////////////////////デバックで見やすくする関数
    //盤面を見る
    public void showBoard(Player player1,Player player2){
        ArrayList<String> line = new ArrayList<>();
        for(int i = 0; i < 7 ; i++){
            line.add("--");
        }
        line.set(player1.getEnemyLine(), "p1");
        line.set(player2.getLine(), "p2");

        System.out.println();
        System.out.print(line.get(0) + "| ");System.out.println("p2");
        System.out.print(line.get(1) + "| ");System.out.println(cardName(player2.getHand()));
        System.out.print(line.get(2) + "| ");System.out.println(cardName(player2.getUseCard()));
        System.out.print(line.get(3) + "| ");System.out.println();
        System.out.print(line.get(4) + "| ");System.out.println(cardName(player1.getUseCard()));
        System.out.print(line.get(5) + "| ");System.out.println(cardName(player1.getHand()));
        System.out.print(line.get(6) + "| ");System.out.println("p1");
        System.out.println();
    }

    public String cardName(List<Card> list){
        String cardName = "";
        int num = 0;
        for(Card card : list){
            cardName += num + ":" + card.getName() + "| ";
            num++;
        }

        return cardName;
    }

    //リストのカード名を読み上げる
    public static void showList(List<Card> list){
        for(Card card : list){
            System.out.print(card.getName() + "| ");
        }
        System.out.println();
    }

    //
}
