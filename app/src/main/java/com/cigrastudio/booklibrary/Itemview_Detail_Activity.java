package com.cigrastudio.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Itemview_Detail_Activity extends AppCompatActivity {
        TextView txtbook_name,txtbook_category,txtbook_description,txtuploaded;
        ImageView imageview;
       String bookimg;
        String book_name,category,description,uploadedby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview_detail);
        txtbook_name=findViewById(R.id.book_itemview_activity);
        txtbook_category=findViewById(R.id.category_itemview_activity);
        txtbook_description=findViewById(R.id.description_itemview_activity);
        txtuploaded=findViewById(R.id.uploadedby_itemview_activity);
        imageview=findViewById(R.id.image_itemview_activity);
        getAndSetIntentData();

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("book_name")&&getIntent().hasExtra("category")&&getIntent().hasExtra("uploadedby")&&getIntent().hasExtra("bookimg")){
             book_name = getIntent().getStringExtra("book_name");
             category=getIntent().getStringExtra("category");
             description=getIntent().getStringExtra("description");
             uploadedby=getIntent().getStringExtra("uploadedby");
             bookimg=getIntent().getStringExtra("bookimg");
             imageview.setImageURI(Uri.parse(bookimg));
             txtbook_name.setText(book_name);
             txtbook_category.setText(category);
             txtbook_description.setText(description);
             txtuploaded.setText(uploadedby);

        }
//        else{
//            Toast.makeText(this, R.string.strData0, Toast.LENGTH_SHORT).show();
//        }
    }
}
