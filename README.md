# Navigation
更简更轻的导航Bar控件，支持绑定ViewPager或者Fragments。支持特殊化Item（加号、发布等）！  [![JitPack](https://jitpack.io/v/ParfoisMeng/Navigation.svg)](https://jitpack.io/#ParfoisMeng/Navigation)

- - - - - 

### 直接上预览
| ViewPager | Fragment | Demo下载 |
| :---: | :---: | :---: |
| <img src="https://github.com/ParfoisMeng/Navigation/raw/master/screenshot/navigation_with_vp.gif" width="260px"/> | <img src="https://github.com/ParfoisMeng/Navigation/raw/master/screenshot/navigation_with_fg.gif" width="260px"/> | <img src="https://github.com/ParfoisMeng/Navigation/raw/master/demo/demo_qr.gif" width="260px"/><br><br>[Demo下载](https://raw.githubusercontent.com/ParfoisMeng/Navigation/master/demo/demo.apk) |

### 引用
 - 请将last-version替换为最新版本号 - [![JitPack](https://jitpack.io/v/ParfoisMeng/Navigation.svg)](https://jitpack.io/#ParfoisMeng/Navigation)
```
    // 1.添加jitpack仓库
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    // 2.添加项目依赖（last-version替换为最新版本号）
    dependencies {
        implementation 'com.github.ParfoisMeng:Navigation:last-version'
    }
```

### API
``` kotlin
    // 主要方法

    // 添加Item项目，可变参数[vararg] 或 直接传入Model集合
    addItems(vararg items: NavItemModel)
    addItems(items: List<NavItemModel>)

    // 设置事件监听
    setOnItemChangeListener(listener: ItemChangeListener?)

    // 绑定ViewPager
    bindViewPager(vp: ViewPager)
    bindFragments(@IdRes container: Int, fgs: List<Fragment>, manager: FragmentManager = (context as FragmentActivity).supportFragmentManager)

    // 选中Item
    setCurrentItem(position: Int)
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
* 初版发布 - 1.0.0

### 计划
* 暂无

### 支持
劳烦各位大佬给个Star让我出去好装B行嘛！

### 其他
已使用<b>996 License</b>，为程序员发声，为自己发声。

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
