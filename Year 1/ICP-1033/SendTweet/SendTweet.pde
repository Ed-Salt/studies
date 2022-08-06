import twitter4j.conf.*;
import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.api.*;
import java.util.*;

Twitter twitter;

void setup()
{
    size(800,600);

    //Keys & tokens
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setOAuthConsumerKey("pwAt5EoMD6yCyibMZdXYdRxA5");
    cb.setOAuthConsumerSecret("YJNQncwz97mKf0WFjssheW4XUuCD7FGwGIR4Mppqm3LC6TyIEu");
    cb.setOAuthAccessToken("1103586018198994944-05Bq7iTh03YpR82G0Mn28bGMwkLRRX");
    cb.setOAuthAccessTokenSecret("9IclaaXwFdjkmnwlnNp14yWfH9AIEVTB7aBIiLAsOoybK");

    TwitterFactory tf = new TwitterFactory(cb.build());

    twitter = tf.getInstance();
}

void draw()
{

}

void tweet()
{
    try
    {
        //Message to tweet
        Status status = twitter.updateStatus("This is a tweet sent from Processing!");
        System.out.println("Status updated to [" + status.getText() + "].");
    }
    catch (TwitterException te)
    {
        System.out.println("Error: "+ te.getMessage());
    }
}

void keyPressed()
{
  tweet();
}
