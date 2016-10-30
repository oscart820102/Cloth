package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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
    private ImageButton hatAddImageButton, clothAddImageButton, pantAddImageButton, shoeAddImageButton;
    private recycleGalleryShoeAdapter shoeAdapter;
    private recycleGalleryPantAdapter pantAdapter;
    private recycleGalleryClothAdapter clothAdapter;
    private recycleGalleryHatAdapter hatAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.activity_match_layout);
        layout.setBackgroundResource(new getPref().getThemeMatchResID(this));



        hatRecylcerView = (RecyclerView) findViewById(R.id.hat_recycler_view);
        hatRecylcerView.setLayoutManager(new ScrollZoomLayoutManager(this,Dp2px(10)));
        hatAdapter = new recycleGalleryHatAdapter(this, hatImageList);
        hatRecylcerView.setAdapter(hatAdapter);
        hatRecylcerView.addOnScrollListener(new CenterScrollListener());

        clothRecylcerView = (RecyclerView) findViewById(R.id.cloth_recycler_view);
        clothRecylcerView.setLayoutManager(new ScrollZoomLayoutManager(this,Dp2px(10)));
        clothAdapter = new recycleGalleryClothAdapter(this, clothImageList);
        clothRecylcerView.setAdapter(clothAdapter);
        clothRecylcerView.addOnScrollListener(new CenterScrollListener());

        pantRecylcerView = (RecyclerView) findViewById(R.id.pant_recycler_view);
        pantRecylcerView.setLayoutManager(new ScrollZoomLayoutManager(this,Dp2px(10)));
        pantAdapter = new recycleGalleryPantAdapter(this, pantImageList);
        pantRecylcerView.setAdapter(pantAdapter);
        pantRecylcerView.addOnScrollListener(new CenterScrollListener());

        shoeRecylcerView = (RecyclerView) findViewById(R.id.shoe_recycler_view);
        shoeRecylcerView.setLayoutManager(new ScrollZoomLayoutManager(this,Dp2px(10)));
        shoeAdapter = new recycleGalleryShoeAdapter(this, shoeImageList);
        shoeRecylcerView.setAdapter(shoeAdapter);
        shoeRecylcerView.addOnScrollListener(new CenterScrollListener());

        hatAddImageButton = (ImageButton) findViewById(R.id.match_hat_add_image_button);
        clothAddImageButton = (ImageButton) findViewById(R.id.match_cloth_add_image_button);
        pantAddImageButton = (ImageButton) findViewById(R.id.match_pant_add_image_button);
        shoeAddImageButton = (ImageButton) findViewById(R.id.match_shoe_add_image_button);

        hatAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Match.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_HAT);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_MATCH);
                startActivity(intent);
            }
        });

        clothAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Match.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_CLOTH);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_MATCH);
                startActivity(intent);
            }
        });

        pantAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Match.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_PANT);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_MATCH);
                startActivity(intent);
            }
        });

        shoeAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Match.this, AlbumActivity.class);
                intent.putExtra(Constant.INTENT_CATEGORY, Constant.INTENT_SHOE);
                intent.putExtra(Constant.INTENT_OPEN_BY, Constant.INTENT_MATCH);
                startActivity(intent);
            }
        });

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

    @Override
    protected void onResume() {
        super.onResume();
        daOdb = new MyDAOdb(this);
        hatImageList.clear();
        clothImageList.clear();
        pantImageList.clear();
        shoeImageList.clear();

        for (CustomImage mi : daOdb.getImages()) {
            if (mi.getIsMatch() == 1) {
                if (mi.getCategory().name().equals("HAT"))
                    hatImageList.add(mi);
                else if (mi.getCategory().name().equals("CLOTHES"))
                    clothImageList.add(mi);
                else if (mi.getCategory().name().equals("PANTS"))
                    pantImageList.add(mi);
                else if (mi.getCategory().name().equals("SHOES"))
                    shoeImageList.add(mi);
            }
        }

        hatAdapter.notifyDataSetChanged();
        clothAdapter.notifyDataSetChanged();
        pantAdapter.notifyDataSetChanged();
        shoeAdapter.notifyDataSetChanged();

        daOdb.close();
    }
//
//    private RecyclerView.OnScrollListener highlightScrollListener(final recycleGalleryHatAdapter recycleGalleryHatAdapter) {
//        return new RecyclerView.OnScrollListener() {
//            int middleItem;
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == recyclerView.SCROLL_STATE_IDLE) {
////                    recyclerView.smoothScrollToPosition(0);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                middleItem = (linearLayoutManager.findFirstVisibleItemPosition() + linearLayoutManager.findLastVisibleItemPosition()) / 2;
//                System.out.println(">>>first " + linearLayoutManager.findFirstVisibleItemPosition());
//                System.out.println(">>>last " + linearLayoutManager.findLastVisibleItemPosition());
//                recycleGalleryHatAdapter.setItemSelected(middleItem);
//            }
//        };
//    }

    public int Dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
