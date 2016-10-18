package drivequickstart.example.com.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by arthome on 2016/9/25.
 */

/**跳出兩個按鈕選項的Dialog*/
public class TwoSelectionDialog extends Dialog {

    private OnDialogButtonListener listener;

    public TwoSelectionDialog(Context context, String firstButtonTitle, String SecondButtonTitle) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_two_selection);

        Button firstOptionButton = (Button) findViewById(R.id.dialog_first_selection_button);
        Button secondOptionButton = (Button) findViewById(R.id.dialog_second_selection_button);
        Button cancelButton = (Button) findViewById(R.id.dialog_cancel_button);

        firstOptionButton.setText(firstButtonTitle);
        secondOptionButton.setText(SecondButtonTitle);

        firstOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFirstOptionClicked();
                dismiss();
            }
        });
        secondOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSecondOptionClicked();
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public void setOnDialogButtonListener(OnDialogButtonListener listener) {
        this.listener = listener;
    }

    /**設定按鈕的onClickListener*/
    public interface OnDialogButtonListener {
        void onFirstOptionClicked();

        void onSecondOptionClicked();
    }
}
