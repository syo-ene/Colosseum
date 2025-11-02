package JavaCard.CardList;

public class Spell8 extends Card{
    public Spell8(){
        setName("ウィンドアクセル");
        setForward(3);
    }

    @Override
    public Card clone(){
        return new Spell8();
    }
}
