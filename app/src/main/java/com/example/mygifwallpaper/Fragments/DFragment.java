package com.example.mygifwallpaper.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mygifwallpaper.Activities.imgViewActivity;
import com.example.mygifwallpaper.R;

import java.util.ArrayList;

public class DFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<DFragment.ItemModel> arrayList;

    ImageView imageView;


    int image[] = {R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8 };
    DFragment.CustomAdapter adapter;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_d, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        imageView = view.findViewById(R.id.image);


        for (int i = 0; i < image.length; i++) {
            DFragment.ItemModel itemModel = new DFragment.ItemModel();
            //  itemModel.setName(name[i]);
            itemModel.setImage(image[i]);

            //add in array list
            arrayList.add(itemModel);
        }


        adapter = new DFragment.CustomAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }


    public class ItemModel {

        int image;
        // String name;

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }




    }


    public class CustomAdapter extends RecyclerView.Adapter<DFragment.CustomAdapter.viewHolder>{

        ArrayList<DFragment.ItemModel> arrayList;

        public CustomAdapter(ArrayList<DFragment.ItemModel> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public DFragment.CustomAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.gif_data, viewGroup, false);
            return new DFragment.CustomAdapter.viewHolder(view);
        }
        @Override
        public  void onBindViewHolder(DFragment.CustomAdapter.viewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
//            viewHolder.name.setText(arrayList.get(position).getName());
            viewHolder.image.setImageResource(arrayList.get(position).getImage());

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), imgViewActivity.class);
                    intent.putExtra("resId",arrayList.get(position).getImage());
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class viewHolder extends RecyclerView.ViewHolder {
            //            TextView name;
            ImageView image;

            public viewHolder(View itemView) {
                super(itemView);
//                name = (TextView) itemView.findViewById(R.id.name);
                image = (ImageView) itemView.findViewById(R.id.image);

            }
        }
    }
}