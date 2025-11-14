package com.brigid.idp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdPApplication

fun main(args: Array<String>) {
	runApplication<IdPApplication>(*args)
}
