package com.material.components.activity.menu;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterPeopleWithMore;
import com.material.components.data.DataGenerator;
import com.material.components.model.People;
import com.material.components.utils.Tools;
import com.material.components.widget.SpacesItemDecoration;

public class MenuOverflowList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPeopleWithMore adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_overflow_list);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));

        //set data and list adapter
        adapter = new AdapterPeopleWithMore(this, DataGenerator.getPeopleData(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterPeopleWithMore.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int pos) {
                Toast.makeText(getApplicationContext(), obj.name, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnMoreButtonClickListener(new AdapterPeopleWithMore.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, People obj, MenuItem item) {
                Toast.makeText(getApplicationContext(), obj.name + " (" + item.getTitle() + ") clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
