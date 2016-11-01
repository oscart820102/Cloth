package drivequickstart.example.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by arthome on 2016/10/26.
 */

public class recycleGalleryPantAdapter extends RecyclerView.Adapter<recycleGalleryPantAdapter.galleryViewHolder> {
    private ArrayList<CustomImage> customImages = new ArrayList<>();
    private Context context;

    public recycleGalleryPantAdapter(Context context, ArrayList<CustomImage> customImages ){
        this.customImages = customImages;
        this.context = context;
    }

    @Override
    public galleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_image, parent, false);
        return new galleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(galleryViewHolder holder, int position) {

        if(position==0){
            Glide.with(context).load(R.drawable.p11)
                    .asBitmap()
                    .placeholder(R.drawable.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imageView);
        }else if(position==1){
            Glide.with(context).load(R.drawable.p22)
                    .asBitmap()
                    .placeholder(R.drawable.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imageView);
        }else if(position==2){
            Glide.with(context).load(R.drawable.p33)
                    .asBitmap()
                    .placeholder(R.drawable.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imageView);
        }
        else{
            Glide.with(context).load(customImages.get(position-3).getImageByte())
                    .asBitmap()
                    .placeholder(R.drawable.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return customImages.size()+3;
    }

    class galleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        galleryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_match_image_view);
        }
    }



}
