package com.codeoptimizer.ebookstore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterToDisplayBook extends RecyclerView.Adapter<AdapterToDisplayBook.MyViewHolder> {

    List<BookData> listData = new ArrayList<>();
    private Context context;

    public AdapterToDisplayBook(List<BookData> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterToDisplayBook.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_book_design,parent,
                false);
        return new AdapterToDisplayBook.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterToDisplayBook.MyViewHolder holder, int position) {
        final int pos=position;
        final BookData dataProvider=listData.get(position);
        holder.bookName.setText(dataProvider.getBookName());
        holder.author.setText(dataProvider.getBookAuthor());
        holder.cat.setText(String.valueOf(dataProvider.getBookCategory()));
        holder.desc.setText(dataProvider.getBookDesc());
        //  Toast.makeText(context, ""+dataProvider.getBookUrl(), Toast.LENGTH_SHORT).show();
        Glide.with(context).load("https://codeoptimizer.000webhostapp.com/"+dataProvider.getBookUrl()+".png").into(holder.bookImg);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookName,author,cat,desc;
        ImageView bookImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName=(TextView)itemView.findViewById(R.id.bookName);
            author=(TextView)itemView.findViewById(R.id.authName);
            cat=(TextView)itemView.findViewById(R.id.catBook);
            desc=(TextView)itemView.findViewById(R.id.descBook);
            bookImg=(ImageView)itemView.findViewById(R.id.bookImg);
        }
    }
}