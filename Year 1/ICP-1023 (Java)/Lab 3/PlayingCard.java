
public class PlayingCard
{
	private int rank;
	private int suit;
	public final int JACK = 11;
	public final int QUEEN = 12;
	public final int KING = 13;
	public final int ACE = 14;
	public final int CLUBS = 0;
	public final int DIAMONDS = 1;
	public final int HEARTS = 2;
	public final int SPADES = 3;

	//sets values of the card
	public PlayingCard(int rk, int st)
	{
		rank = rk;
		suit = st;
	}

	//returns the rank of the card
	public int getRank()
	{
		return rank;
	}

	//returns the suit of the card
	public int getSuit()
	{
		return suit;
	}

	//converts the numerical rank value to its string counterpart
	private String stringRank(int sRank)
	{
		if (sRank >= 2 && sRank <= 10) {
			return String.valueOf(sRank);
		}
		else if (sRank == JACK) {
			return "Jack";
		}
		else if (sRank == QUEEN) {
			return "Queen";
		}
		else if (sRank == KING) {
			return "King";
		}
		else if (sRank == ACE) {
			return "Ace";
		}
		return "";
	}

	//converts the numerical suit value to its string counterpart
	private String stringSuit(int sSuit)
	{
		if (sSuit == CLUBS) {
			return "Clubs";
		}
		else if (sSuit == DIAMONDS) {
			return "Diamonds";
		}
		else if (sSuit == HEARTS) {
			return "Hearts";
		}
		else if (sSuit == SPADES) {
			return "Spades";
		}
		return "";
	}

	//returns the variables in a string
	public String toString()
	{
		String sRank = stringRank(rank);
		String sSuit = stringSuit(suit);
		return getClass().getName() +
			"[rank=" + sRank +
			", suit=" + sSuit +
			"]";
	}

	//returns the variables in a formatted string
	public String format()
	{
		String sRank = stringRank(rank);
		String sSuit = stringSuit(suit);
		return String.format("%s", sRank) +
			String.format(" of %s", sSuit);
	}

	//tests whether two cards are equal
	public boolean equals(PlayingCard otherCard)
	{
		if (otherCard.getSuit() == suit && otherCard.getRank() == rank) {
			return true;
		}
		return false;
	}
}