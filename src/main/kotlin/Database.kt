package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database

/*Ktorアプリケーションでデータベース接続を設定する
  configureDatabase：アプリケーションの設定ファイルから
  ・データベースのURL
  ・ドライバー
  ・ユーザー名
  ・パスワード　を取得し、Exposedライブラリを使ってデータベースへ接続する*/
fun Application.configureDatabase() {
    Database.connect(
        url = environment.config.property("database.url").getString(),
        driver = environment.config.property("database.driver").getString(),
        user = environment.config.property("database.user").getString(),
        password = environment.config.property("database.password").getString()
    )
}