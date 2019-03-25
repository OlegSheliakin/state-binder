package com.olegsheliakin.statebinder

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        return@lazy ViewModelProviders.of(this@MainFragment)[MainViewModel::class.java]
    }

    private val stateBinder: StateBinder<MainState> = StateBinder.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let(stateBinder::newState)
        })

        stateBinder.apply {
            applyCurrentState()
            bind(MainState::label) {
                tvLabel.text = it
            }
            bindNullable(MainState::errorText) {
                etText.error = it
            }
        }
    }

    private fun initUi() {
        btnSetText.setOnClickListener {
            viewModel.loadText()
        }

        btnSetErrorState.setOnClickListener {
            viewModel.initError()
        }

        etText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.text = s?.toString() ?: ""
            }

        })

        btnOpenDetail.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, DetailFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}
