package drivequickstart.example.com.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {
    private static final int RESULT_SELECT_PHOTO = 1888;
    private static final int REQUEST_CAMERA = 1889;
    private static final int REQUEST_EXTERNAL_STORAGE = 2016;
    private static final int REQUEST_CAMERA_PERMISSION = 2015;

    private ImageButton addImageButton;
    private ImageButton deleteImageButton;
    private Button addToMatchButton;
    private Button cancelDeleteButton;
    private Button deleteButton;
    private LinearLayout deleteLayout;
    private ArrayList<CustomImage> customImageArrayList;
    private RecyclerView recyclerView;
    private ImageRecyclerViewAdapter adapter;
    private MyDAOdb daOdb;
    private Uri photoUri;
    private RelativeLayout layout;
    private String categoryString;
    private CustomImage.Category category;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getSupportActionBar().hide();

        layout = (RelativeLayout) findViewById(R.id.activity_album_layout);
        layout.setBackgroundResource(new getPref().getThemeBrowseResID(this));

        categoryString = getIntent().getStringExtra(Constant.INTENT_CATEGORY);


        switch (categoryString){
            case Constant.INTENT_HAT :
                category = CustomImage.Category.HAT;
                break;
            case Constant.INTENT_CLOTH :
                category = CustomImage.Category.CLOTHES;
                break;
            case Constant.INTENT_PANT :
                category = CustomImage.Category.PANTS;
                break;
            case Constant.INTENT_SHOE :
                category = CustomImage.Category.SHOES;
                break;
        }

        //SDK23以上的手機要RunTime Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        }

        findUIViews();



        // Construct the data source
        customImageArrayList = new ArrayList();
        // Create the adapter to convert the array to views
        adapter = new ImageRecyclerViewAdapter(this, customImageArrayList);
        // Attach the adapter to a ListView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        if(getIntent().getStringExtra(Constant.INTENT_OPEN_BY).equals(Constant.INTENT_ALBUM)){
            isAlbumMode(true);
        }else if(getIntent().getStringExtra(Constant.INTENT_OPEN_BY).equals(Constant.INTENT_MATCH)){
            isAlbumMode(false);
            adapter.setMatchMode(true);
            adapter.notifyDataSetChanged();
        }

        setListeners();

        initDB();
    }

    private void findUIViews() {
        deleteButton = (Button) findViewById(R.id.album_delete_button);
        cancelDeleteButton = (Button) findViewById(R.id.album_cancel_button);
        deleteLayout = (LinearLayout) findViewById(R.id.album_delete_layout);
        addImageButton = (ImageButton) findViewById(R.id.album_add_imagebutton);
        deleteImageButton = (ImageButton) findViewById(R.id.album_delete_imagebutton);
        recyclerView = (RecyclerView) findViewById(R.id.album_recycler_view);
        layout = (RelativeLayout) findViewById(R.id.activity_album_layout);
        addToMatchButton = (Button) findViewById(R.id.album_add_to_match_button);
    }

    private void setListeners() {
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setDeleteMode(true);
                deleteLayout.setVisibility(View.VISIBLE);
                addImageButton.setVisibility(View.INVISIBLE);
                deleteImageButton.setVisibility(View.INVISIBLE);
            }
        });

        adapter.setListener(new ImageRecyclerViewAdapter.AdapterListener() {
            @Override
            public void deleteImage(CustomImage customImage) {
                daOdb.deleteImage(customImage);
            }

            @Override
            public void imageSelected(int selectedCount) {
                if (selectedCount == 0)
                    deleteButton.setText("刪除");
                else
                    deleteButton.setText("刪除 (" + selectedCount + ")");
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getDeleteCount() == 0) {    //沒選刪除圖片時 忽略
                    return;
                }
                adapter.deleteImages();
                customImageArrayList.clear();
                for (CustomImage mi : daOdb.getCategoryImages(categoryString)) {
                    customImageArrayList.add(mi);
                }
                adapter.notifyDataSetChanged();

                normalMode();
            }
        });

        cancelDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normalMode();
                adapter.cancelDelete();
                adapter.notifyDataSetChanged();
            }
        });

        addToMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.saveToDB();
                finish();
            }
        });
    }

    /**一般瀏覽模式(非刪除模式)*/
    private void normalMode() {
        adapter.setDeleteMode(false);
        deleteButton.setText("刪除");
        deleteLayout.setVisibility(View.INVISIBLE);
        addImageButton.setVisibility(View.VISIBLE);
        deleteImageButton.setVisibility(View.VISIBLE);
    }

    private void initDB() {
        daOdb = new MyDAOdb(this);
        //add images from database to images ArrayList
        for (CustomImage mi : daOdb.getCategoryImages(categoryString)) {
            customImageArrayList.add(mi);
        }
        //從DB撈完資料,通知adapter刷新
        adapter.notifyDataSetChanged();
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
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) { //requestCode是相機
            Bitmap bmp = BitmapFactory.decodeFile(photoUri.getPath());

            CustomImage customImage = new CustomImage();
            customImage.setTitle("");
            customImage.setDescription("");
            customImage.setImageByte(DbBitmapUtility.getBytes(bmp));
            customImage.setCategory(category);
            daOdb.addImage(customImage);    //加入DB
            customImageArrayList.add(0, customImage);   //加入要顯示的List
            adapter.notifyDataSetChanged();     //刷新adapter
        } else if (requestCode == RESULT_SELECT_PHOTO && resultCode == RESULT_OK) { //requestCode是相簿
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
            customImage.setCategory(category);
            daOdb.addImage(customImage);    //加入DB
            customImageArrayList.add(0, customImage);   //加入要顯示的List
            adapter.notifyDataSetChanged();     //刷新adapter
        }
    }

    private void showDialog() {
        TwoSelectionDialog dialog = new TwoSelectionDialog(this, "相機", "相簿");
        dialog.setOnDialogButtonListener(new TwoSelectionDialog.OnDialogButtonListener() {
            @Override
            public void onFirstOptionClicked() {
                activityTakePhoto();
            }

            @Override
            public void onSecondOptionClicked() {
                activeGallery();
            }
        });

        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }

    private void isAlbumMode(boolean isAlbumMode){     //判斷是album瀏覽 或是 match要增加,顯示對應的View
        if(isAlbumMode){
            addImageButton.setVisibility(View.VISIBLE);
            deleteImageButton.setVisibility(View.VISIBLE);
            addToMatchButton.setVisibility(View.INVISIBLE);
        }else {
            addImageButton.setVisibility(View.INVISIBLE);
            deleteImageButton.setVisibility(View.INVISIBLE);
            addToMatchButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 從圖庫選照片
     */
    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_SELECT_PHOTO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        daOdb.close();
    }
}
