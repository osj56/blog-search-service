package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchcommon.annotations.InterfaceHandler;
import com.internet.blogsearchcommon.enums.CompanyName;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class BlogSearchInterfaceManager {
    final ApplicationContext applicationContext;
    private Map<CompanyName, BlogSearchInterfaceHandler> serviceInterfaceHandlerMap = new HashMap<>();

    @PostConstruct
    public void init(){
        for(BlogSearchInterfaceHandler blogSearchInterfaceHandler : applicationContext.getBeansOfType(BlogSearchInterfaceHandler.class).values()){
            InterfaceHandler annotation = blogSearchInterfaceHandler.getClass().getAnnotation(InterfaceHandler.class);

            serviceInterfaceHandlerMap.put(annotation.value(), blogSearchInterfaceHandler);
        }
    }

    public BlogSearchResponse requestBlogSearchApi(CompanyName companyName, BlogSearchRequest blogSearchRequest){
        return serviceInterfaceHandlerMap.get(companyName).requestBlogSearchApi(companyName, blogSearchRequest);
    }
}
