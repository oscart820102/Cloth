package drivequickstart.example.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by arthome on 2016/9/24.
 */

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CustomImage> imageList;
    private ArrayList<Integer> deletePosition = new ArrayList<>();  //記住刪除模式選取的位置
    private ArrayList<Integer> addMatchPosition = new ArrayList<>();  //記住match模式選取的位置
    private boolean deleteMode = false; //是否為刪除模式
    private boolean matchMode = false;
    private Context context;
    private AdapterListener listener;

    public int getDeleteCount() {
        return deleteCount;
    }

    private int deleteCount = 0;

    public ImageRecyclerViewAdapter(Context context, ArrayList<CustomImage> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deleteMode) {
                    if (myViewHolder.deleteImageView.isSelected()) {
                        myViewHolder.deleteImageView.setSelected(false);
                        deletePosition.remove(Integer.valueOf(myViewHolder.getAdapterPosition()));
                        deleteCount--;
                        listener.imageSelected(deletePosition.size());
                    } else {
                        myViewHolder.deleteImageView.setSelected(true);
                        deletePosition.add(myViewHolder.getAdapterPosition());
                        deleteCount++;
                        listener.imageSelected(deletePosition.size());
                    }
                } else if (matchMode) {
                    if (myViewHolder.deleteImageView.isSelected()) {
                        myViewHolder.deleteImageView.setSelected(false);
                        addMatchPosition.remove(Integer.valueOf(myViewHolder.getAdapterPosition()));
                    } else {
                        myViewHolder.deleteImageView.setSelected(true);
                        addMatchPosition.add(myViewHolder.getAdapterPosition());
                    }
                } else {
                    ImageDialog imageDialog = new ImageDialog(context, imageList.get(myViewHolder.getAdapterPosition()));
                    imageDialog.show();
                }
//                Toast.makeText(context,Integer.toString(myViewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(matchMode){      //match Mode改成+號
            holder.deleteImageView.setChooseSelector();
        }
        holder.deleteImageView.setSelected(false);  //防bug, 按完刪除後 沒刪除的照片會出現X
        Glide.with(context).load(imageList.get(position).getImageByte())
                .asBitmap()
                .placeholder(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.deleteImageView.getImageView());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CustomDeleteImageView deleteImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            deleteImageView = (CustomDeleteImageView) itemView.findViewById(R.id.item_recycler_custom_delete_image);
//            deleteImageView = (ImageView) itemView.findViewById(R.id.item_recycler_delete_image);
        }
    }


    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public boolean isMatchMode() {
        return matchMode;
    }

    public void setMatchMode(boolean matchMode) {
        this.matchMode = matchMode;
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public boolean deleteImages() {
        for (int i = 0; i < deletePosition.size(); i++) {
            listener.deleteImage(imageList.get(deletePosition.get(i)));
        }
        deletePosition.clear();
        deleteCount = 0;
        return true;
    }

    public void cancelDelete() {
        deleteCount = 0;
        deletePosition.clear();
        deleteMode = false;
    }

    public void saveToDB(){
        MyDAOdb  daOdb = new MyDAOdb(context);
        for (int i = 0; i< addMatchPosition.size() ; i++){
            daOdb.updateImage(imageList.get(addMatchPosition.get(i)));
        }
        daOdb.close();
    }

    interface AdapterListener {
        public void deleteImage(CustomImage customImage);

        public void imageSelected(int selectedCount);
    }
}
