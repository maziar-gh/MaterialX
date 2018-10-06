package com.material.components.activity.slider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SliderDark extends AppCompatActivity {

    private AppCompatSeekBar seekbar_accent_dark;
    private AppCompatSeekBar seekbar_primary_dark;
    private AppCompatSeekBar seekbar_accent_dark_outline;
    private AppCompatSeekBar seekbar_primary_dark_outline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_dark);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dark");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        // dark theme
        seekbar_accent_dark = (AppCompatSeekBar) findViewById(R.id.seekbar_accent_dark);
        seekbar_accent_dark.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);

        seekbar_primary_dark = (AppCompatSeekBar) findViewById(R.id.seekbar_primary_dark);
        seekbar_primary_dark.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

        seekbar_accent_dark_outline = (AppCompatSeekBar) findViewById(R.id.seekbar_accent_dark_outline);
        seekbar_accent_dark_outline.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);

        seekbar_primary_dark_outline = (AppCompatSeekBar) findViewById(R.id.seekbar_primary_dark_outline);
        seekbar_primary_dark_outline.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

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
