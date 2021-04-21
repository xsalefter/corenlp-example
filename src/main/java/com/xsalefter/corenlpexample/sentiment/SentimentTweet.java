package com.xsalefter.corenlpexample.sentiment;

public class SentimentTweet {

    private final String tweet;
    private final String sentiment;

    public SentimentTweet(String tweet, String sentiment) {
        this.tweet = tweet;
        this.sentiment = sentiment;
    }

    @Override
    public String toString() {
        return "SentimentTweet {\n" +
                "  'sentiment':'" + sentiment + "', \n" +
                "  'tweet':'" + tweet + "'\n" +
                "}";
    }
}
