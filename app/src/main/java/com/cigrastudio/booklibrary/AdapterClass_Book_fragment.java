package com.cigrastudio.booklibrary;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClass_Book_fragment extends RecyclerView.Adapter<AdapterClass_Book_fragment.myviewholder2> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth firebaseAuth;
    String uid = user.getUid();
    ArrayList<ModelClass> data=new ArrayList<>();
    Context context;

    public AdapterClass_Book_fragment(ArrayList<ModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        AdapterClass_Book_fragment.myviewholder2 myviewholder2=new AdapterClass_Book_fragment.myviewholder2(view);
        return myviewholder2;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder2 holder, int position) {
        final  String key=data.get(position).getKey();
        holder.uploadedby.setText(data.get(position).getUploadedby());
        holder.book_name.setText(data.get(position).getBook_name());
        holder.category.setText(data.get(position).getCategory());
        holder.description.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getBookimg()).into( holder.bookimg);

        if (data.get(position).getKey().equals(uid)){
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked on position " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), Itemview_Detail_Activity.class);
                intent.putExtra("book_name",String.valueOf(data.get(position).getBook_name()) );
                intent.putExtra("category",String.valueOf(data.get(position).getCategory()));
                intent.putExtra("description",String.valueOf(data.get(position).getDescription()));
                intent.putExtra("uploadedby",String.valueOf(data.get(position).getUploadedby()));
                intent.putExtra("bookimg",(data.get(position).getBookimg()));
//                Log.d("TAG", "Book Name: " + data.get(position).getBook_name());
//                Log.d("TAG", "Context: " + context);
                view.getContext().startActivity(intent);

            }
        });
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Method 1
                moveFirebaseRecord(holder.databaseReference.child(key), holder.databaseReference2);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myviewholder2 extends  RecyclerView.ViewHolder {
        TextView uploadedby,book_name,category,description;
        ImageButton favourite;
        ImageView bookimg;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        DatabaseReference databaseReference2;
        public myviewholder2(@NonNull View itemView) {
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
        }
    }
    public void moveFirebaseRecord(DatabaseReference fromPath, final DatabaseReference toPath){
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
