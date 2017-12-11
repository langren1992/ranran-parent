package com.ranran.core.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ranran.core.shiro.bind.method.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class StaticController extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     *（1）在启动类App.java类中继承：WebMvcConfigurerAdapter
     *（2）覆盖方法：configureContentNegotiation
     * favorPathExtension表示支持后缀匹配，
     * 属性ignoreAcceptHeader默认为fasle，表示accept-header匹配，defaultContentType开启默认匹配。
     * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
     * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /**
         * 我们刚才在上面说了Spring 会通过URI后缀获取请求格式，当访问/login.html的时候，那么根据当前的URI获取到后缀.html，
         * 那么判断与.html文档类型是否匹配，匹配的话执行相应的解析器。那么我们就会想，我们能够关闭这种默认的后缀匹配规则呢，
         * 既然本文章说是完美解决答案就是肯定的。
         * */
        configurer.favorPathExtension(false);
    }
    /**
     * 添加@CurrentUser 并为它赋值
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new CurrentUserArgumentResolver());
    }

    /**
     * java Long 转 javaScript Number 精度丢失的问题
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}
