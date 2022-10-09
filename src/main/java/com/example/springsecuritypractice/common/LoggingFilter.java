package com.example.springsecuritypractice.common;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

public class LoggingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(((HttpServletRequest)servletRequest).getRequestURI());

		filterChain.doFilter(servletRequest, servletResponse);

		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());
	}
}
