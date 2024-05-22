package com.projet.demo.dto;

public class CheckoutSessionResponse {
	 private String id;
	    private String url;
	    
	    
	    
		public CheckoutSessionResponse() {
			super();
			// TODO Auto-generated constructor stub
		}



		public CheckoutSessionResponse(String id, String url) {
			super();
			this.id = id;
			this.url = url;
		}



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getUrl() {
			return url;
		}



		public void setUrl(String url) {
			this.url = url;
		}



		@Override
		public String toString() {
			return "CheckoutSessionResponse [id=" + id + ", url=" + url + "]";
		}

}
