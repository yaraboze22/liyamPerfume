package com.example.yaredperfume.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yaredperfume.Adapter.ImageAdapter;
import com.example.yaredperfume.Modal.ImageData;
import com.example.yaredperfume.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class home extends Fragment {
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private List<ImageData> imageDataList;

    private FirebaseFirestore db;
    Fragment fragment=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        gridView = view.findViewById(R.id.idGVperfume);
        Button cart=view.findViewById(R.id.cart);

        imageDataList = new ArrayList<>();
        imageAdapter = new ImageAdapter(getContext(), imageDataList);
        gridView.setAdapter(imageAdapter);

        db = FirebaseFirestore.getInstance();

        fetchImagesFromFirestore();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fragment=new PerfumeFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            }
        });

        return view;
    }

    private void fetchImagesFromFirestore() {
        db.collection("images").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String id = document.getId();
                        String url = document.getString("url");
                        String name = document.getString("name");
                        String description = document.getString("description");
                        String price = document.getString("price");
                        boolean liked = document.getBoolean("liked") != null && Boolean.TRUE.equals(document.getBoolean("liked"));

                        imageDataList.add(new ImageData(id,url, name, description, price, liked));
                    }
                    imageAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), " error getting documents: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}