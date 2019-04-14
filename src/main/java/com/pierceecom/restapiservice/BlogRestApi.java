package com.pierceecom.restapiservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.json.JSONArray;





import com.pierceecom.model.BlogPost;

public class BlogRestApi {
	Client client = ClientBuilder.newClient();
	WebTarget baseTarget = client.target("http://localhost:8080/blog-web/posts");
	WebTarget singleMessageTarget = baseTarget.path("{blogPostId}");
	/* Method to get all Blog from the records */
	public BlogRestApi(){
		
	}
public BlogRestApi (WebTarget baseTarget, WebTarget singleMessageTarget)
{
	this.baseTarget =baseTarget;
	this.singleMessageTarget = singleMessageTarget;
}

public List<BlogPost> GetBlogPosts(){
	Response response =baseTarget.request(MediaType.APPLICATION_JSON).get();
	String s = response.readEntity(String.class);
	JSONArray arr = new JSONArray(s);
	List<BlogPost> list = new ArrayList<>();
	for(int i = 0; i < arr.length(); i++){
		BlogPost b = new BlogPost (arr.getJSONObject(i).getString("id"),arr.getJSONObject(i).getString("title"),arr.getJSONObject(i).getString("content"));
		list.add(b);
	}
	return list;
}
public	BlogPost GetBlogPostById(String id){
	Response response =singleMessageTarget
			.resolveTemplate("blogPostId", id)
			.request(MediaType.APPLICATION_JSON).get();
	BlogPost arr = response.readEntity(BlogPost.class);
	return arr;
}
public BlogPost PostBlogPost(BlogPost blogPost){
	Response postResponse = baseTarget
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.json(blogPost));
		if (postResponse.getStatus() != 201) {
			System.out.println("Error");
		}
		BlogPost createdPost = postResponse.readEntity(BlogPost.class);

	return createdPost;
}

public BlogPost PutBlogPost(BlogPost blogPost){
	Response putResponse = baseTarget
			.request(MediaType.APPLICATION_JSON)
			.put(Entity.json(blogPost));
		if (putResponse.getStatus() != 200) {
			System.out.println("Error");
		}
		BlogPost updatedObject = putResponse.readEntity(BlogPost.class);

	return updatedObject;
}

public int DeleteBlogPost(String id){
	System.out.println("Inside DeleteBlogPost"+id);
	Response deleteResponse = singleMessageTarget
			.resolveTemplate("blogPostId", id)
			.request()
			.delete();
		return deleteResponse.getStatus();


}
	
	

}
