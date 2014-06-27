package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	
	private TwitterClient client;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
		populateTimeline();
	}


    public void populateTimeline() {
		client.getUserTimeline(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				addAll(Tweet.fromJSONArray(json));
//				max_id = getItem(tweets.size()-1).getId();
//				lvTweets.onRefreshComplete();
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
			}
		});
	}

}