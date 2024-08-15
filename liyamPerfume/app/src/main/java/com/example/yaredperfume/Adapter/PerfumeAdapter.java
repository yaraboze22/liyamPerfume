package com.example.yaredperfume.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yaredperfume.Modal.Perfume;
import com.example.yaredperfume.R;

import java.util.List;


public class PerfumeAdapter extends RecyclerView.Adapter<PerfumeAdapter.ViewHolder> {
    private List<Perfume> perfumeList;
    private Context context;

    public PerfumeAdapter(List<Perfume> perfumeList, Context context) {
        this.perfumeList = perfumeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.perfume_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Perfume perfume = perfumeList.get(position);
        holder.name.setText(perfume.getName());
        holder.description.setText(perfume.getDescription());
        holder.price.setText(perfume.getPrice());

        // Load image using Glide
        Glide.with(context).load(perfume.getUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showPopup(perfume);
            }
        });

    }

    @Override
    public int getItemCount() {
        return perfumeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, price;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.image);
        }
    }
    private void showPopup(Perfume perfume) {
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
        price.setText("Price: $" + perfume.getPrice());
        Glide.with(context).load(perfume.getUrl()).into(imageView);

        // Add an ÖK"button
        builder.setPositiveButton("OK", null);

        // Show the popup+
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
