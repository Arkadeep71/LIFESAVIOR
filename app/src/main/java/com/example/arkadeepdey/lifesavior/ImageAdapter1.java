package com.example.arkadeepdey.lifesavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class ImageAdapter1 extends PagerAdapter {
    Context mContext;
    ImageAdapter1(Context context){this.mContext=context;}


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== object;

    }
    private int[] sliderImageId=new int[]{
            R.drawable.s0,R.drawable.s1,
            R.drawable.s3,R.drawable.s4,
            R.drawable.s5,R.drawable.s6,
            R.drawable.s7,R.drawable.s8,
            R.drawable.s9, R.drawable.s10,
            R.drawable.s11, R.drawable.s12,
            R.drawable.s3, R.drawable.s14,
            R.drawable.s15, R.drawable.s16,
    };
    @Override
    public  Object instantiateItem(ViewGroup container,int position){
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    imageView.setImageResource(sliderImageId[position]);
        container.addView(imageView,0);
        return imageView;
    }
    @Override
    public  void  destroyItem(ViewGroup container,int position,Object object){
    container.removeView((ImageView)object);}
    @Override
    public  int getCount() {return  sliderImageId.length;}

}
