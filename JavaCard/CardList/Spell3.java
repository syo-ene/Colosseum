package JavaCard.CardList;

public class Spell3 extends Card{
    public Spell3(){
        setName("エクスプロージョン");
        addAttack(2);
    }

    @Override
    public Card clone(){
        return new Spell3();
    }
}
