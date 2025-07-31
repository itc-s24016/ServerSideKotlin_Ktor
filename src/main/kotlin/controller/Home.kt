package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get


fun Route.homeRoute() {
    get("/") {
        //レスポンスを返すときはCall関数を使う (Springのreturn)
        call.respondText("Hello Ktor!")
    }

    /*パスパラメータを受け取るルーティング
    Springの@RequestMappingと同じ*/
    get("/hello/{name}") {
        val name = call.parameters["name"] //パラメータを取得
        call.respondText("Hello $name!")
    }

    //クエリパラメータを受け取るルーティング ?=◯◯のやつ
    get("/hello") {
        val name = call.request.queryParameters["name"] //クエリパラメータを取得
        call.respondText("(qs) Hello $name!")
    }
}
