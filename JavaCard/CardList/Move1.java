package JavaCard.CardList;

public class Move1 extends Card{
    public Move1(){
        setName("ステップ");
        setForward(1);
    }

    @Override
    public Card clone(){
        return new Move1();
    }
}
