package JavaCard.CardList;

public class Spell7 extends Card{
    public Spell7(){
        setName("ドリルショット");
        addAttack(1);
        addAttack(2);
    }

    @Override
    public Card clone(){
        return new Spell7();
    }
}
