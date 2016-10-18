package drivequickstart.example.com.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by arthome on 2016/9/25.
 */

public class ImageDialog extends Dialog {


    public ImageDialog(Context context, CustomImage customImage) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image);

        Button okButton = (Button) findViewById(R.id.dialog_ok_button);
        ImageView imageView = (ImageView) findViewById(R.id.dialog_image);

        imageView.setImageBitmap(DbBitmapUtility.getImage(customImage.getImageByte()));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
