package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route


fun Route.greetingRoute() {
    route("/greeting") { //パス中の共通部分を宣言
        get("/hello") {
            call.respondText("Hello!")
        }

        get("good_morning") {
            call.respondText("Good Morning!")
        }
    }
}
