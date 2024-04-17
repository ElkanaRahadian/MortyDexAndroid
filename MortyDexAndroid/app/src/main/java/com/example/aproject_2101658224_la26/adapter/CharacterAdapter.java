package com.example.aproject_2101658224_la26.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aproject_2101658224_la26.R;
import com.example.aproject_2101658224_la26.model.CharacterModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private ArrayList<CharacterModel> characterModelArrayList = new ArrayList<>();
    private Context context;

    public CharacterAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<CharacterModel> data) {
        characterModelArrayList = data;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_card, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        String name = characterModelArrayList.get(position).getName();
        String image = characterModelArrayList.get(position).getImage();
        holder.getName().setText(name);
        Picasso.get().load(image).into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return characterModelArrayList.size();
    }
}
