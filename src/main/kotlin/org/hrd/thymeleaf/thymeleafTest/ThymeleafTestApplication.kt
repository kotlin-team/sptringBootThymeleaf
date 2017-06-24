package org.hrd.thymeleaf.thymeleafTest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ThymeleafTestApplication{
    companion object{
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(ThymeleafTestApplication::class.java, *args)
        }
    }
}


