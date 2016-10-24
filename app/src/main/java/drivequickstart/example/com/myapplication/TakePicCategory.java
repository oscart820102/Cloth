package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        layout = (RelativeLayout) findViewById(R.id.activity_take_pic_category_layout);
        hatImageButton = (ImageButton) findViewById(R.id.take_pic_category_hat_image_button);
        clothImageButton = (ImageButton) findViewById(R.id.take_pic_category_cloth_image_button);
        pantImageButton = (ImageButton) findViewById(R.id.take_pic_category_pant_image_button);
        shoeImageButton = (ImageButton) findViewById(R.id.take_pic_category_shoe_image_button);

        layout.setBackgroundResource(new getPref().getThemeBrowseResID(this));
    }
}
