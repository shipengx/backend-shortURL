package org.shipeng.backend_shortURL.server;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.StaticHttpHandler;



public class start {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Initialize Grizzly HttpServer
        HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("grizzly2", "localhost", 8888);
        server.addListener(listener);
        
        // Initialize and add Spring-aware Jersey resource
        WebappContext ctx = new WebappContext("ctx", "/");
        final ServletRegistration reg = ctx.addServlet("spring", new SpringServlet());
        reg.addMapping("/*");
        ctx.addContextInitParameter("contextConfigLocation", "classpath:spring-context.xml");  //we use this line to find spring-context.xml to find beans.
        
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.addListener("org.springframework.web.context.request.RequestContextListener");
        ctx.deploy(server);
        

        try {
            server.start();
            
            System.out.println("Press enter to stop the server...");
            System.in.read();
        } finally {
            server.shutdownNow();
        }




	}

}
