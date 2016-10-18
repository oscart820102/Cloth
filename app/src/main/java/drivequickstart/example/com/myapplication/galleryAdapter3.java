package drivequickstart.example.com.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

class galleryAdapter3 extends BaseAdapter {
    Context mContext;
    private int selectItem;
    private int drawable1[]=new int[] {R.mipmap.p1,R.mipmap.p2,R.mipmap.p3};
    public galleryAdapter3(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub  
        return  drawable1.length;          //这里的目的是可以让图片循环浏览
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub  
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub  
        return position;
    }
    public void setSelectItem(int selectItem) {

        if (this.selectItem != selectItem) {
            this.selectItem = selectItem;
            notifyDataSetChanged();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub  
        ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(drawable1[position]);
//取余，让图片循环浏览  

        if(selectItem==position){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_scale_action);    //实现动画效果
            imageView.setLayoutParams(new Gallery.LayoutParams(250,250));
            imageView.startAnimation(animation);  //选中时，这是设置的比较大
        }
        else{
            imageView.setLayoutParams(new Gallery.LayoutParams(75,90));
//未选中  
        }
        return imageView;
    }

}  