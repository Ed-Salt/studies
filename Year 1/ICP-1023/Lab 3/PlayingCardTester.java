public class PlayingCardTester
{
	public static void main(String[] args) 
	{
		PlayingCard playingCard1 = new PlayingCard(14, 3);
		System.out.println(playingCard1.getRank());
		System.out.println(playingCard1.getSuit());
		System.out.println(playingCard1.toString());
		System.out.println(playingCard1.format());
		PlayingCard playingCard2 = new PlayingCard(7, 1);
		System.out.println("Is the " + playingCard1.format() + " equal to the " + playingCard2.format() + "?");
		System.out.println(playingCard1.equals(playingCard2));
		PlayingCard playingCard3 = new PlayingCard(14, 3);
		System.out.println("Is the " + playingCard1.format() + " equal to the " + playingCard3.format() + "?");
		System.out.println(playingCard1.equals(playingCard3));
	}
}