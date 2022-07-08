package com.cigrastudio.booklibrary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cigrastudio.booklibrary.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favouriteFragment#newInstance} factory method to
 * create an instance of this com.cigrastudio.booklibrary.fragment.
 */
public class favouriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the com.cigrastudio.booklibrary.fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this com.cigrastudio.booklibrary.fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of com.cigrastudio.booklibrary.fragment favoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favouriteFragment newInstance(String param1, String param2) {
        favouriteFragment fragment = new favouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }
}