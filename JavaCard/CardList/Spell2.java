package JavaCard.CardList;

public class Spell2 extends Card{
    public Spell2(){
        setName("アクアスラッシュ");
        addAttack(1);
    }

    @Override
    public Card clone(){
        return new Spell2();
    }
}
