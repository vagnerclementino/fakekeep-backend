package com.example.fakekeep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FakekeepApplication

fun main(args: Array<String>) {
	runApplication<FakekeepApplication>(*args)
}
