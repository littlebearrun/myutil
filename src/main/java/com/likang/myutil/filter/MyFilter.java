package com.likang.myutil.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {
		@Override
		public void destroy() {
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;
			long i = System.currentTimeMillis();
			filterChain.doFilter(srequest, sresponse);
			long j = System.currentTimeMillis();
			System.err.println(request.getRequestURI() + " 请求耗时:" + (j - i));
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
		}
    }