package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity_camera1 extends AppCompatActivity {
    static{
        System.out.println("OK0");
    }
    private Bitmap bmp;
    private float sw=1,sh=1;
    private RelativeLayout la;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("OK1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera1);
    }

    public void onGet(View v){
        System.out.println("OK2");
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //建立動作為拍照的Intent
        startActivityForResult(it, 100);  //啟動Intent並要求傳回資料
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100){
            System.out.println("OK");
            super.onActivityResult(requestCode, resultCode, data);
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            double size=3;
            int w=bmp.getWidth();
            int h=bmp.getHeight();
            System.out.println(w+" "+h);
            sw=(float)(1*size);
            sh=(float)(1*size);
            System.out.println(sw + " " + sh);
            Matrix ma=new Matrix();
            ma.postScale(sw, sh);
            Bitmap res= Bitmap.createBitmap(bmp,0,0,w,h,ma,true);
            ImageView imv = (ImageView)findViewById(R.id.imageView1);
            imv.setImageBitmap(res);

        }else {
            Toast.makeText(this, "沒有拍到照片",  Toast.LENGTH_LONG).show();
        }
    }
}
