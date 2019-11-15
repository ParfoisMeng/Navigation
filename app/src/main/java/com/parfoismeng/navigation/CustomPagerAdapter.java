package com.parfoismeng.navigation;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
import java.util.Random;

public class CustomPagerAdapter extends PagerAdapter {
    //上下文
    private Context mContext;
    //数据
    private List<String> mData;

    /**
     * 构造函数
     * 初始化上下文和数据
     *
     * @param context
     * @param list
     */
    public CustomPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mData = list;
    }

    /**
     * 返回要滑动的VIew的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * 1.将当前视图添加到container中
     * 2.返回当前View
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView tv = new TextView(mContext);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setGravity(Gravity.CENTER);
        tv.setText(mData.get(position));

        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        tv.setBackgroundColor(ranColor);

        container.addView(tv);
        return tv;
    }

    /**
     * 从当前container中删除指定位置（position）的View
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View) object);
    }

    /**
     * 确定页视图是否与特定键对象关联
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}