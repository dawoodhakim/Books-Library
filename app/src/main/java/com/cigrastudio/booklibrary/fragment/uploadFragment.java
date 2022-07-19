package com.cigrastudio.booklibrary.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cigrastudio.booklibrary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class uploadFragment extends Fragment {

    EditText book_name, category, description;
    Button upload;
    DatabaseReference databaseReference;
    String userid;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    Button image_url;
    FirebaseUser user;
    String userId;
    String userName;
    String imgUrl;

    Uri imageUri;
    Bitmap bitmap;

    ImageView imageView;


    StorageReference storageReference;

    String name_book;

    public uploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        book_name = view.findViewById(R.id.book_name_upload_fragment);
        category = view.findViewById(R.id.category_upload_fragment);
        description = view.findViewById(R.id.description_upload_fragment);
        image_url = view.findViewById(R.id.btn_upload_image_fragment);
        upload = view.findViewById(R.id.btn_upload_fragment);
        imageView=view.findViewById(R.id.imageView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");
        userid = firebaseAuth.getCurrentUser().getUid().toString();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        storageReference = FirebaseStorage.getInstance().getReference().child(userId);

        image_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


//        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.child("name").exists()){
//                    userName = (String) snapshot.child("name").getValue();
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        name_book = book_name.getText().toString();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String book_category = category.getText().toString();
                String book_description = description.getText().toString();

                if (name_book.isEmpty()){
                    book_name.setError("Please enter your name");
                }else if (book_category.isEmpty()){
                    category.setError("Please enter your name");
                }else if (book_description.isEmpty()){
                    description.setError("Please enter your name");
                }else{
                    HashMap<String, Object> user = new HashMap<>();
                    user.put("book_name", name_book);
                    user.put("category", book_category);
                    user.put("description", book_description);
                    user.put("uploadedby", userName);
                    user.put("bookimg", imgUrl);
                    databaseReference.child(userid).child("uploads").child(name_book).updateChildren(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        return view;

    }

    private void selectImage() {



        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 00);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 00 && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();

            imageView.setImageURI(imageUri);


            try {
                bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
                byte[] imageByte = stream.toByteArray();

                uploadImage(imageByte);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void uploadImage(byte[] imageByte) {



        ProgressDialog pd = new ProgressDialog(getContext());

        pd.setTitle("Uploading Image...");
        pd.show();

        storageReference.child(System.currentTimeMillis() + ".jpg");

        storageReference.putBytes(imageByte).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                pd.dismiss();

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        databaseReference.child(userId).child("uploads").child(name_book).child("bookimg").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getActivity(), "Image added to database", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage " + (int) progressPercent + "%");
            }
        });
    }
}