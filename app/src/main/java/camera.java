import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.Button;

import android.widget.ImageView;

import drivequickstart.example.com.myapplication.R;

/**
 * Created by LAB403 on 2016/5/18.
 */
public class camera extends Activity {
    private static final int IMAGE_CAPTURE = 0;
    private Button startBtn;
    private Uri imageUri;
    private ImageView imageView;


    /**
     * Called when the activity is first created.
     * sets the content and gets the references to
     * the basic widgets on the screen like
     * {@code Button} or {@link ImageView}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        imageView = (ImageView) findViewById(R.id.img);
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startCamera();
            }
        });

    }

    public void startCamera()  {
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
        startActivityForResult(intent, IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Log.d("ANDRO_CAMERA", "Picture taken!!!");
                imageView.setImageURI(imageUri);
            }
        }
    }
}