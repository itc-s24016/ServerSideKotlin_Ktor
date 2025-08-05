package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.maxAge
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.model.UserSession
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds

fun Application.configureSessions() {
    //セッション管理とcookieの連携
    install(Sessions) {
        //セッション( 命名:user_session )をメモリに保存する宣言
        cookie<UserSession>("user_session", SessionStorageMemory()) {
            //全てのパスに対して
            cookie.path = "/"
            //セッション保存期間の設定：1時間30分間
            cookie.maxAge = 1.hours + 30.seconds
        }
    }
}