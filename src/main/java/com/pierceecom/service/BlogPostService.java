package com.pierceecom.service;

import java.util.List;



import com.pierceecom.model.BlogPost;
import com.pierceecom.restapiservice.BlogRestApi;


public class BlogPostService {
	
	BlogRestApi blogRestApi =new BlogRestApi();
	
	public BlogPostService(){
	}
	
	/* Method to get all Blog from the records */
	public List<BlogPost> getAllBlogPost() {		 
		return (blogRestApi.GetBlogPosts());
	}
	
	public int DeleteBlogPost(String id){
		System.out.println(id + "  2");
		int deleteResponse = blogRestApi.DeleteBlogPost(id);
			return deleteResponse;


}

	public BlogPost GetBlogPostById(String id){
		BlogPost blogPost = new BlogPost();
		blogPost =blogRestApi.GetBlogPostById(id);
		return blogPost;
}
	public BlogPost AddBlogPost(BlogPost blogPost){
		blogPost =blogRestApi.PostBlogPost(blogPost);
		return blogPost;
}
	public BlogPost EditBlogPost(BlogPost blogPost){
		blogPost =blogRestApi.PutBlogPost(blogPost);
		return blogPost;
}
	

	}
