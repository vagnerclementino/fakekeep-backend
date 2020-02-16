package com.example.fakekeep.controllers

import com.example.fakekeep.data.Greeting
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong


@RestController
class GreetingController {

    val counter = AtomicLong()

    @GetMapping("/greeting", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) = Greeting(counter.incrementAndGet(), "Hello, $name")
}