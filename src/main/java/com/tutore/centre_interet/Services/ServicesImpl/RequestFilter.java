package com.tutore.centre_interet.Services.ServicesImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class RequestFilter implements Filter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RequestFilter.class);
    private ServiceUtilisateurClient serviceUtilisateurClient;

    public RequestFilter(ServiceUtilisateurClient serviceUtilisateurClient) {
        this.serviceUtilisateurClient = serviceUtilisateurClient;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String header = httpRequest.getHeader("Authorization");
        String token = header.substring(7);
        Boolean val = (Boolean) serviceUtilisateurClient.checkTokenExpiration(token).block().get("success");
        if (val == true) {
            log.info(String.valueOf(val));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            log.info("requete non autorise");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
