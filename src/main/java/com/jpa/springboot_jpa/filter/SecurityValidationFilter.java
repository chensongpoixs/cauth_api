/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.jpa.springboot_jpa.filter;


//import static cn.edu.sgu.www.knife4j.system.Constants.TOKEN_HEADER;

//import cn.edu.sgu.www.knife4j.dto.ExceptionResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.springboot_jpa.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//import static cn.edu.sgu.www.knife4j.system.Constants.X_TOKEN_HEADER;
import static java.util.Collections.emptyList;
import static java.util.Collections.list;
import static java.util.function.Function.identity;

/**
 * Filter created to validate if this application has access to requested model
 */

@Component
@RequiredArgsConstructor
@Profile("!integration-test")
@Order(1)
@Slf4j

public class SecurityValidationFilter implements Filter {

    @Autowired
    private final UserServiceImpl userServiceimpl;


//    private final ResponseExceptionHandler handler;
    private final ObjectMapper objectMapper;

//    private final ModelStatisticCacheProvider modelStatisticCacheProvider;

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain
    ) throws IOException, ServletException {
        val httpRequest = (HttpServletRequest) servletRequest;
        val httpResponse = (HttpServletResponse) servletResponse;
//        httpRequest.g

//        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "X-Custom-Header");
//        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
//        Access-Control-Allow-Headers: X-Custom-Header
//        Access-Control-Allow-Credentials: true
//        log.info("==============================================doFilter==============================================" + httpRequest.getMethod().toLowerCase());
        String header_Origin = "*";
        if (httpRequest.getHeader("Origin") != null)
        {
            if (httpRequest.getHeader("Origin").charAt( httpRequest.getHeader("Origin").length()-1) != '/')
            {
                header_Origin = httpRequest.getHeader("Origin");
            }
            else
            {
                header_Origin = httpRequest.getHeader("Origin").substring(0, httpRequest.getHeader("Origin").length()-1);
            }
        }
        httpResponse.setHeader("Access-Control-Allow-Origin", header_Origin);
        if (httpRequest.getMethod().toLowerCase().equals( "options"))
        {
            httpResponse.setStatus(204);
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
            httpResponse.setHeader("Access-Control-Request-Headers", "Content-Type");
//            httpResponse.setHeader("Access-Control-Request-Headers", "Content-Type, Access-token, x-api-key, x-token");
            httpResponse.setHeader("Access-Control-Allow-Headers", "DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range,x-api-key,x-token");
            httpResponse.setHeader("Access-Control-Max-Age", "1728000"); // ,Authorization,X-Auth-Token

           httpResponse.setHeader("Content-Length", "0");
            httpResponse.setHeader("Content-Type", "text/plain; charset=UTF-8");

            return;
        }
        String requestURI = httpRequest.getRequestURI();
        if (!requestURI.matches("^/(api/v1/netauth).*$"))
        {
            val headersMap =
                    list(httpRequest.getHeaderNames()).stream()
                            .collect(Collectors.<String, String, List<String>>toMap(
                                    identity(),
                                    header -> list(httpRequest.getHeaders(header))
                            ));

            log.info("-->url = " + requestURI);
//            var apiKey = headersMap.getOrDefault(X_TOKEN_HEADER, emptyList());
//            if (!apiKey.isEmpty())
//            {
//                val key = apiKey.get(0);
//                try {
////                    if (key.length() < 36) {
////                        throw new IllegalArgumentException("UUID length is incorrect");
////                    }
////                    UUID.fromString(key);
//                }
//                catch (Exception e)
//                {
//                    log.info(" x-token failed !!! " + apiKey);
//                    ResponseEntity<ExceptionResponseDto>  responseEntity = new ResponseEntity<>(HttpStatus.valueOf(26));
//                    responseEntity.getBody().setMessage("not find x-token failed !!! ");
//                    buildException(httpResponse, responseEntity);
//                    return;
//                }
//            } else {
//                log.info("not  find x-token failed !!!");
//                ResponseEntity<ExceptionResponseDto>  responseEntity = new ResponseEntity<>(HttpStatus.valueOf(26));
//                responseEntity.getBody().setMessage("not find x-token failed !!! ");
//                buildException(httpResponse, responseEntity);
//
//                return;
//            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @SneakyThrows
    private void buildException(final HttpServletResponse response, final ResponseEntity<?> responseEntity) {
        response.setStatus(responseEntity.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().append(objectMapper.writeValueAsString(responseEntity.getBody()));
        //response.getWriter().flush();
        //don't need to flush or close the writer
    }

}
