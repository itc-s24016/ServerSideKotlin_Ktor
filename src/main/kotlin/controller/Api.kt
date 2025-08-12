package jp.ac.it_college.std.s24016.kotlin.ktor.examole.controller

import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.request.receive
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.resources.post
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

/*
メンバーIDを指定して、該当するメンバー情報を取得するAPIエンドポイントを定義します。
該当しない場合は404を返す
 */
fun Route.apiRoute() {
    get<Api.Member.Select.Id> { params ->
        val entity = transaction {
            MemberEntity.findById(params.id)
        }?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond(
            Member(
            entity.id.value,
            entity.name
            )
        )
    }

    post<Api.Member.Insert> {
        val request = call.receive<InsertRequest>()
        val newMemberEntity = transaction {
            MemberEntity.new {
                name = request.name
            }
        }
        call.respond(
            HttpStatusCode.Created,
            InsertResponse(
                201,
                Member(
                    newMemberEntity.id.value,
                    newMemberEntity.name
                )
            )
        )
    }
}
// アノテーションを使用して、APIのエンドポイントを定義
@Resource("/api")
class Api {
    @Resource("/member")
    class Member(val api: Api = Api()) {
        @Resource("/select")
        class Select(val member: Member = Member()) {
            @Resource("/{id}")
            class Id(val select: Select = Select(), val id: Int)
        }

        @Resource("/insert")
        class Insert(val member: Member = Member())
    }
}

// リクエストとレスポンスのデータクラスを定義
@Serializable
data class InsertRequest(
    val name: String
)

@Serializable
data class Member(
    val id: Int,
    val name: String
)

@Serializable
data class InsertResponse(
    val status: Int,
    val createdMember: Member
)

// Exposed関連 Entityを作成する
object Members : IntIdTable("member") {
    val name = varchar("name", 32)
}

class MemberEntity(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<MemberEntity>(Members)

    var name by Members.name
}