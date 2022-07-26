package com.cigrastudio.booklibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClass_favourite_fragment extends RecyclerView.Adapter<AdapterClass_favourite_fragment.myviewholder1> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth firebaseAuth;
    String uid = user.getUid();
    ArrayList<ModelClass> data=new ArrayList<>();
    Context context;

    public AdapterClass_favourite_fragment(ArrayList<ModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        AdapterClass_favourite_fragment.myviewholder1 myviewholder1=new AdapterClass_favourite_fragment.myviewholder1(view);
        return myviewholder1;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder1 holder, int position) {
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

    public  class myviewholder1 extends RecyclerView.ViewHolder {
        TextView uploadedby,book_name,category,description;
        ImageButton favourite;
        ImageView bookimg;

        public myviewholder1(@NonNull View itemView) {
            super(itemView);

            favourite=itemView.findViewById(R.id.btn_favorite);
            uploadedby=itemView.findViewById(R.id.upload_by_recyclerview);
            book_name=itemView.findViewById(R.id.book_name_recyclerview);
            category=itemView.findViewById(R.id.category_recyclerview);
            description=itemView.findViewById(R.id.description_recyclerview);
            bookimg=itemView.findViewById(R.id.image_recyclerview);
        }
    }

}
