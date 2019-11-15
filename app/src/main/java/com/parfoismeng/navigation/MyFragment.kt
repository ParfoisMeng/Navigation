package com.parfoismeng.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my.view.*

/**
 * author : ParfoisMeng
 * time   : 2019-11-15
 * desc   : ...
 */
class MyFragment(private val textStr: String = "") : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_my, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tvFragment.text = textStr
    }
}
