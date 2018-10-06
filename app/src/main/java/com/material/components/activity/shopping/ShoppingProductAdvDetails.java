package com.material.components.activity.shopping;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class ShoppingProductAdvDetails extends AppCompatActivity {

    private View parent_view;
    private TextView tv_qty;

    private static int[] array_color_fab = {
            R.id.fab_color_blue,
            R.id.fab_color_pink,
            R.id.fab_color_grey,
            R.id.fab_color_green
    };

    private static int[] array_size_bt = {
            R.id.bt_size_s,
            R.id.bt_size_m,
            R.id.bt_size_l,
            R.id.bt_size_xl
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_product_adv_details);
        parent_view = findViewById(R.id.parent_view);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ecommerce");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_shop_9);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_shop_10);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_shop_11);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_shop_12);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_shop_13);
        tv_qty = (TextView) findViewById(R.id.tv_qty);
        ((FloatingActionButton) findViewById(R.id.fab_qty_sub)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(tv_qty.getText().toString());
                if (qty > 1) {
                    qty--;
                    tv_qty.setText(qty + "");
                }
            }
        });

        ((FloatingActionButton) findViewById(R.id.fab_qty_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(tv_qty.getText().toString());
                if (qty < 10) {
                    qty++;
                    tv_qty.setText(qty + "");
                }
            }
        });

        ((AppCompatButton) findViewById(R.id.bt_add_to_wishlist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(parent_view, "Add to Wishlist", Snackbar.LENGTH_SHORT).show();
            }
        });

        ((AppCompatButton) findViewById(R.id.bt_add_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(parent_view, "Add to Cart", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    public void setSize(View v) {
        Button bt = (Button) v;
        bt.setEnabled(false);
        bt.setTextColor(Color.WHITE);
        for (int id : array_size_bt) {
            if (v.getId() != id) {
                Button bt_unselect = (Button) findViewById(id);
                bt_unselect.setEnabled(true);
                bt_unselect.setTextColor(Color.BLACK);
            }
        }
    }

    public void setColor(View v) {
        ((FloatingActionButton) v).setImageResource(R.drawable.ic_done);
        for (int id : array_color_fab) {
            if (v.getId() != id) {
                ((FloatingActionButton) findViewById(id)).setImageResource(android.R.color.transparent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart_setting, menu);
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
