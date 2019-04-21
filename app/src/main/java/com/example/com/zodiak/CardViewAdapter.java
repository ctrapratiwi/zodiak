package com.example.com.zodiak;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {
    private static final String TAG = "CardViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private Context mContext;

    public CardViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> date, ArrayList<String> description){
        this.mImageNames = imageNames;
        this.mImages = images;
        this.mDate = date;
        this.mDescription = description;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cardview, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(cardViewHolder.image);
        cardViewHolder.imageName.setText(mImageNames.get(position));
        cardViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, GalleryActivity.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name", mImageNames.get(position));
                intent.putExtra("date", mDate.get(position));
                intent.putExtra("description", mDescription.get(position));
                mContext.startActivity(intent);
            }
        });
        cardViewHolder.btnFav.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(mContext, "Favorite"+mImageNames.get(position) ,Toast.LENGTH_SHORT).show();
            }
        }));
        cardViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(mContext, "Share"+ mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        }));

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView imageName;
        TextView date;
        TextView description;
        Button btnFav;
        Button btnShare;
        RelativeLayout parentLayout;
        public CardViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.image);
            imageName = view.findViewById(R.id.image_name);
            date = view.findViewById(R.id.date);
            description = view.findViewById(R.id.description);
            btnFav = view.findViewById(R.id.btn_set_favorite);
            btnShare = view.findViewById(R.id.btn_set_share);

        }
    }
}
