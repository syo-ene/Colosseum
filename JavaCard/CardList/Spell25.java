package JavaCard.CardList;

import JavaCard.Player;

public class Spell25 extends Card{
    public Spell25(){
        setName("re do");
    }

    @Override
    public Card clone(){
        return new Spell25();
    }

    @Override
    public void ability(Player user, Player enemy){
        user.move(-6);

        for (Card card : user.getRedoCard())
        user.addHand(card);
    }
}