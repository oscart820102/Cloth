package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Match extends Activity {
    /**
     * Called when the activity is first created.
     */
    private galleryAdapter adapter;
    private galleryAdapter adapter2;
    private galleryAdapter adapter3;
    private galleryAdapter adapter4;
    private LinearLayout layout;
    private MyDAOdb daOdb;
    private ArrayList<CustomImage> hatImageList = new ArrayList<>();
    private ArrayList<CustomImage> clothImageList = new ArrayList<>();
    private ArrayList<CustomImage> pantImageList = new ArrayList<>();
    private ArrayList<CustomImage> shoeImageList = new ArrayList<>();
    private List<Bitmap> hatBitmapList = new ArrayList<>();
    private List<Bitmap> clothBitmapList = new ArrayList<>();
    private List<Bitmap> pantBitmapList = new ArrayList<>();
    private List<Bitmap> shoeBitmapList = new ArrayList<>();
    private RecyclerView hatRecylcerView;
    private RecyclerView clothRecylcerView;
    private RecyclerView pantRecylcerView;
    private RecyclerView shoeRecylcerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.activity_match_layout);
        layout.setBackgroundResource(new getPref().getThemeMatchResID(this));

        daOdb = new MyDAOdb(this);

        for (CustomImage mi : daOdb.getImages()) {
            if (mi.getCategory().name().equals("HAT"))
                hatImageList.add(mi);
            else if(mi.getCategory().name().equals("CLOTHES"))
                clothImageList.add(mi);
            else if(mi.getCategory().name().equals("PANTS"))
                pantImageList.add(mi);
            else if(mi.getCategory().name().equals("SHOES"))
                shoeImageList.add(mi);
        }

        daOdb.close();
        System.out.println(">>>>>CLOSED size is " + hatImageList.size());

        hatRecylcerView = (RecyclerView) findViewById(R.id.hat_recycler_view);
        hatRecylcerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hatRecylcerView.setAdapter(new recycleGalleryAdapter(this, hatImageList));

        clothRecylcerView = (RecyclerView) findViewById(R.id.cloth_recycler_view);
        clothRecylcerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        clothRecylcerView.setAdapter(new recycleGalleryAdapter(this, clothImageList));

        pantRecylcerView = (RecyclerView) findViewById(R.id.pant_recycler_view);
        pantRecylcerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        pantRecylcerView.setAdapter(new recycleGalleryAdapter(this, pantImageList));

        shoeRecylcerView = (RecyclerView) findViewById(R.id.shoe_recycler_view);
        shoeRecylcerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        shoeRecylcerView.setAdapter(new recycleGalleryAdapter(this, shoeImageList));

//
//        Gallery gallery2=(Gallery)findViewById(R.id.gallery2);
//        adapter2= new galleryAdapter(this, clothImageList);
//        gallery2.setAdapter(adapter2);
//        gallery2.setSpacing(50);
//
//        Gallery gallery3=(Gallery)findViewById(R.id.gallery3);
//        adapter3=new galleryAdapter(this, shoeImageList);
//        gallery3.setAdapter(adapter3);
//        gallery3.setSpacing(50);
//
//        Gallery gallery4=(Gallery)findViewById(R.id.gallery4);
//        adapter4=new galleryAdapter(this , pantImageList);
//        gallery4.setAdapter(adapter4);
//        gallery4.setSpacing(50);

//        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //將對應選到的縮圖的大圖放置於ImageSwitcher中
//                adapter.setSelectItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //什麼也不必做
//            }
////        });
//        gallery2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //將對應選到的縮圖的大圖放置於ImageSwitcher中
//                adapter2.setSelectItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //什麼也不必做
//            }
//        });
//        gallery3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //將對應選到的縮圖的大圖放置於ImageSwitcher中
//                adapter3.setSelectItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //什麼也不必做
//            }
//        });
//        gallery4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //將對應選到的縮圖的大圖放置於ImageSwitcher中
//                adapter4.setSelectItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //什麼也不必做
//            }
//        });
    }


}
