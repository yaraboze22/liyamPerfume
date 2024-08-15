package com.example.yaredperfume.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaredperfume.Adapter.PerfumeAdapter;
import com.example.yaredperfume.Modal.Perfume;
import com.example.yaredperfume.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PerfumeFragment extends Fragment {
    private RecyclerView recyclerView;
    private PerfumeAdapter adapter;
    private List<Perfume> perfumeList;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfume, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        perfumeList = new ArrayList<>();
        adapter = new PerfumeAdapter(perfumeList, getContext());
        recyclerView.setAdapter(adapter);

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Liked");

        // Retrieve data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                perfumeList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Perfume perfume = snapshot.getValue(Perfume.class);
                    if (perfume.isLiked()){
                        perfumeList.add(perfume);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });

        return view;
    }
}
