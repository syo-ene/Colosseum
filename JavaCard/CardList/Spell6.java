package JavaCard.CardList;

public class Spell6 extends Card{
    public Spell6(){
        setName("ヘルフレア");
        addAttack(5);
    }

    @Override
    public Card clone(){
        return new Spell6();
    }
}
