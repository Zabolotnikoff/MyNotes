package com.example.mynotes.domain.entities

import java.io.Serializable

data class Note(
    val id: Int = 0,
    val header: String = "",
    val text: String = ""

) : Serializable