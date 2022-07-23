package com.cigrastudio.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Itemview_Detail_Activity extends AppCompatActivity {
        TextView txtbook_name;
        String book_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview_detail);
        txtbook_name=findViewById(R.id.book_itemview_activity);
        getAndSetIntentData();

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("book_name")){
             book_name = getIntent().getStringExtra("book_name");
             txtbook_name.setText(book_name);
        }
//        else{
//            Toast.makeText(this, R.string.strData0, Toast.LENGTH_SHORT).show();
//        }
    }
}
