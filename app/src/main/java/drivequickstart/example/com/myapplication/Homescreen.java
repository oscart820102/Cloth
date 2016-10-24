package drivequickstart.example.com.myapplication;

/**
 * Created by student on 2016/4/19.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Homescreen extends Activity {

    /**
     * Called when the activity is first created.
     */
    private static final int IMAGE_CAPTURE = 0;
    private ImageButton imgbtn;
    private ImageButton albumImageButton, themeImageButton;
    private LinearLayout layout;
    private Uri imageUri;
    private Bitmap bmp;
    private float sw = 1, sh = 1;
    private RelativeLayout la;
    public static int num = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton2);
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);
        albumImageButton = (ImageButton) findViewById(R.id.home_album_image_button);
        themeImageButton = (ImageButton) findViewById(R.id.theme_image_button);
        layout = (LinearLayout) findViewById(R.id.homesreen_layout);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {/*
                    Log.d("ANDRO_CAMERA", "Starting camera on the phone...");
                    String fileName = "testphoto.jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION,
                            "Image capture by camera");
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, IMAGE_CAPTURE);*/
                Intent intent = new Intent();
//                intent.setClass(Homescreen.this, Takepic.class);
                intent.setClass(Homescreen.this, TakePicCategory.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent();
                i.setClass(Homescreen.this, Match.class);
                startActivity(i);
            }
        });

        albumImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homescreen.this, AlbumActivity.class);
                startActivity(intent);
            }
        });

        themeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homescreen.this, ThemePreferenceActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onGet(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //建立動作為拍照的Intent
        it.setClass(Homescreen.this, MainActivity_camera1.class);
        startActivityForResult(it, 100);  //啟動Intent並要求傳回資料

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            System.out.println("OK");
            super.onActivityResult(requestCode, resultCode, data);
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            double size = 3;
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            System.out.println(w + " " + h);
            sw = (float) (1 * size);
            sh = (float) (1 * size);
            System.out.println(sw + " " + sh);
            Matrix ma = new Matrix();
            ma.postScale(sw, sh);
            Bitmap res = Bitmap.createBitmap(bmp, 0, 0, w, h, ma, true);
            ImageView imv = (ImageView) findViewById(R.id.imageView1);
            imv.setImageBitmap(res);

        } else {
            Toast.makeText(this, "沒有拍到照片", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent();
        i.setClass(Homescreen.this, Match.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu;
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.setBackgroundResource(new getPref().getThemeMainResID(this));
    }
}




