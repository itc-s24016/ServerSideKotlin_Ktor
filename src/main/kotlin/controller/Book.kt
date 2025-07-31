package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.resources.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable


fun Route.bookRouting() {
    // ここに書籍関連のルーティングを定義します
    // 例: GET /book/{id} で特定の書籍情報を取得する
    get<Book.Detail.BookId> { params ->
        call.respond(
            BookResponse(params.bookId,"Kotlin入門","コトリン太郎")
        )

        post <Book.Register>{ params ->
            val body = call.receive<RegisterRequest>()
            call.respondText("registered. id=${body.id}, title=${body.title}, author=${body.author}")
        }
    }
}


@Resource("/book")
class Book {
    @Resource("/detail")
    class Detail(val parent: Book = Book()) {
        @Resource("/{bookId}")
        class BookId(val parent: Detail, val bookId: Long)
    }

    @Resource("/register")
    class Register (val parent: Book = Book())
}


@Serializable
data class BookResponse(
    val id: Long,
    val title: String,
    val author: String,
)

@Serializable
data class RegisterRequest(
    val id: Long,
    val title: String,
    val author: String
)