package com.olegsheliakin.statebinder

import com.olegsheliakin.states.SimpleState
import org.junit.Assert.*
import org.junit.Test

class StateBinderTest {

    private val subject: StateBinder<SimpleState> = StateBinder.create()

    @Test
    fun testInitial() {
        var counter = 0
        subject.bind(SimpleState::nonNullableProperty) { counter++ }
        subject.bind(SimpleState::nullableProperty) { counter++ }

        val state = SimpleState.initial()
        subject.newState(state)

        assertEquals(2, counter)
    }

    @Test
    fun testActionWhenNonNullPropertyChanged() {
        var counter = 0

        subject.bind(SimpleState::nonNullableProperty) {
            counter++
        }

        subject.newState(SimpleState(null, ""))

        subject.newState(SimpleState(null, ""))

        assertEquals(1, counter)
    }

    @Test
    fun testActionWhenNonNullPropertyChangedTwoTimes() {
        var counter = 0
        subject.bind(SimpleState::nonNullableProperty) {
            counter++
        }

        //changed first time
        subject.newState(SimpleState(null, ""))

        //no changes
        subject.newState(SimpleState(null, ""))

        //changed second time
        subject.newState(SimpleState(null, "diff"))

        assertEquals(2, counter)
    }

    @Test
    fun testActionWhenNullPropertyChangedTwoTimes() {
        var counter = 0
        subject.bind(SimpleState::nullableProperty) {
            counter++
        }

        //changed first time
        subject.newState(SimpleState(null, ""))

        //no changes
        subject.newState(SimpleState(null, ""))

        //changed second time
        subject.newState(SimpleState("", ""))

        assertEquals(2, counter)
    }

}