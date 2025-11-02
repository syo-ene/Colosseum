package JavaCard.CardList;

import java.util.Scanner;

import JavaCard.Player;

public class Spell12 extends Card{
    public Spell12(){
        setName("等価交換");
    }

    @Override
    public Card clone(){
        return new Spell12();
    }

    @Override
    public void ability(Player user, Player enemy){

        int cardNum;
        do{
            System.out.print(new Move1().getName() + "ではない奪うカードを選べ");
            cardNum = new Scanner(System.in).nextInt();
        }while(enemy.getHand().get(cardNum).getName() == new Move1().getName());
        user.addHand(enemy.removeHand(cardNum));
    }
}
