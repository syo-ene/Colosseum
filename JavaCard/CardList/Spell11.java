package JavaCard.CardList;

import JavaCard.Player;

public class Spell11 extends Card{
    public Spell11(){
        setName("スパイダーウェブ");
    }

    @Override
    public Card clone(){
        return new Spell11();
    }

    @Override
    public void ability(Player user, Player enemy){
        enemy.move(-2);
    }
}
