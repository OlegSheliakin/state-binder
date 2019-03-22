package com.olegsheliakin.states

import com.olegsheliakin.statebinder.State

data class SimpleState(
    val nullableProperty: String? = null,
    val nonNullableProperty: String
) : State {

    companion object {
        fun initial(): SimpleState = SimpleState(null, "")
    }
}