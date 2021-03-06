package com.codepath.apps.basictwitter;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "ZMfbetzCUtWShLUbi65pqVKOI";       // Change this
    public static final String REST_CONSUMER_SECRET = "Yr5ZEoK2LQaChph7kec7SyE5OrbuQ4sxuO4r7cVUJ2qyJaQlge"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://cpbasictweets"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
    	String apiUrl = getApiUrl("statuses/home_timeline.json");
    	RequestParams params = new RequestParams();
//    	params.put("since_id", "1");
    	client.get(apiUrl, params, handler);
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler, String page) {
    	String apiUrl = getApiUrl("statuses/home_timeline.json");
    	RequestParams params = new RequestParams();
//    	params.put("since_id", "1");
    	params.put("page", page);
    	client.get(apiUrl, params, handler);
    }
    
    public void postStatus(AsyncHttpResponseHandler handler, String status) {
    	String apiUrl = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams();
    	params.put("status", status);
    	client.post(apiUrl,  params, handler);
    }
    
    public void getAccountInfo(AsyncHttpResponseHandler handler) {
    	String apiUrl = getApiUrl("account/verify_credentials.json");
        client.get(apiUrl, handler);
    }
    
    public void getAccountInfo(AsyncHttpResponseHandler handler, String username) {
    	String apiUrl = getApiUrl("statuses/user_timeline.json");
//    	String apiUrl = getApiUrl("users/show.json");
    	RequestParams params = new RequestParams();
    	params.put("screen_name", username);
    	client.get(apiUrl, params, handler);
    }

	public void getMentionsTimeline(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		client.get(apiUrl, null, handler);	
	}
	
	public void getMentionsTimeline(AsyncHttpResponseHandler handler, String lastTweetID) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		RequestParams params = new RequestParams();
//    	params.put("since_id", "1");
		params.put("max_id", lastTweetID);
    	client.get(apiUrl, params, handler);
	}
	
	public void getUserTimeline(AsyncHttpResponseHandler handler, String screenname) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
    	params.put("screen_name", screenname);
    	client.get(apiUrl, params, handler);
	} 
	
	public void getUserTimelinePage(AsyncHttpResponseHandler handler, String screenname, String page) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
    	params.put("screen_name", screenname);
    	params.put("page", page);
    	client.get(apiUrl, params, handler);
	} 
	
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}