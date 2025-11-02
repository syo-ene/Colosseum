package JavaCard.CardList;

import JavaCard.Player;

public class Spell1 extends Card {
    public Spell1(){
        setName("サマーソルト");
        addAttack(1);
    }

    @Override
    public Card clone(){
        return new Spell1();
    }

    @Override
    public void ability(Player user, Player enemy){
        user.move(-1);
    }

}
