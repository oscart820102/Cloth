package drivequickstart.example.com.myapplication;

import android.support.v7.widget.RecyclerView;


/**
 * Created by Dajavu on 16/8/18.
 */
public class CenterScrollListener extends RecyclerView.OnScrollListener{
    private boolean mAutoSet = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(!(layoutManager instanceof ScrollZoomLayoutManager)){
            mAutoSet = true;
            return;
        }

        if(!mAutoSet){
            if(newState == RecyclerView.SCROLL_STATE_IDLE){
                int dx = 0;
                if(layoutManager instanceof ScrollZoomLayoutManager){
                    dx = ((ScrollZoomLayoutManager)layoutManager).getOffsetCenterView();
                }
                recyclerView.smoothScrollBy(dx,0);
            }
            mAutoSet = true;
        }
        if(newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING){
            mAutoSet = false;
        }
    }
}
