package com.material.components.activity.slider;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SliderColorPicker extends AppCompatActivity {

    private TextView tv_result;
    private int current_red = 127, current_green = 127, current_blue = 210;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_color_picker);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Color Picker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        ((AppCompatButton) findViewById(R.id.bt_pick_color)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPickerDialog();
            }
        });

        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result.setTextColor(Color.rgb(current_red, current_green, current_blue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    private void showColorPickerDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_color_picker);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final View view_result = (View) dialog.findViewById(R.id.view_result);
        final AppCompatSeekBar seekbar_red = (AppCompatSeekBar) dialog.findViewById(R.id.seekbar_red);
        final AppCompatSeekBar seekbar_green = (AppCompatSeekBar) dialog.findViewById(R.id.seekbar_green);
        final AppCompatSeekBar seekbar_blue = (AppCompatSeekBar) dialog.findViewById(R.id.seekbar_blue);

        final TextView tv_red = (TextView) dialog.findViewById(R.id.tv_red);
        final TextView tv_green = (TextView) dialog.findViewById(R.id.tv_green);
        final TextView tv_blue = (TextView) dialog.findViewById(R.id.tv_blue);

        tv_red.setText(current_red + "");
        tv_green.setText(current_green + "");
        tv_blue.setText(current_blue + "");

        seekbar_red.setProgress(current_red);
        seekbar_green.setProgress(current_green);
        seekbar_blue.setProgress(current_blue);

        view_result.setBackgroundColor(Color.rgb(current_red, current_green, current_blue));

        seekbar_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_red.setText(progress + "");
                view_result.setBackgroundColor(Color.rgb(seekbar_red.getProgress(), seekbar_green.getProgress(), seekbar_blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_green.setText(progress + "");
                view_result.setBackgroundColor(Color.rgb(seekbar_red.getProgress(), seekbar_green.getProgress(), seekbar_blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_blue.setText(progress + "");
                view_result.setBackgroundColor(Color.rgb(seekbar_red.getProgress(), seekbar_green.getProgress(), seekbar_blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((Button) dialog.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                current_red = seekbar_red.getProgress();
                current_green = seekbar_green.getProgress();
                current_blue = seekbar_blue.getProgress();

                tv_result.setText("RGB(" + current_red + ", " + current_green + ", " + current_blue + ")");
                tv_result.setTextColor(Color.rgb(current_red, current_green, current_blue));
            }
        });
        ((Button) dialog.findViewById(R.id.bt_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
