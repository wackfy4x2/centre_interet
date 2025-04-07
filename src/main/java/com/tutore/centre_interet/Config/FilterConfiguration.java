package com.tutore.centre_interet.Config;

import com.tutore.centre_interet.Services.ServicesImpl.RequestFilter;
import com.tutore.centre_interet.Services.ServicesImpl.ServiceUtilisateurClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfiguration {
//    @Autowired
//    private ServiceUtilisateurClient serviceUtilisateurClient;

//    @Bean
//    public FilterRegistrationBean<RequestFilter> loggingFilter() {
//        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RequestFilter(serviceUtilisateurClient));
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
}
