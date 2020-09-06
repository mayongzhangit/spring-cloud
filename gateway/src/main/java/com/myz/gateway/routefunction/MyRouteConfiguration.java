package com.myz.gateway.routefunction;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/27 16:13
 * @email 2641007740@qq.com
 */
@Configuration
public class MyRouteConfiguration {

    /**
     * 比较挫的方式
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> fallbackFunction(){
        return new RouterFunction<ServerResponse>() {
            @Override
            public Mono<HandlerFunction<ServerResponse>> route(ServerRequest request) {
                // AbstractErrorWebExceptionHandler.getRoutingFunction
                String path = request.path();
                if (!StringUtils.equals("/fallback-function",path)){
                    return Mono.empty();
                }

                List<MediaType> acceptedMediaTypes = request.headers().accept();
                acceptedMediaTypes.remove(MediaType.ALL);
                MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
                if (acceptedMediaTypes.stream().anyMatch(MediaType.TEXT_HTML::isCompatibleWith)){

                    HandlerFunction<ServerResponse> htmlHandlerFunction = new HandlerFunction<ServerResponse>() {
                        @Override
                        public Mono<ServerResponse> handle(ServerRequest request) {

                            Resource resource = new DefaultResourceLoader().getResource(ResourceLoader.CLASSPATH_URL_PREFIX);
                            try {
                                resource = resource.createRelative("fallback/hystrix-fallback.html");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (!resource.exists()){
                                return Mono.empty();
                            }

                            Mono<ServerResponse> htmlResponse = ServerResponse.status(HttpStatus.OK)
                                    .contentType(MediaType.TEXT_HTML)
                                    .body(BodyInserters.fromResource(resource));
                            return htmlResponse;
                        }
                    };

                    return Mono.just(htmlHandlerFunction);
                }

                HandlerFunction<ServerResponse> jsonHandlerFunction = new HandlerFunction<ServerResponse>() {
                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        Map<String,Object> errorMap = new HashMap<>();
                        errorMap.put("code","fallback");
                        errorMap.put("message","熔断了");

                        Mono<ServerResponse> jsonResponse = ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(fromObject(errorMap));
                        return jsonResponse;
                    }
                };
                return Mono.just(jsonHandlerFunction) ;
            }
        };
    }

    /**
     *
     * @return
     */
    @Bean
    RouterFunction<ServerResponse> home() {
        return route(GET("/home"), request -> ok().body(fromObject("Home page")));
    }
}
