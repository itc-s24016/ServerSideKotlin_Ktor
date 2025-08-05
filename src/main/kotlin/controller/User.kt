package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.resources.*
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.model.UserSession

fun Route.userRoute() {
    //何に対する認証なのか
    authenticate("auth-form"){
        post<User.Login>{ params ->
            //認証情報が取得できる
            val principal = call.principal<UserIdPrincipal>()
                ?: return@post call.respond(HttpStatusCode.Unauthorized)

            call.sessions.set(UserSession(12, principal.name))
            //Hello メールアドレス　が返ってくるはず
            call.respondText("Helllo ${principal.name}")

        }
    }

    authenticate("auth-session"){
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
}

@Resource("/user")
class User {
    @Resource("/login")
    class Login(val parent: User = User())

    @Resource("/{id}")
    class Id(val parent: User = User(), val id: Long)


    @Resource("/detail")
    class Detail(val parent: User = User()) {
        @Resource("/{id}")
        class Id(val parent: Detail, val id: Long)
    }
}