package com.pierceecom.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pierceecom.model.BlogPost;
import com.pierceecom.service.BlogPostService;

/**
 * Servlet implementation class BlogClient
 */
public class BlogClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BlogPostService bps = new BlogPostService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String method = (String) request.getParameter("methodName");
		String msgId = (String) request.getParameter("MsgId");

		HttpSession session = request.getSession();
		session.setAttribute("EVNT", "GET");
		if (method.equals("Get Message") && (msgId == null) || msgId.equals("")) {
			List<BlogPost> list = new ArrayList<>();
			list = bps.getAllBlogPost();
			request.setAttribute("BlogList", list);
			request.setAttribute("ReturnType", "BlogList");
		} else if (method.equals("Get Message")) {
			BlogPost blogPost = new BlogPost();
			blogPost = bps.GetBlogPostById(msgId);
			request.setAttribute("BlogPost", blogPost);
			request.setAttribute("ReturnType", "BlogPost");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");

		BlogPost blogPost = new BlogPost();
		String method = request.getParameter("methodName");
		String putMethod = request.getParameter("saveButton");
		String deleteMethod = request.getParameter("delete");
		String content = request.getParameter("message");
		String title = request.getParameter("title");
		String id =request.getParameter("id");
		
		System.out.println(deleteMethod);
		if (method != null && method.equals("Post") && content != null && title != null) {
			 blogPost = new BlogPost(id, title, content);
			blogPost = bps.AddBlogPost(blogPost);
			request.setAttribute("BlogPost", blogPost);
			request.setAttribute("ReturnType", "BlogPost");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		else if (putMethod !=null && putMethod.equals("Save") ) {
	
			blogPost.setId(request.getParameter("bid"));
			blogPost.setTitle(request.getParameter("btitle"));
			blogPost.setContent(request.getParameter("bcontent"));

			blogPost = bps.EditBlogPost(blogPost);
			request.setAttribute("BlogPost", blogPost);
			request.setAttribute("ReturnType", "BlogPost");
		}
		else if (deleteMethod !=null && deleteMethod.equals("Delete") ) {
			System.out.println(request.getParameter("bid") +"   1");
			int status = bps.DeleteBlogPost(request.getParameter("bid"));
			System.out.println(status);
			if (status ==200) {
				request.setAttribute("Status", "200");	
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}


