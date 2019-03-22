package com.olegsheliakin.statebinder

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val internalState = MutableLiveData<MainState>()

    val state: LiveData<MainState> = internalState

    var text: String = ""
        set(value) {
            if (field != value) {
                field = value
                reduce { it.copy(errorText = null) }
            }
        }

    init {
        internalState.value = MainState("", null)
    }

    fun loadText() {
        reduce {
            it.copy(label = text)
        }
    }

    fun initError() {
        reduce {
            it.copy(errorText = "Error")
        }
    }

    private fun reduce(reducer: (MainState) -> MainState) {
        internalState.value?.let {
            internalState.value = reducer(it)
        }
    }

}