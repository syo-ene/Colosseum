package JavaCard.CardList;

public class Spell4 extends Card{
    public Spell4(){
        setName("ナチュラルコントロール");
        addAttack(3);
    }

    @Override
    public Card clone(){
        return new Spell4();
    }
}
