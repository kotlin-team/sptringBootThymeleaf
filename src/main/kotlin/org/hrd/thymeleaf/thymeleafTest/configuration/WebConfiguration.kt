package org.hrd.thymeleaf.thymeleafTest.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by ratha on 25-Jul-17.
 */
@Configuration
class WebConfiguration  : WebMvcConfigurerAdapter(){
    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
    }


}