package JavaCard.CardList;

import JavaCard.Player;

public class Spell21 extends Card{
    public Spell21(){
        setName("マーシャルカウンター");
    }

    @Override
    public Card clone(){
        return new Spell21();
    }

}
