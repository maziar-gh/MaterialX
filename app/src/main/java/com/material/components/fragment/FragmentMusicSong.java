package com.material.components.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterListMusicSong;
import com.material.components.data.DataGenerator;
import com.material.components.model.MusicSong;

import java.util.Collections;
import java.util.List;

public class FragmentMusicSong extends Fragment {

    public FragmentMusicSong() {
    }

    public static FragmentMusicSong newInstance() {
        FragmentMusicSong fragment = new FragmentMusicSong();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_music_song, container, false);


        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        List<MusicSong> items = DataGenerator.getMusicSong(getActivity());
        Collections.shuffle(items);

        //set data and list adapter
        AdapterListMusicSong mAdapter = new AdapterListMusicSong(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListMusicSong.OnItemClickListener() {
            @Override
            public void onItemClick(View view, MusicSong obj, int position) {
                Toast.makeText(getActivity(), "Item " + obj.title + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnMoreButtonClickListener(new AdapterListMusicSong.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, MusicSong obj, MenuItem item) {
                Toast.makeText(getActivity(), obj.title + " (" + item.getTitle() + ") clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}