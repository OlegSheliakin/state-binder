package com.olegsheliakin.statebinder

import java.util.*

typealias ValueSelector<T, V> = (T) -> V
typealias BindAction<V> = (V) -> Unit

/**
 * Created by Oleg Sheliakin on 22.03.2019.
 * Contact me by email - olegsheliakin@gmail.com
 */
class StateBinder<T : State> private constructor() {

    private var prev: T? = null
    private var current: T? = null

    private val nullableBinderActions: LinkedList<Binder<T, Any?>> = LinkedList()
    private val nonNullBinderActions: LinkedList<Binder<T, Any>> = LinkedList()

    companion object {
        fun <STATE : State> create(): StateBinder<STATE> {
            return StateBinder()
        }
    }

    /**
     * It applies current state even if the previous one is the same.
     */
    fun applyCurrentState() {
        if (prev != null) {
            prev = null
            applyState()
        }
    }

    /**
     * Applies new state
     */
    fun newState(state: T) {
        prev = this.current
        current = state
        applyState()
    }

    /**
     * Binds nullable property
     */
    @Suppress("UNCHECKED_CAST")
    fun <V> bindNullable(selector: ValueSelector<T, V?>, action: BindAction<V?>) {
        val localAction: (Any?) -> Unit = action as (Any?) -> Unit
        nullableBinderActions.add(Binder(selector, localAction))
    }

    /**
     * Binds non nullable property
     */
    @Suppress("UNCHECKED_CAST")
    fun <V : Any> bind(selector: ValueSelector<T, V>, action: BindAction<V>) {
        val localAction: (Any) -> Unit = action as (Any) -> Unit
        nonNullBinderActions.add(Binder(selector, localAction))
    }

    private fun applyState() {
        nonNullBinderActions.forEach {
            diff(it.valueSelector, it.action::invoke)
        }
        nullableBinderActions.forEach {
            diffNullable(it.valueSelector, it.action::invoke)
        }
    }

    private fun <V> diffInternal(selector: (T) -> V?, action: (V?, V?) -> Unit) {
        val localPrev = prev
        val localCurrent = current
        val prevValue = if (localPrev == null) null else selector(localPrev)
        val currentValue = if (localCurrent == null) null else selector(localCurrent)
        action(prevValue, currentValue)
    }

    private fun <V> diffNullable(selector: ValueSelector<T, V?>, action: BindAction<V?>) {
        diffInternal(selector) { prevValue, currentValue ->
            if (prev == null || prevValue != currentValue) {
                action(currentValue)
            }
        }
    }

    private fun <V : Any> diff(selector: ValueSelector<T, V>, action: BindAction<V>) {
        diffInternal(selector) { prevValue, currentValue ->
            currentValue?.let {
                if (prev == null || prevValue != currentValue) {
                    action(currentValue)
                }
            }
        }
    }

    private data class Binder<T, V>(
        val valueSelector: ValueSelector<T, V>,
        val action: BindAction<V>
    )
}