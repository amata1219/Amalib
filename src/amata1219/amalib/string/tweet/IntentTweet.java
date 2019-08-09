package amata1219.amalib.string.tweet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import amata1219.amalib.string.StringTemplate;

public class IntentTweet {

	public static IntentTweet write(String text){
		return new IntentTweet(text);
	}

	private final String text;
	private final List<String> hashtags = new ArrayList<>();
	private String url;

	public IntentTweet(String text){
		this.text = text;
	}

	public IntentTweet addHashtag(String hashtag){
		hashtags.add(hashtag);
		return this;
	}

	public IntentTweet setURL(String url){
		this.url = url;
		return this;
	}

	public String build(){
		String text = "text=" + encode(this.text);
		String hashtags = this.hashtags.isEmpty() ? "" : "&hashtags=" + encode(String.join(",", this.hashtags));
		String url = this.url != null ? "&url=" + this.url : "";
		return StringTemplate.apply("https://twitter.com/intent/tweet?$0$1$2", text, hashtags, url);
	}

	private String encode(String text){
		try {
			return new URLCodec("UTF-8").encode(text);
		} catch (EncoderException e) {
			e.printStackTrace();
		}
		return "";
	}

}
