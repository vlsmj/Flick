package com.vanjavier.flick.common

@Suppress("ClassName")
sealed class Genre(val name: String) {
    object COMEDY : Genre("Comedy")
    object ACTION_ADVENTURE : Genre("Action & Adventure")
    object ROMANCE : Genre("Romance")
    object SCI_FI_FANTASY : Genre("Sci-Fi & Fantasy")
    object DRAMA : Genre("Drama")
}
