package com.olegsheliakin.statebinder

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val stateBinder: StateBinder<DetailState> = StateBinder.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateBinder.applyCurrentState()

        stateBinder.apply {
            bind(DetailState::firstName) {
                tvFirstName.text = it
            }

            bindNullable(DetailState::lastName) {
                tvLastName.text = it
            }
        }

        stateBinder.newState(DetailState("First name", "Last name"))
    }

}
