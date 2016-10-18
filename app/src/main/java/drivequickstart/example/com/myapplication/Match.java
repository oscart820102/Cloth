package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Match extends Activity{
    /** Called when the activity is first created. */
    private galleryAdapter adapter;
    private galleryAdapter2 adapter2;
    private galleryAdapter3 adapter3;
    private galleryAdapter4 adapter4;
    private LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.activity_match_layout);
        layout.setBackgroundResource(new getPref().getThemeMatchResID(this));

        Gallery gallery=(Gallery)findViewById(R.id.gallery);
        adapter=new galleryAdapter(this);
        gallery.setAdapter(adapter);
        gallery.setSpacing(50);


        Gallery gallery2=(Gallery)findViewById(R.id.gallery2);
        adapter2=new galleryAdapter2(this);
        gallery2.setAdapter(adapter2);
        gallery2.setSpacing(50);

        Gallery gallery3=(Gallery)findViewById(R.id.gallery3);
        adapter3=new galleryAdapter3(this);
        gallery3.setAdapter(adapter3);
        gallery3.setSpacing(50);

        Gallery gallery4=(Gallery)findViewById(R.id.gallery4);
        adapter4=new galleryAdapter4(this);
        gallery4.setAdapter(adapter4);
        gallery4.setSpacing(50);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //將對應選到的縮圖的大圖放置於ImageSwitcher中
                adapter.setSelectItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //什麼也不必做
            }
        });
        gallery2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //將對應選到的縮圖的大圖放置於ImageSwitcher中
                adapter2.setSelectItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //什麼也不必做
            }
        });
        gallery3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //將對應選到的縮圖的大圖放置於ImageSwitcher中
                adapter3.setSelectItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //什麼也不必做
            }
        });
        gallery4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //將對應選到的縮圖的大圖放置於ImageSwitcher中
                adapter4.setSelectItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //什麼也不必做
            }
        });
    }


}
