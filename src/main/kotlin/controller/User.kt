package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.resources.Resource
import io.ktor.server.resources.get
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route

fun Route.userRoute() {
    //ここのgetは、server.resources.getのget
    get<User.Id> { params ->
        val id = params.id
        call.respondText("id = $id")
    }
    get<User.Detail.Id> { params ->
        val id = params.id
        call.respondText("detail id = $id")
    }
}

@Resource("/user")
class User {
    @Resource("/{id}")
    class Id(val parent: User = User(), val id: Long)


    @Resource("/detail")
    class Detail(val parent: User = User()) {
        @Resource("/{id}")
        class Id(val parent: Detail, val id: Long)
    }
}