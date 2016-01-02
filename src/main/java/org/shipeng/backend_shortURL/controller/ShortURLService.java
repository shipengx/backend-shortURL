package org.shipeng.backend_shortURL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.shipeng.backend_shortURL.dao.*;
import org.shipeng.backend_shortURL.model.URL;
import org.shipeng.backend_shortURL.utils.*;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class ShortURLService {
	
	@Autowired
	@Qualifier("urlDAOImpl")
	private urlDAOImpl urlDAOImpl;  
	
	@Autowired
	@Qualifier("Helper")
	private Helper helper;   
	
	@GET
	@Path("{shortURL}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response redirectToLongURL(@PathParam("shortURL") String shortURL) throws Exception {
		//convert the shortURL to id
		
		long id = helper.shortURLToId(shortURL);
		System.out.println("short url is: " + shortURL);
		
		URL url = urlDAOImpl.get(id);
		System.out.println("long url is: " + url.getLongURL());
		URI targetURIForRedirection = new URI("http://" + url.getLongURL());
	    return Response.seeOther(targetURIForRedirection).build();
	}
	
}
