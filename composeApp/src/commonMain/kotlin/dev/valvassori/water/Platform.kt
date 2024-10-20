package dev.valvassori.water

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform