package JavaCard.CardList;

public class Spell5 extends Card{
    public Spell5(){
        setName("パワー");
        addAttack(4);
    }

    @Override
    public Card clone(){
        return new Spell5();
    }
}
