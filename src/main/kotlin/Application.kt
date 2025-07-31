package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureContentNegotiation() //追記
}
