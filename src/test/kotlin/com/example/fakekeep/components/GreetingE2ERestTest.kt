package com.example.fakekeep.components


import com.example.fakekeep.FakekeepApplication
import com.example.fakekeep.data.Greeting
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [FakekeepApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingE2ERestTest {

    @LocalServerPort
    private val port: Int = 0

    @Test
    @Throws(Exception::class)
    fun `should return content equals 'Hello, World' when name is empty`() {
        val greeting:Greeting = When {
            get("http://localhost:$port/greeting?name=")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            `as`(Greeting::class.java)
        }
        assertThat(greeting.content, `is`("Hello, World"))
    }


    @Test
    @Throws(Exception::class)
    fun `should return content equals 'Hello, Kotlin' when name is 'Kotlin'`() {
        val name = "Kotlin"
        val greeting:Greeting = When {
            get("http://localhost:$port/greeting?name=$name")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            `as`(Greeting::class.java)
        }
        assertThat(greeting.content, `is`("Hello, $name"))
    }
}
