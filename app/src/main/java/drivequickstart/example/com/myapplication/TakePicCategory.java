package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class TakePicCategory extends AppCompatActivity {

    RelativeLayout layout;
    ImageButton hatImageButton;
    ImageButton clothImageButton;
    ImageButton pantImageButton;
    ImageButton shoeImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic_category);
        getSupportActionBar().hide();

        findUIView();
        layout.setBackgroundResource(new getPref().getThemeBrowseResID(this));
        hatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePicCategory.this, Takepic.class);
                intent.putExtra(Constant.INTENT_CATEGORY,Constant.INTENT_HAT);
                startActivity(intent);
                finish();
            }
        });
        clothImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePicCategory.this, Takepic.class);
                intent.putExtra(Constant.INTENT_CATEGORY,Constant.INTENT_CLOTH);
                startActivity(intent);
                finish();
            }
        });
        pantImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePicCategory.this, Takepic.class);
                intent.putExtra(Constant.INTENT_CATEGORY,Constant.INTENT_PANT);
                startActivity(intent);
                finish();
            }
        });
        shoeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePicCategory.this, Takepic.class);
                intent.putExtra(Constant.INTENT_CATEGORY,Constant.INTENT_SHOE);
                startActivity(intent);
                finish();
            }
        });
    }

    private void findUIView() {
        layout = (RelativeLayout) findViewById(R.id.activity_take_pic_category_layout);
        hatImageButton = (ImageButton) findViewById(R.id.take_pic_category_hat_image_button);
        clothImageButton = (ImageButton) findViewById(R.id.take_pic_category_cloth_image_button);
        pantImageButton = (ImageButton) findViewById(R.id.take_pic_category_pant_image_button);
        shoeImageButton = (ImageButton) findViewById(R.id.take_pic_category_shoe_image_button);
    }
}
