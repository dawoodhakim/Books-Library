package com.cigrastudio.booklibrary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cigrastudio.booklibrary.fragment.favouriteFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myviewholder> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth firebaseAuth;
    String uid = user.getUid();



    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("fav");

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
        final  String key=data.get(position).getKey();
        holder.uploadedby.setText(data.get(position).getUploadedby());
        holder.book_name.setText(data.get(position).getBook_name());
        holder.category.setText(data.get(position).getCategory());
        holder.description.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getBookimg()).into( holder.bookimg);

        if (data.get(position).getKey().equals(uid)){
            holder.itemView.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked on position " + position, Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "Position: " + data.get(position).getKey());
//                Intent intent = new Intent(view.getContext(), MainActivity.class);
//                intent.putExtra("name", data.get(position).getName());
            }
        });

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Method 1
            moveFirebaseRecord(holder.databaseReference.child(key), holder.databaseReference2.child(key));


            // Method 2
//                String uploadedBy = data.get(position).getUploadedby();
//                String bookNmae = data.get(position).getBook_name();
//                String category = data.get(position).getCategory();
//                String description = data.get(position).getDescription();
//                String bookImage = data.get(position).getBookimg();

                //databaseReference.child(bookNmae).child("uploadedBy").setValue(uploadedBy);
//                databaseReference.child(bookNmae).child("bookName").setValue(bookNmae);
//                databaseReference.child(bookNmae).child("category").setValue(category);
//                databaseReference.child(bookNmae).child("description").setValue(description);
//                databaseReference.child(bookNmae).child("bookImage").setValue(bookImage);


                    // Method 3
////                HashMap<String, Object> data = new HashMap<>();
////                data.put(uploadedBy, "uploadedby");
////                data.put(bookImage, "bookImg");
////                data.put(bookNmae, "book_name");
////                data.put(description, "description");
////                data.put(category, "category");
//                databaseReference.updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        Toast.makeText(v.getContext(), "Book Added To faviourite", Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView uploadedby,book_name,category,description;
        ImageButton favourite;
        ImageView bookimg;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        DatabaseReference databaseReference2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            user= FirebaseAuth.getInstance().getCurrentUser();
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference().child("books");
            databaseReference2=firebaseDatabase.getReference().child("users").child(uid).child("Favourite");
            favourite=itemView.findViewById(R.id.btn_favorite);
            uploadedby=itemView.findViewById(R.id.upload_by_recyclerview);
            book_name=itemView.findViewById(R.id.book_name_recyclerview);
            category=itemView.findViewById(R.id.category_recyclerview);
            description=itemView.findViewById(R.id.description_recyclerview);
            bookimg=itemView.findViewById(R.id.image_recyclerview);

//            favourite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position=getAdapterPosition();
//
//                }
//            })
        }
    }
    public void moveFirebaseRecord(DatabaseReference fromPath,final DatabaseReference toPath){
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toPath.setValue(snapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
