package com.su.accounting.controller;

import com.su.accounting.entity.web.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("v1.0/greeting")
    public Greeting sayHello(@RequestParam("name") String name) {
        LinkedList<String> l = new LinkedList<>();
        Stack<String> s =new Stack<>();
        return new Greeting(counter.incrementAndGet(),String.format("Hello, %s",name));
    }
}
