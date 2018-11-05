package com.dao.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@SuppressWarnings({ "deprecation", "resource" })
public class TwitterDAO {

	public OAuthConsumer getOAuthConsumer() throws IOException {
		String consumerKeyStr = " ";
		String consumerSecretStr = " ";
		String accessTokenStr = " ";
		String accessTokenSecretStr = " ";
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
		return oAuthConsumer;
	}
	


private HttpResponse executeHttpGet(String apiUrl) throws OAuthMessageSignerException,
	OAuthExpectationFailedException, OAuthCommunicationException, IOException {
HttpGet httprequest = new HttpGet(apiUrl);
getOAuthConsumer().sign(httprequest);
HttpClient client = new DefaultHttpClient();
HttpResponse httpresponse = client.execute(httprequest);
int statusCode = httpresponse.getStatusLine().getStatusCode();
System.out.println(statusCode + ":" + httpresponse.getStatusLine().getReasonPhrase());
return httpresponse;
}

private HttpResponse executeHttpPost(String apiUrl) throws OAuthMessageSignerException,
OAuthExpectationFailedException, OAuthCommunicationException, IOException {
HttpPost httprequest = new HttpPost(apiUrl);
getOAuthConsumer().sign(httprequest);
HttpClient client = new DefaultHttpClient();
HttpResponse httpresponse = client.execute(httprequest);
int statusCode = httpresponse.getStatusLine().getStatusCode();
System.out.println(statusCode + ":" + httpresponse.getStatusLine().getReasonPhrase());
return httpresponse;
}

	public List<String> followersList(String twitterid) {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/followers/list.json" + "?screen_name=" + twitterid;
			HttpResponse apiResponse = executeHttpGet(apiUrl);
			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONObject jsonobject = new JSONObject(EntityUtils.toString(apiResponse.getEntity()));
				JSONArray jsonArray = (JSONArray) jsonobject.get("users");
				for (int i = 0; i < jsonArray.length() && i < 20; i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					String displayText = (String) object.get("name") + " : " + (String) object.get("screen_name");
					twitterresponse.add(displayText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;
	}
	public List<String> homeTimeline() {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/statuses/home_timeline.json" + "?count=10";
																								

			HttpResponse apiResponse = executeHttpGet(apiUrl);

			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONArray jsonArray = new JSONArray(EntityUtils.toString(apiResponse.getEntity()));
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					JSONObject userObject = (JSONObject) jsonObject.get("user");
					String displayText = (String) userObject.get("screen_name") + " : "
							+ (String) jsonObject.get("text"); 
					twitterresponse.add(displayText); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;

	}
	public List<String> statusUpdate(String status) {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/statuses/update.json";
			apiUrl = apiUrl + "?status=" + status.replace(" ", "%20"); 

			HttpResponse apiResponse = executeHttpPost(apiUrl);
			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				twitterresponse.add("Tweeted: "+ status );
			} else {
				twitterresponse.add("failed to tweet");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;
	}
	public List<String> getFavoriteTweets() {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/favorites/list.json" + "?count=10";
																							

			HttpResponse apiResponse = executeHttpGet(apiUrl);

			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONArray jsonArray = new JSONArray(EntityUtils.toString(apiResponse.getEntity()));
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					JSONObject userObject = (JSONObject) jsonObject.get("user");
					String displayText = (String) userObject.get("screen_name") + " : "
							+ (String) jsonObject.get("text"); 
					twitterresponse.add(displayText); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;

	}
	public List<String> blockUser(String twitterid) {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/blocks/create.json" + "?screen_name=" + twitterid;

			HttpResponse apiResponse = executeHttpPost(apiUrl);
			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				twitterresponse.add("Blocked user: "+ twitterid );
			} else {
				twitterresponse.add("Blocking failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;
	}
	public List<String> getTrends() {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/trends/available.json"; 
																			

			HttpResponse apiResponse = executeHttpGet(apiUrl);

			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONArray jsonArray = new JSONArray(EntityUtils.toString(apiResponse.getEntity()));
				for (int i = 1; i < jsonArray.length() && i <= 20; i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					String displayText = (String) object.get("country") + " : " + (String) object.get("url");
					twitterresponse.add(displayText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;

	}

	public List<String> getLanguages() {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/help/languages.json";

			HttpResponse apiResponse = executeHttpGet(apiUrl);

			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONArray jsonArray = new JSONArray(EntityUtils.toString(apiResponse.getEntity()));
				for (int i = 0; i < jsonArray.length() && i < 20; i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					String displayText = (String) object.get("name");
					twitterresponse.add(displayText);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;
	}
	public List<String> getBlockList() {
		List<String> twitterresponse = new ArrayList<String>();
		try {
			String apiUrl = "https://api.twitter.com/1.1/blocks/list.json";
			HttpResponse apiResponse = executeHttpGet(apiUrl);
			if (200 == apiResponse.getStatusLine().getStatusCode()) {
				JSONObject jsonobject = new JSONObject(EntityUtils.toString(apiResponse.getEntity()));
				JSONArray jsonArray = (JSONArray) jsonobject.get("users");
				for (int i = 0; i < jsonArray.length() && i < 20; i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					String displayText = (String) object.get("name") + " : " + (String) object.get("screen_name");
					twitterresponse.add(displayText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterresponse;
	}
}
