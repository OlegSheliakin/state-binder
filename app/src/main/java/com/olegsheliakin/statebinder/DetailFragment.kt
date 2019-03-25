package com.olegsheliakin.statebinder

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        stateBinder.newState(DetailState("First name", null))
    }

}
