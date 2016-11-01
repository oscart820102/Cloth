package drivequickstart.example.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AlbumCategoryActivity extends AppCompatActivity {
    LinearLayout hatLayout;
    LinearLayout clothLayout;
    LinearLayout pantLayout;
    LinearLayout shoeLayout;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_category);

        getSupportActionBar().hide();
        layout = (LinearLayout) findViewById(R.id.activity_album_category_layout);
        hatLayout = (LinearLayout) findViewById(R.id.album_category_hat_layout);
        clothLayout = (LinearLayout) findViewById(R.id.album_category_cloth_layout);
        pantLayout = (LinearLayout) findViewById(R.id.album_category_pant_layout);
        shoeLayout = (LinearLayout) findViewById(R.id.album_category_shoe_layout);

        layout.setBackgroundResource(new getPref().getThemeAlbumResID(this));

        hatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumCategoryActivity.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_HAT);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_ALBUM);
                startActivity(intent);
                finish();
            }
        });

        clothLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumCategoryActivity.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_CLOTH);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_ALBUM);
                startActivity(intent);
                finish();
            }
        });
        pantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumCategoryActivity.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_PANT);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_ALBUM);
                startActivity(intent);
                finish();
            }
        });
        shoeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumCategoryActivity.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_SHOE);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_ALBUM);
                startActivity(intent);
                finish();
            }
        });
    }
}
