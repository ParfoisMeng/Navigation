package com.parfoismeng.navigationlib.utils

import android.content.res.Resources

private val scale = Resources.getSystem().displayMetrics.density
private val fontScale = Resources.getSystem().displayMetrics.scaledDensity

fun dp2px(dpValue: Int): Int {
    return (dpValue * scale + 0.5f).toInt()
}

fun sp2px(spValue: Int): Int {
    return (spValue * fontScale + 0.5f).toInt()
}