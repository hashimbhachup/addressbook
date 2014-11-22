package com.hashim.interview.problem.addressbook.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseFilter implements ContainerResponseFilter{

	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
			requestContext.getHeaders();
			//TODO add filter
		
	}

}
