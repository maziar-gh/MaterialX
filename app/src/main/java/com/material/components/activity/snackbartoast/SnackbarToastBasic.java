package com.material.components.activity.snackbartoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SnackbarToastBasic extends AppCompatActivity {

    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_toast_basic);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snackbar & Toast");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        ((AppCompatButton) findViewById(R.id.toast_simple)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Simple Toast", Toast.LENGTH_SHORT).show();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_custom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View layout = getLayoutInflater().inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Custom Toast!");

                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_custom_colored)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View layout = getLayoutInflater().inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setTextColor(Color.WHITE);
                text.setText("Custom Toast!");
                CardView lyt_card = (CardView) layout.findViewById(R.id.lyt_card);
                lyt_card.setCardBackgroundColor(getResources().getColor(R.color.yellow_800));

                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_simple)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Simple Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_with_action)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBarWithAction();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_with_action_indefinite)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBarWithActionIndefinite();
            }
        });
    }

    private void snackBarWithAction() {
        Snackbar snackbar = Snackbar.make(parent_view, "Snackbar With Action", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(parent_view, "UNDO CLICKED!", Snackbar.LENGTH_SHORT).show();
                    }
                });
        snackbar.show();
    }

    private void snackBarWithActionIndefinite() {
        Snackbar snackbar = Snackbar.make(parent_view, "Snackbar With Action INDEFINITE", Snackbar.LENGTH_INDEFINITE)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(parent_view, "UNDO CLICKED!", Snackbar.LENGTH_SHORT).show();
                    }
                });
        snackbar.show();
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
