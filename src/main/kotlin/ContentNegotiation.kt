package jp.ac.it_college.std.s24016.kotlin.ktor.examole
//設定ファイル
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true //改行とインデント含めて見やすくする
            ignoreUnknownKeys = true
            namingStrategy = JsonNamingStrategy.SnakeCase //プロパティ名をスネークケースに変換する
        })
    }
}