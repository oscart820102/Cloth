package drivequickstart.example.com.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by X550C on 2016/3/28.
 */
public class Takepic extends Activity {
    //宣告
    private ImageView mImg;
    private DisplayMetrics mPhone;
    private final static int CAMERA = 66;
    private final static int PHOTO = 99;
    private static final int REQUEST_EXTERNAL_STORAGE = 2016;
    private static final int REQUEST_CAMERA_PERMISSION = 2015;
    private Uri photoUri;
    private MyDAOdb daOdb;
    private ImageButton goToMatchImageButton;
    private Button categoryButton;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepic);
        layout = (RelativeLayout) findViewById(R.id.activity_takepic_layout);
        layout.setBackgroundResource(new getPref().getThemeBrowseResID(this));

        //SDK23以上的手機要RunTime Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        }

        //初始DB
        daOdb = new MyDAOdb(this);
        //讀取手機解析度
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        mImg = (ImageView) findViewById(R.id.img);
        ImageButton mCamera = (ImageButton) findViewById(R.id.camera);
        goToMatchImageButton = (ImageButton) findViewById(R.id.takepic_match_imagebutton);
        categoryButton = (Button) findViewById(R.id.takepic_category_button);

        activityTakePhoto();

        mCamera.setOnClickListener(new Button.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                //開啟相機功能，並將拍照後的圖片存入SD卡相片集內，須由startActivityForResult且

                activityTakePhoto();
            }
        });

        goToMatchImageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Takepic.this,Match.class);
                startActivity(intent);
                finish();
            }
        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryDialog();
            }
        });
    }

    //拍照完畢或選取圖片後呼叫此函式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if (requestCode == PHOTO && data != null &&resultCode == RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    Log.e("FileNotFound", e.getMessage());
                }
                Bitmap photoBitmap = BitmapFactory.decodeStream(imageStream);
                CustomImage customImage = new CustomImage();
                customImage.setTitle("");
                customImage.setDescription("");
                customImage.setImageByte(DbBitmapUtility.getBytes(photoBitmap));
                daOdb.addImage(customImage);    //加入DB
                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                if (photoBitmap.getWidth() > photoBitmap.getHeight()) {
                    ScalePic(photoBitmap, mPhone.heightPixels);
                } else {
                    ScalePic(photoBitmap, mPhone.widthPixels);
                }
            } catch (Exception e) {
            }
        } else if (requestCode == CAMERA && resultCode == RESULT_OK) {

            //讀取照片，型態為Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(photoUri.getPath());

            CustomImage customImage = new CustomImage();
            customImage.setTitle("");
            customImage.setDescription("");
            customImage.setImageByte(DbBitmapUtility.getBytes(bitmap));
            daOdb.addImage(customImage);    //加入DB
            ScalePic(bitmap, mPhone.widthPixels);
        }
    }

    private void ScalePic(Bitmap bitmap, int phone) {
        //縮放比例預設為1
        float mScale = 1;
        getOutputMediaFile(bitmap);
        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if (bitmap.getWidth() > phone) {
            //判斷縮放比例
            mScale = (float) phone / (float) bitmap.getWidth();

            Matrix mMat = new Matrix();
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    mMat,
                    false);
            mImg.setImageBitmap(mScaleBitmap);
        } else mImg.setImageBitmap(bitmap);
    }

    private void getOutputMediaFile(Bitmap bitmap) {

        //String extStorage = Environment.getExternalStorageDirectory().toString();
        File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(filepath, "FitFit.jpeg");
        Log.e("路徑", filepath.toString());

        try {
            OutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block

        }
    }


    /**
     * 從圖庫選照片
     */
    private void activityGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO);
    }

    /**
     * 拍攝照片
     */
    private void activityTakePhoto() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File output = new File(dir, "cloth.jpeg");
        photoUri = Uri.fromFile(output);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);   //解決拍照模糊
        startActivityForResult(cameraIntent, CAMERA);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showCategoryDialog(){
        final String[] categorys = {"帽子","衣服","褲子","鞋子"};

        AlertDialog.Builder dialog_list = new AlertDialog.Builder(this);
        dialog_list.setTitle("類別");
        dialog_list.setItems(categorys, new DialogInterface.OnClickListener(){
            @Override
            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
            public void onClick(DialogInterface dialog, int which) {
                // TODO 存照片
                categoryButton.setText(categorys[which]);
            }
        });
        dialog_list.show();
    }
}