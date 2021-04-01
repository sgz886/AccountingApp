package com.su.accounting.controller;

import com.su.accounting.entity.web.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * a hello controller that shows hello,{username}.
 */
@RestController
public class HelloController {
    private final AtomicLong counter = new AtomicLong();

    /**
     * return hello, value.
     *
     * @param name value get from http://url/v1.0/greeting/name={value}
     * @return hello, value.
     */
    @GetMapping("v1.0/greeting")
    public Greeting sayHello(@RequestParam("name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s", name));
    }
}
