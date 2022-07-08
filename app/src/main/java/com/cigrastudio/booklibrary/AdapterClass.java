package com.cigrastudio.booklibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.name.setText(data.get(position).getName());
        holder.age.setText(data.get(position).getAge());
        holder.hobby.setText(data.get(position).getHobby());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView name,age,hobby;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_recyclerview);
            age=itemView.findViewById(R.id.age_recyclerview);
            hobby=itemView.findViewById(R.id.hobby_recyclerview);
        }
    }
}
