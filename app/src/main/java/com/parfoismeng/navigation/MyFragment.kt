package com.parfoismeng.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.android.synthetic.main.fragment_my.view.*

/**
 * author : ParfoisMeng
 * time   : 2019-11-15
 * desc   : ...
 */
class MyFragment(private val textStr: String = "") : Fragment() {
    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(TAG, "------ $textStr ------")
            Log.d(TAG, "LifecycleEventObserver Event: $event")
            Log.d(TAG, "------ ------ ------")
        })
    }

    private val TAG = "MyFragment $textStr"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onAttach: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onCreate: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onCreateView: $textStr")
        Log.d(TAG, "------ ------ ------")
        return View.inflate(context, R.layout.fragment_my, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onViewCreated: $textStr")
        Log.d(TAG, "------ ------ ------")
        view.tvFragment.text = textStr
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onActivityCreated: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onStart: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onResume: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onPause: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onStop: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onDestroyView: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onDestroy: $textStr")
        Log.d(TAG, "------ ------ ------")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "------ $textStr ------")
        Log.d(TAG, "onDetach: $textStr")
        Log.d(TAG, "------ ------ ------")
    }
}
