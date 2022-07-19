package com.cigrastudio.booklibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myviewholder> {
        ArrayList<ModelClass> data=new ArrayList<>();
        Context context;

    public AdapterClass(ArrayList<ModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        myviewholder myviewholder=new myviewholder(view);
        return myviewholder;
        
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
//        holder.name.setText(data.get(position).getName());
//        holder.age.setText(data.get(position).getAge());
//        holder.hobby.setText(data.get(position).getHobby());
        holder.uploadedby.setText(data.get(position).getUploadedby());
        holder.book_name.setText(data.get(position).getBook_name());
        holder.category.setText(data.get(position).getCategory());
        holder.description.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getBookimg()).into( holder.bookimg);




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView uploadedby,book_name,category,description;
        ImageView bookimg;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
//            name=itemView.findViewById(R.id.name_profile_recyclerview);
//            age=itemView.findViewById(R.id.age_profile_recyclerview);
//            hobby=itemView.findViewById(R.id.hobby_profile_recyclerview);
            uploadedby=itemView.findViewById(R.id.upload_by_recyclerview);
            book_name=itemView.findViewById(R.id.book_name_recyclerview);
            category=itemView.findViewById(R.id.category_recyclerview);
            description=itemView.findViewById(R.id.description_recyclerview);
            bookimg=itemView.findViewById(R.id.image_recyclerview);
        }
    }
}
