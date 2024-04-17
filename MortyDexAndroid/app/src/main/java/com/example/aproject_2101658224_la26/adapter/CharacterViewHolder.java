package com.example.aproject_2101658224_la26.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aproject_2101658224_la26.R;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView image;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
        image = itemView.findViewById(R.id.ivImage);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
