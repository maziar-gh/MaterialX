package com.material.components.activity.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class DialogBasic extends AppCompatActivity {

    private View parent_view;

    private static final String[] RINGTONE = new String[]{
            "None", "Callisto", "Ganymede", "Luna"
    };

    private boolean[] clicked_colors = new boolean[COLORS.length];
    private static final String[] COLORS = new String[]{
            "Red", "Green", "Blue", "Purple", "Olive"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_basic);
        parent_view = findViewById(android.R.id.content);

        initToolbar();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
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

    public void clickAction(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.confirm_dialog:
                showConfirmDialog();
                break;
            case R.id.alert_dialog:
                showAlertDialog();
                break;
            case R.id.single_choice_dialog:
                showSingleChoiceDialog();
                break;
            case R.id.multi_choice_dialog:
                showMultiChoiceDialog();
                break;
        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Use Google's location services ?");
        builder.setMessage(R.string.middle_lorem_ipsum);
        builder.setPositiveButton(R.string.AGREE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parent_view, "Agree clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.DISAGREE, null);
        builder.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discard draft ?");
        builder.setPositiveButton(R.string.DISCARD, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parent_view, "Discard clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.CANCEL, null);
        builder.show();
    }


    private String single_choice_selected;

    private void showSingleChoiceDialog() {
        single_choice_selected = RINGTONE[0];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phone Ringtone");
        builder.setSingleChoiceItems(RINGTONE, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                single_choice_selected = RINGTONE[i];
            }
        });
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parent_view, "selected : " + single_choice_selected, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.CANCEL, null);
        builder.show();
    }

    private void showMultiChoiceDialog() {
        single_choice_selected = RINGTONE[0];
        clicked_colors = new boolean[COLORS.length];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your preferred colors");
        builder.setMultiChoiceItems(COLORS, clicked_colors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                clicked_colors[i] = b;
            }
        });
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parent_view, "Data submitted", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.CANCEL, null);
        builder.show();
    }
}
