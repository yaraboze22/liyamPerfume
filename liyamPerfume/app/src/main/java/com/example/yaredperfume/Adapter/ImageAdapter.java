package com.example.yaredperfume.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yaredperfume.Modal.ImageData;
import com.example.yaredperfume.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<ImageData> imageDataList;
    private DatabaseReference db;

    public ImageAdapter(Context context, List<ImageData> imageDataList) {
        this.context = context;
        this.imageDataList = imageDataList;
        this.db= FirebaseDatabase.getInstance().getReference("Liked");
    }

    @Override
    public int getCount() {
        return imageDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);
        TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);
        ImageView heartIcon = convertView.findViewById(R.id.heartIcon);

        ImageData imageData = imageDataList.get(position);


        Glide.with(context).load(imageData.getUrl()).into(imageView);
        textViewName.setText(imageData.getName());
        textViewDescription.setText(imageData.getDescription());
        textViewPrice.setText(imageData.getPrice());

        if (imageData.isLiked()){
            heartIcon.setImageResource(R.drawable.baseline_favorite_24);
        }else {
            heartIcon.setImageResource(R.drawable.baseline_favorite_border_24);
        }

        convertView.setOnClickListener(v -> sPopup(imageData));
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLiked = !imageData.isLiked();
                imageData.setLiked(isLiked);

                if (isLiked){
                    heartIcon.setImageResource(R.drawable.baseline_favorite_24);
                }else {
                    heartIcon.setImageResource(R.drawable.baseline_favorite_border_24);
                }

                Map<String,Object> image = new HashMap<>();
                image.put("liked",isLiked);
                image.put("url", imageData.getUrl());
                image.put("name", imageData.getName());
                image.put("description", imageData.getDescription());
                image.put("price", imageData.getPrice());
                db.child(imageData.getId()).updateChildren(image);

            }
        });

        return convertView;
    }
private void sPopup(ImageData perfume) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(perfume.getName());

                    // Create a custom layout for the popup
        View customLayout = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);
            builder.setView(customLayout);

                // Set the content of the popup
        TextView description = customLayout.findViewById(R.id.popup_description);
            TextView price = customLayout.findViewById(R.id.popup_price);
                ImageView imageView = customLayout.findViewById(R.id.popup_image);

                    description.setText(perfume.getDescription());
                        price.setText("Price: $"+ perfume.getPrice());
                            Glide.with(context).load(perfume.getUrl()).into(imageView);

                                // Add an ÖK"button
        builder.setPositiveButton("OK", null);

                    // Show the popup" +
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }


}

