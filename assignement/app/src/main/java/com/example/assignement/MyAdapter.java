package com.example.assignement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    List<Model> datalist;
    Context context;
    btn_more more_btn;

    public MyAdapter(List<Model> datalist, Context context, btn_more more_btn) {
        this.datalist = datalist;
        this.context = context;
        this.more_btn = more_btn;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdesign, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.desc.setText(datalist.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView desc;
        private ImageButton more;

        public Holder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.tv_desc);
            more = itemView.findViewById(R.id.btn_more);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position=getAdapterPosition();
                more_btn.more(position);
                }
            });
        }
    }
    public interface btn_more
    {
        void more(int positon);
    }
}
