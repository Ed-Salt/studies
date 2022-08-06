import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Pack
{
	private int cardCount = 52;
	PlayingCard[] cards = new PlayingCard[cardCount];

	//creates enough cards to fill the pack
	public Pack()
	{
		int count = 0;
		for (int i = 0;i <= 3;i++) {
			for (int j = 2;j <= 14;j++)	{
				cards[count] = new PlayingCard(j, i);
				count ++;
			}
		}
	}

	//randomly shuffles the order of the cards
	public void shuffle()
	{
		Random rand = ThreadLocalRandom.current();
	    for (int i = cardCount-1;i > 1;i--)
    	{
      		int num = rand.nextInt(i-1);
      		PlayingCard temp = cards[num];
      		cards[num] = cards[i];
      		cards[i] = temp;
    	}
	}

	//returns the cards' variables in a string
	public String toString()
	{
		String packString = "Pack[";
		for (int i = 0;i < cardCount;i++) {
			packString = (packString + cards[i].toString());
			if (i < cardCount-1) {
				packString = (packString + ", ");
			}
			else {
				packString = (packString + "]");
			}
		}
		return packString;
	}
}