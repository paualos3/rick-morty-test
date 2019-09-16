package com.example.paualos3.rick_morty_test.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.paualos3.rick_morty_test.Models.CharacterModel;
import com.example.paualos3.rick_morty_test.R;

import java.util.ArrayList;

public class MyListAdapter  extends RecyclerView.Adapter<MyListAdapter.ViewHolder>  {

    private ArrayList<CharacterModel> dataset;
    private Context context;

    public MyListAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CharacterModel p = dataset.get(position);
        holder.nameText.setText(p.getName());

        Glide.with(context)
                .load(p.getImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addCharactersList(ArrayList<CharacterModel> charactersList) {
        dataset.addAll(charactersList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImageView;
        private TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);

            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            nameText = (TextView) itemView.findViewById(R.id.nameText);
        }
    }
}
