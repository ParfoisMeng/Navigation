package com.parfoismeng.navigation

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.parfoismeng.navigationlib.widget.NavigationView
import kotlinx.android.synthetic.main.activity_fg.*

class FGActivity : AppCompatActivity() {
    private val itemList by lazy {
        listOf(
                NavigationView.NavItemModel(
                        getDrawable2(R.drawable.ic_launcher_foreground),
                        getDrawable2(R.drawable.ic_launcher_background),
                        "000"),
                NavigationView.NavItemModel(
                        getDrawable2(R.drawable.ic_launcher_foreground),
                        getDrawable2(R.drawable.ic_launcher_background),
                        "111"),
                NavigationView.NavItemModel(getDrawable2(R.drawable.ic_launcher_foreground), ignoreTabSwitch = true),
                NavigationView.NavItemModel(
                        getDrawable2(R.drawable.ic_launcher_foreground),
                        getDrawable2(R.drawable.ic_launcher_background),
                        "222"),
                NavigationView.NavItemModel(
                        getDrawable2(R.drawable.ic_launcher_foreground),
                        getDrawable2(R.drawable.ic_launcher_background),
                        "333"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg)

        navViewFG.addItems(itemList)
        navViewFG.setOnItemChangeListener(object : NavigationView.ItemChangeListener {
            override fun onItemChange(position: Int): Boolean {
                val model: NavigationView.NavItemModel = itemList[position]
                val str = "text = ${model.navText}\nposition = $position"
                Toast.makeText(this@FGActivity, str, Toast.LENGTH_SHORT).show()
                return true
            }
        })

        val fgList = listOf(MyFragment("000"), MyFragment("111"), MyFragment("222"), MyFragment("333"))
        navViewFG.bindFragments(R.id.flContent, fgList)

        navViewFG.setCurrentItem(0)
    }

    private fun Context.getDrawable2(id: Int): Drawable? {
        return ContextCompat.getDrawable(this, id)
    }
}
