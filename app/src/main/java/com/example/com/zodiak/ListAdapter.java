package com.example.com.zodiak;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private static final String TAG = "ListAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private Context mContext;

    public ListAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> date, ArrayList<String> description){
        this.mImageNames = imageNames;
        this.mImages = images;
        this.mDate = date;
        this.mDescription = description;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listzodiak, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(listViewHolder.image);
        listViewHolder.imageName.setText(mImageNames.get(position));
        listViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, GalleryActivity.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name",mImageNames.get(position));
                intent.putExtra("date", mDate.get(position));
                intent.putExtra("description", mDescription.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        TextView date;
        TextView description;
        RelativeLayout parentLayout;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
