package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    //認証が最初に来るように気をつける
    configureSessions() //セッションを探す
    configureAuthentication() //認証する
    configureRouting() //ルーティング
    configureContentNegotiation() //設定ファイルを読み込む？
    configureDatabase() //データベース接続を設定する (ktor × exposed)
}
