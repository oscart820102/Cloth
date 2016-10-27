package drivequickstart.example.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by arthome on 2016/9/29.
 */

/**自訂一個CustomVIew, 可顯示照片&設定選取時顯示刪除符號*/
public class CustomDeleteImageView extends RelativeLayout {
    ImageView imageView;
    ImageView deleteImageView;

    public CustomDeleteImageView(Context context) {
        super(context);
        init(context);
    }

    public CustomDeleteImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomDeleteImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.custom_delete_imageview, this, true);
        imageView = (ImageView) findViewById(R.id.custom_delete_imageview);
        deleteImageView = (ImageView) findViewById(R.id.custom_delete_ic);
    }
    public ImageView getImageView(){
        return imageView;
    }

    public void setSelected(boolean selected){
        if(selected)
            deleteImageView.setSelected(true);
        else
            deleteImageView.setSelected(false);
    }

    public void setChooseSelector(){
        deleteImageView.setImageResource(R.drawable.choose_selector);
    }
    public boolean isSelected(){
        return deleteImageView.isSelected();
    }
}
