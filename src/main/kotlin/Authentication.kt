package jp.ac.it_college.std.s24016.kotlin.ktor.examole

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.form
import io.ktor.server.auth.session
import io.ktor.server.response.respond
import jp.ac.it_college.std.s24016.kotlin.ktor.examole.model.UserSession

fun Application.configureAuthentication() {
    install(Authentication) {
        //フォームログイン認証の設定
        form("auth-form"){
            //今回は固定のユーザにする
            //本来はデータベースから探してきたりする
            //emailをユーザネームとして扱う
            userParamName = "email"
            passwordParamName = "pass"

            //何をチェックして認証するかを設定する
            validate { credentials ->
                //特定のメアド、パスワードだけ認証を許可する(お試し)
                if (credentials.name == "user@example.com" && credentials.password == "user@pass"){
                    //成功 = 何かしらのデータを返す
                    UserIdPrincipal(credentials.name)
                } else {
                    //失敗 = nullを返す
                    null
                }
            }
            challenge {
                //認証できなかったことを表す = 401 Unauthorized を返す
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "メールアドレスまたはパスワードが違います"
                )
            }
        }

        //セッション認証の設定
        session<UserSession>("auth-session"){
            //セッション情報のチェック
            validate { session ->
                session
            }
            challenge {
                // 401 とログインを促すメッセージを返す
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "ログインしてください"
                )
            }
        }
    }
}