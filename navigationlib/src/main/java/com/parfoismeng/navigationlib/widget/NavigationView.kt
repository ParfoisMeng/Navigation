package com.parfoismeng.navigationlib.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.parfoismeng.navigationlib.R
import com.parfoismeng.navigationlib.utils.dp2px
import com.parfoismeng.navigationlib.utils.sp2px
import com.parfoismeng.navigationlib.widget.NavigationView.NavItemView.Companion.buildNavItem

@Suppress("unused")
class NavigationView constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    private val llNavButton by lazy { LinearLayout(context) }
    private val viewLine by lazy { View(context) }

    /**
     * 容器布局ID
     */
    @IdRes
    private var containerLayoutId: Int = 0

    // ------ NavigationView使用 ------
    /**
     * 分隔线颜色 默认#E6E6E6
     */
    private var dividerLineColor = Color.parseColor("#E6E6E6")

    // ------  NavItemView使用  ------
    /**
     * 图片高 默认0(即使用权重属性)
     */
    var imageHeight = 0
    /**
     * 图片高 权重 默认0.5
     */
    var imageSizeWeight = 0.5F
    /**
     * 图文间距高 默认1dp
     */
    var spaceHeight = dp2px(1)
    /**
     * 文字 颜色 普通状态 默认#666666
     */
    var navTextColorNormal = Color.parseColor("#666666")
    /**
     * 文字 颜色 选中状态 默认#333333
     */
    var navTextColorSelect = Color.parseColor("#333333")
    /**
     * 文字 大小 默认12sp
     */
    var navTextSize = sp2px(12)

    /**
     * Item对应的对象集合
     */
    private val itemModelList = ArrayList<NavItemModel>()
    /**
     * Item按钮集合
     */
    private val itemViewList = ArrayList<NavItemView>()
    /**
     * 忽略切换的按钮下标集合
     */
    private val ignoreItemIndexList = ArrayList<Int>()
    /**
     * Tab点击事件
     */
    private var onItemClickListener: ItemClickListener? = null

    /**
     * 绑定的ViewPager
     */
    private var viewPager: ViewPager? = null
    /**
     * Fragment集合
     */
    private var fgList: List<Fragment>? = null
    /**
     * Fragment管理器
     */
    private var fgManager: FragmentManager? = null

    /**
     * 当前选中Item
     */
    private var currentItem = -1

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.NavigationView)
        dividerLineColor = array.getColor(R.styleable.NavigationView_dividerLineColor, dividerLineColor)
        imageHeight = array.getDimensionPixelOffset(R.styleable.NavigationView_imageHeight, imageHeight)
        imageSizeWeight = array.getFloat(R.styleable.NavigationView_imageSizeWeight, imageSizeWeight)
        spaceHeight = array.getDimensionPixelOffset(R.styleable.NavigationView_spaceHeight, spaceHeight)
        navTextColorNormal = array.getColor(R.styleable.NavigationView_navTextColorNormal, navTextColorNormal)
        navTextColorSelect = array.getColor(R.styleable.NavigationView_navTextColorSelect, navTextColorSelect)
        navTextSize = array.getDimensionPixelOffset(R.styleable.NavigationView_navTextSize, navTextSize)
        array.recycle()

        // 导航按钮
        addView(llNavButton.apply {
            // 设置宽高
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        })
        // 分隔线
        addView(viewLine.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(1))
            setBackgroundColor(dividerLineColor)
        })
    }

    fun addItems(vararg items: NavItemModel) {
        addItems(items.toList())
    }

    fun addItems(items: List<NavItemModel>) {
        items.forEachIndexed { index, model ->
            buildNavItem(model).also { navItemView ->
                navItemView.setOnClickListener { onItemClick(index) }
                llNavButton.addView(navItemView)
                if (!model.ignoreTabSwitch) {
                    itemViewList.add(navItemView)
                } else {
                    // 记录忽略切换的按钮下标
                    ignoreItemIndexList.add(index)
                }
            }
        }

        itemModelList.addAll(items)
    }

    /**
     * 上一次页面切换时间戳
     */
    private var lastItemChangeTimeStamp = 0L

    private fun onItemClick(position: Int) {
        // 防止短时间(200ms)内多次切换
        val nowTime = System.currentTimeMillis()
        if (nowTime - lastItemChangeTimeStamp <= 200) {
            return
        }
        lastItemChangeTimeStamp = nowTime

        if (onItemClickListener?.invoke(position) == true) {
            if (!itemModelList[position].ignoreTabSwitch) {
                setCurrentItem(getRealPositionIgnore(position))
            }
        }
    }

    /**
     * 变更选中Item
     */
    fun setCurrentItem(position: Int) {
        if (currentItem == position) return

        itemViewList.forEachIndexed { index, navItemView ->
            if (position == index) {
                navItemView.isSelect = true

                viewPager?.currentItem = position
                switchFragment(position)
            } else {
                navItemView.isSelect = false
            }
        }

        currentItem = position
    }

    /**
     * 获取真实下标（去除或加上忽略切换的Item）
     */
    private fun getRealPositionIgnore(position: Int, isAdd: Boolean = false): Int {
        var ignoreSum = 0
        if (isAdd) {
            ignoreItemIndexList.forEach {
                if (position >= it) {
                    ignoreSum++
                }
            }
        } else {
            ignoreItemIndexList.forEach {
                if (position > it) {
                    ignoreSum--
                }
            }
        }
        return position + ignoreSum
    }

    /**
     * 绑定ViewPager
     */
    fun bindViewPager(vp: ViewPager) {
        if (viewPager == vp) return

        viewPager = vp
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                onItemClick(getRealPositionIgnore(position, true))
            }
        })
    }

    /**
     * 绑定Fragment
     * @param fgs Fragment集合 (请确保集合长度与非ignoreTabSwitch的Item长度一致)
     * @param container 容器ID
     * @param manager Fragment管理器 默认依据父级Context获取 (若父级非FragmentActivity会报错)
     * @param defaultSelect 默认选中的item (默认第0个，为null不选中)
     */
    fun bindFragments(@IdRes container: Int, fgs: List<Fragment>, manager: FragmentManager = (context as FragmentActivity).supportFragmentManager, defaultSelect: Int? = 0) {
        containerLayoutId = container
        fgList = fgs
        fgManager = manager

        defaultSelect?.let { setCurrentItem(it) }
    }

    /**
     * 切换Fragment
     */
    private fun switchFragment(position: Int) {
        if (fgManager == null || fgList.isNullOrEmpty()) {
            return
        }

        val tran = fgManager?.beginTransaction()
        fgList?.forEachIndexed { index, fg ->
            if (position == index) {
                if (!fg.isAdded) {
                    tran?.add(containerLayoutId, fg)
                }
                tran?.show(fg)
            } else {
                tran?.hide(fg)
            }
        }
        tran?.commit()
    }

    /**
     * 设置点击事件监听
     */
    fun setOnItemClickListener(onItemClick: ItemClickListener?) {
        onItemClickListener = onItemClick
    }

    open class NavItemModel(
            /**
             * 图片 普通状态
             */
            var imageDrawableNormal: Drawable? = null,
            /**
             * 图片 选中状态
             */
            var imageDrawableSelect: Drawable? = null,
            /**
             * 文字 内容
             */
            var navText: String? = "",
            /**
             * 忽略页面切换
             */
            val ignoreTabSwitch: Boolean = false
    )

    private class NavItemView constructor(context: Context) : LinearLayout(context) {
        private val ivNavItem by lazy { ImageView(context) }
        private val spaceNavItem by lazy { Space(context) }
        private val tvNavItem by lazy { TextView(context) }

        /**
         * 图片 普通状态
         */
        private var imageDrawableNormal: Drawable? = null
        /**
         * 图片 选中状态
         */
        private var imageDrawableSelect: Drawable? = null
        /**
         * 文字 内容
         */
        private var navText: String? = ""

        /**
         * 图片高 默认wrap_content
         */
        private var imageHeight: Int = 0
        /**
         * 图片高 权重 默认0.5
         */
        private var imageSizeWeight: Float = 0.5F
        /**
         * 图文间距高 默认1dp
         */
        private var spaceHeight: Int = dp2px(1)
        /**
         * 文字 颜色 普通状态 默认#666666
         */
        private var navTextColorNormal: Int = Color.parseColor("#666666")
        /**
         * 文字 颜色 选中状态 默认#333333
         */
        private var navTextColorSelect: Int = Color.parseColor("#333333")
        /**
         * 文字 大小 默认12sp
         */
        private var navTextSize: Int = sp2px(12)

        init {
            // 宽、高、权重
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
            // 内部权重总数
            weightSum = 1F
            // 垂直模式
            orientation = VERTICAL
            // 内部居中
            gravity = Gravity.CENTER

            // 图片
            addView(ivNavItem.apply { adjustViewBounds = true })
            // 图文间隔
            addView(spaceNavItem)
            // 文字
            addView(tvNavItem.apply {
                // 宽高自适应
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                // 去除文字内边距
                includeFontPadding = false
            })
        }

        var isSelect: Boolean = false
            set(value) {
                // 状态没变直接return
                if (field == value) return

                // 改变按钮状态 图片/字色
                if (value) {
                    ivNavItem.setImageDrawable(imageDrawableSelect)
                    tvNavItem.setTextColor(navTextColorSelect)
                } else {
                    ivNavItem.setImageDrawable(imageDrawableNormal)
                    tvNavItem.setTextColor(navTextColorNormal)
                }
                field = value
            }

        private fun update(model: NavItemModel, imageHeight: Int = 0, imageSizeWeight: Float = 0.5F, spaceHeight: Int = dp2px(1), navTextColorNormal: Int = Color.parseColor("#666666"), navTextColorSelect: Int = Color.parseColor("#333333"), navTextSize: Int = sp2px(12)) {
            imageDrawableNormal = model.imageDrawableNormal
            imageDrawableSelect = model.imageDrawableSelect
            navText = model.navText

            this.imageHeight = imageHeight
            this.imageSizeWeight = imageSizeWeight
            this.spaceHeight = spaceHeight
            this.navTextColorNormal = navTextColorNormal
            this.navTextColorSelect = navTextColorSelect
            this.navTextSize = navTextSize

            // 图片 宽高
            ivNavItem.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, imageHeight).apply {
                if (imageHeight == 0) weight = imageSizeWeight
            }
            // 图文间隔 宽高
            spaceNavItem.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, spaceHeight)
            // 文字 颜色/大小
            tvNavItem.apply {
                setTextColor(navTextColorNormal)
                setTextSize(TypedValue.COMPLEX_UNIT_PX, navTextSize.toFloat())
            }

            // 如果图片都为null则隐藏图片
            if (imageDrawableNormal == null && imageDrawableSelect == null) {
                ivNavItem.visibility = View.GONE
            } else {
                ivNavItem.visibility = View.VISIBLE
                ivNavItem.setImageDrawable(imageDrawableNormal)
            }
            // 如果文字为null或""则隐藏间距和文字
            if (navText == null || navText == "") {
                spaceNavItem.visibility = View.GONE
                tvNavItem.visibility = View.GONE
            } else {
                spaceNavItem.visibility = View.VISIBLE
                tvNavItem.visibility = View.VISIBLE
                tvNavItem.text = navText
            }
        }

        companion object {
            fun NavigationView.buildNavItem(model: NavItemModel): NavItemView {
                return NavItemView(context).apply {
                    update(model, imageHeight, imageSizeWeight, spaceHeight, navTextColorNormal, navTextColorSelect, navTextSize)
                }
            }
        }
    }
}

/**
 * 点击事件监听
 * 入参 Item对应的下标
 * 返参 true下一步，false拦截
 */
typealias ItemClickListener = (Int) -> Boolean