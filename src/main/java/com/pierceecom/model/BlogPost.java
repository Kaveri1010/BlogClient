package com.pierceecom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

		private String id;
		private String title;
		private String content;
	
		public BlogPost() {

		}

			public BlogPost(String id ,String title ,String content) {
			this.content = content;
			this.title = title;
			this.id = id;
		}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

	

	}

