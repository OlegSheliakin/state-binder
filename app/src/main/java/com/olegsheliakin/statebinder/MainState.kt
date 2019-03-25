package com.olegsheliakin.statebinder

data class MainState(
    val label: String,
    val errorText: String?
) : State