package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.server.application.*
import io.ktor.server.resources.Resources
import io.ktor.server.routing.*
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller.apiRoute
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller.bookRouting
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller.greetingRoute
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller.homeRoute
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller.userRoute


fun Application.configureRouting() {
    install(Resources) //Resourcesをインストールすることで、型安全なルーティングが可能になる
    routing {
        //KtorはSpring Bootみたいにアノテーションで自動呼び出しがないから、関数の呼び出しは必ず行わないといけない
        homeRoute()
        greetingRoute()
        userRoute()
        bookRouting()
        apiRoute()
    }
}

