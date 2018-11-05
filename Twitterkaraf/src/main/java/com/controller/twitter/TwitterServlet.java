package com.controller.twitter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.twitter.TwitterDAO;

import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class TwitterServlet
 */
@WebServlet("/TwitterServlet")
public class TwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			TwitterDAO twitterdao = new TwitterDAO();
			List<String> outputList = new ArrayList<String>();
			if(request.getParameter("name").equals("gettimeline")) {
				outputList = twitterdao.homeTimeline();
				request.setAttribute("name","Timeline");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("followerslist")) {
				String twitterid = request.getParameter("twitterid");
				outputList = twitterdao.followersList(twitterid);
				request.setAttribute("name","Followers");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("statusupdate")) {
				String statusUpdate = request.getParameter("status");
				outputList = twitterdao.statusUpdate(statusUpdate);
				request.setAttribute("name","Status Update");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("getfavoritetweets")) {
				outputList = twitterdao.getFavoriteTweets();
				request.setAttribute("name","Favorite Tweets");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("blockuser")) {
				String twitterid = request.getParameter("twitterid");
				outputList = twitterdao.blockUser(twitterid);
				request.setAttribute("name","Blocked User");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("blocklist")) {
				outputList = twitterdao.getBlockList();
				request.setAttribute("name","Blocked Accounts");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("gettrends")) {
				System.out.println("gettrends");
				outputList = twitterdao.getTrends();
				request.setAttribute("name","Available Trends");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getParameter("name").equals("getlanguage")) {
				System.out.println("getprivacy");
				outputList = twitterdao.getLanguages();
				request.setAttribute("name","Languages Supported");
				request.getSession().setAttribute("response",outputList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}

	}
}
