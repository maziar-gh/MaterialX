package com.material.components.activity.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SearchFilterProperty extends AppCompatActivity {

    private Button bt_buy, bt_rent, bt_sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_property);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Find Property");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        bt_buy = (Button) findViewById(R.id.bt_buy);
        bt_rent = (Button) findViewById(R.id.bt_rent);
        bt_sell = (Button) findViewById(R.id.bt_sell);

        actionClickButton(bt_rent);

        bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClickButton(v);
            }
        });
        bt_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClickButton(v);
            }
        });
        bt_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClickButton(v);
            }
        });
    }

    private void actionClickButton(View view){
        // reset all
        bt_buy.setSelected(false);
        bt_rent.setSelected(false);
        bt_sell.setSelected(false);
        selectedButton(bt_buy);
        selectedButton(bt_rent);
        selectedButton(bt_sell);

        // set one selected
        view.setSelected(true);
        selectedButton(view);
    }

    public void selectedButton(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            if (b.isSelected()) {
                b.setTextColor(Color.WHITE);
            } else {
                b.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
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
