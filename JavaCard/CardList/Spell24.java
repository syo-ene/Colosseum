package JavaCard.CardList;

public class Spell24 extends Card{
    public Spell24(){
        setName("空間圧縮");
        setForward(1);
    }

    @Override
    public Card clone(){
        return new Spell24();
    }
    
}
