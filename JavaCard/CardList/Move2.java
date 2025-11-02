package JavaCard.CardList;

public class Move2 extends Card{
    public Move2(){
        setName("ダッシュ");
        setForward(2);
    }

    @Override
    public Card clone(){
        return new Move2();
    }
}