package Twitterkaraf;


import java.util.List;
import org.junit.Test;

import com.dao.twitter.TwitterDAO;

import org.junit.Assert;
/**
 * Unit test for simple App.
 */

public class AppTest 
    
{
  
    
	TwitterDAO twitterdao = new TwitterDAO();
	
	@Test
	public void testHomeTimeline() {
		List<String> response = twitterdao.homeTimeline();
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testFavoriteTweets() {
		List<String> response = twitterdao.getFavoriteTweets();
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testFollowersList() {
		List<String> response = twitterdao.followersList("abc");
		Assert.assertNotNull(response);
	}
	@Test
	public void testTrendsAvailable() {
		List<String> response = twitterdao.getTrends();
		Assert.assertNotNull(response);
	}
	@Test
	public void testStatusUpdate() {
		List<String> response = twitterdao.statusUpdate("abc");
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testBlockUser() {
		List<String> response = twitterdao.blockUser("abc");
		Assert.assertNotNull(response);
	}
	@Test
	public void testBlockList() {
		List<String> response = twitterdao.getBlockList();
		Assert.assertNotNull(response);
	}
		
	@Test
	public void testLanguagesSupported() {
		List<String> response = twitterdao.getLanguages();
		Assert.assertNotNull(response);
	}
	
	
    
    
}
