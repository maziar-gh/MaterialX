package com.material.components.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.material.components.R;
import com.material.components.adapter.AdapterGridSectioned;
import com.material.components.data.DataGenerator;
import com.material.components.model.SectionImage;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabsGallery extends Fragment {

    public FragmentTabsGallery() {
    }

    public static FragmentTabsGallery newInstance() {
        FragmentTabsGallery fragment = new FragmentTabsGallery();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabs_gallery, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Integer> items_img = DataGenerator.getNatureImages(getActivity());
        items_img.addAll(DataGenerator.getNatureImages(getActivity()));
        items_img.addAll(DataGenerator.getNatureImages(getActivity()));
        items_img.addAll(DataGenerator.getNatureImages(getActivity()));
        items_img.addAll(DataGenerator.getNatureImages(getActivity()));

        List<SectionImage> items = new ArrayList<>();
        for (Integer i : items_img) {
            items.add(new SectionImage(i, "IMG_" + i + ".jpg", false));
        }

        int sect_count = 0;
        int sect_idx = 0;
        List<String> months = DataGenerator.getStringsMonth(getActivity());
        for (int i = 0; i < items.size() / 10; i++) {
            items.add(sect_count, new SectionImage(-1, months.get(sect_idx), true));
            sect_count = sect_count + 10;
            sect_idx++;
        }

        //set data and list adapter
        AdapterGridSectioned mAdapter = new AdapterGridSectioned(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridSectioned.OnItemClickListener() {
            @Override
            public void onItemClick(View view, SectionImage obj, int position) {

            }
        });

        return root;
    }
}