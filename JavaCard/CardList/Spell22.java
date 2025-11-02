package JavaCard.CardList;

public class Spell22 extends Card{
    public Spell22(){
        setName("スペルカウンター");
    }

    @Override
    public Card clone(){
        return new Spell22();
    }

}
