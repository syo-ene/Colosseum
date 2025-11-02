package JavaCard.CardList;

public class Spell23 extends Card{
    public Spell23(){
        setName("リバーススタート");
    }

    @Override
    public Card clone(){
        return new Spell23();
    }
}
