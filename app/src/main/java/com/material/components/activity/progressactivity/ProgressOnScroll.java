package com.material.components.activity.progressactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterGridScrollProgress;
import com.material.components.data.DataGenerator;
import com.material.components.model.ProgressImage;
import com.material.components.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class ProgressOnScroll extends AppCompatActivity {

    private View parent_view;

    private int item_per_display = 6;

    private RecyclerView recyclerView;
    private AdapterGridScrollProgress mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_on_scroll);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        //set data and list adapter
        mAdapter = new AdapterGridScrollProgress(this, item_per_display, generateListItems(item_per_display));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new AdapterGridScrollProgress.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int current_page) {
                loadNextData();
            }
        });

    }

    private void loadNextData() {
        mAdapter.setLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.insertData(generateListItems(item_per_display));
            }
        }, 1500);
    }

    private List<ProgressImage> generateListItems(int count) {
        List<Integer> items_img = DataGenerator.getNatureImages(this);
        items_img.addAll(DataGenerator.getNatureImages(this));

        List<ProgressImage> items = new ArrayList<>();
        for (Integer i : items_img) {
            items.add(new ProgressImage(i, "IMG_" + i + ".jpg", false));
        }

        return items.subList(0, count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}