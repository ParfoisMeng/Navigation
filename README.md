# Navigation
更简更轻的导航 Bar 控件，支持绑定 ViewPager 或者 Fragments 。支持特殊化 Item （加号、发布等）！  [![JitPack](https://jitpack.io/v/ParfoisMeng/Navigation.svg)](https://jitpack.io/#ParfoisMeng/Navigation)

- - - - - 

### 直接上预览
| ViewPager | Fragment | Demo 下载 |
| :---: | :---: | :---: |
| <img src="https://github.com/ParfoisMeng/Navigation/raw/master/screenshot/navigation_with_vp.gif" width="260px"/> | <img src="https://github.com/ParfoisMeng/Navigation/raw/master/screenshot/navigation_with_fg.gif" width="260px"/> | <img src="https://github.com/ParfoisMeng/Navigation/raw/master/demo/demo_qr.png" width="260px"/><br><br>[Demo下载](https://raw.githubusercontent.com/ParfoisMeng/Navigation/master/demo/demo.apk) |

### 引用
 - 请将 last-version 替换为最新版本号 - [![JitPack](https://jitpack.io/v/ParfoisMeng/Navigation.svg)](https://jitpack.io/#ParfoisMeng/Navigation)
```
    // 1.添加 jitpack 仓库
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    // 2.添加项目依赖（ last-version 替换为最新版本号）
    dependencies {
        implementation 'com.github.ParfoisMeng:Navigation:last-version'
    }
```

### API
``` kotlin
    // 主要方法

    // 添加 Item 项目，可变参数 [vararg] 或 直接传入 Model 集合
    fun addItems(vararg items: NavItemModel)
    fun addItems(items: List<NavItemModel>)

    /**
     * 设置点击事件监听
     */
    fun setOnItemClickListener(onItemClick: ItemClickListener)
    /**
     * 点击事件监听
     * 入参 Item对应的下标
     * 返参 true下一步，false拦截
     */
    typealias ItemClickListener = (Int) -> Boolean

    /**
     * 绑定ViewPager
     */
    fun bindViewPager(vp: ViewPager)
    /**
     * 绑定Fragment
     * @param container 容器ID
     * @param fgs Fragment集合 (请确保集合长度与非ignoreTabSwitch的Item长度一致)
     * @param manager Fragment管理器 默认依据父级Context获取 (若父级非FragmentActivity会报错)
     * @param defaultSelect 默认选中的item (默认第0个，为null不选中)
     */
    fun bindFragments(@IdRes container: Int, fgs: List<Fragment>, manager: FragmentManager = (context as FragmentActivity).supportFragmentManager, defaultSelect: Int? = 0)

    /**
     * 变更选中Item
     */
    fun setCurrentItem(position: Int)

    // Model 属性
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
             * 忽略页面切换 （对于特殊化Item(如加号/发布)不需要切换Tab时，请设为true）
             */
            val ignoreTabSwitch: Boolean = false
    )
```
``` xml
    <!-- XML属性注释 -->

    <declare-styleable name="NavigationView">
        <!-- 分隔线颜色 默认#E6E6E6 -->
        <attr name="dividerLineColor" format="color" />
        <!-- 图片高 默认0(即使用权重属性) -->
        <attr name="imageHeight" format="dimension" />
        <!-- 图片高 权重 默认0.5 -->
        <attr name="imageSizeWeight" format="float" />
        <!-- 图文间距高 默认1dp -->
        <attr name="spaceHeight" format="dimension" />
        <!-- 文字 颜色 普通状态 默认#666666 -->
        <attr name="navTextColorNormal" format="color" />
        <!-- 文字 颜色 选中状态 默认#333333 -->
        <attr name="navTextColorSelect" format="color" />
        <!-- 文字 大小 默认12sp -->
        <attr name="navTextSize" format="dimension" />
    </declare-styleable>
```

### 更新
* Tab 点击事件默认 true - 1.1.1
* 绑定 Fragment 时不会再初始即全 Add - 1.1.0
* 简单更新 - 1.0.1
* 初版发布 - 1.0.0

### 计划
* 暂无

### 支持
劳烦各位大佬给个 Star 让我出去好装B行嘛！

### 其他
已使用 <b>996 License</b> ，为程序员发声，为自己发声。

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
