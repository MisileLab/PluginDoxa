package com.chizstudio.misilelaboratory.event

import org.bukkit.Server
import kotlin.concurrent.timer

class AnnouncementChat {
    private var announcementnumber: Int? = null
    fun announcementtimer(server: Server) {
       timer(period = (1000L * 300L), initialDelay = (1000L * 300L)) {
           announcement(server)
       }
    }

    private fun announcement(server: Server) {
        when (announcementnumber) {
            null -> {
                announcementnumber = 1
                broadcast(server, "&c&l[ 공지사항 ] &f마법 부여는 오직 책만 가능합니다.")
            }
            1 -> {
                announcementnumber = 2
                broadcast(server, "&c&l[ 공지사항 ] &f돈을 수표로 꺼내는 방법은 /수표 [금액] [개수] 입니다.")
            }
            2 -> {
                announcementnumber = 3
                broadcast(server, "&c&l[ 공지사항 ] &f상점은 /상점 을 통해 입장 가능합니다.")
            }
            3 -> {
                announcementnumber = 4
                broadcast(server, "&c&l[ 공지사항 ] &f아이템을 상점 란에 넣으시면 증발할 위험이 있으니 조심하시기 바랍니다.")
            }
            4 -> {
                announcementnumber = 5
                broadcast(server, "&c&l[ 공지사항 ] &f일부 아이템은 구매를 통해서만 얻을 수 있습니다.")
            }
            5 -> {
                announcementnumber = 6
                broadcast(server, "&c&l[ 공지사항 ] &f해당 서버에서는 주민의 이용이 불가합니다.")
            }
            6 -> {
                announcementnumber = 7
                broadcast(server, "&c&l[ 공지사항 ] &f/명령어 를 통해 명령어의 종류를 확인할 수 있습니다.")
            }
            7 -> {
                announcementnumber = 8
                broadcast(server, "&c&l[ 공지사항 ] &f엘리트 몬스터나 보스 몬스터를 사냥하면 현상금을 획득하실 수 있습니다.")
            }
            8 -> {
                announcementnumber = 9
                broadcast(server, "&c&l[ 공지사항 ] &f이름표의 이름변경은 무료입니다.")
            }
            9 -> {
                announcementnumber = null
                broadcast(server, "&c&l[ 공지사항 ] &f엘리트 몬스터는 등장하고 5분 뒤 사라집니다.")
            }
        }
    }
}

fun broadcast(server: Server, chat: String) {
    for (i in server.onlinePlayers) {
        i.chat(chat)
    }
}