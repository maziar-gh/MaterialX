package com.material.components.activity.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.model.Event;
import com.material.components.utils.Tools;

public class DialogCustom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_custom);

        initToolbar();

        ((AppCompatButton) findViewById(R.id.custom_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Custom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void displayDataResult(Event event) {
        ((TextView) findViewById(R.id.tv_email)).setText(event.email);
        ((TextView) findViewById(R.id.tv_name)).setText(event.name);
        ((TextView) findViewById(R.id.tv_location)).setText(event.location);
        ((TextView) findViewById(R.id.tv_from)).setText(event.from);
        ((TextView) findViewById(R.id.tv_to)).setText(event.to);
        ((TextView) findViewById(R.id.tv_allday)).setText(event.is_allday.toString());
        ((TextView) findViewById(R.id.tv_timezone)).setText(event.timezone);
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

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_event);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final Button spn_from_date = (Button) dialog.findViewById(R.id.spn_from_date);
        final Button spn_from_time = (Button) dialog.findViewById(R.id.spn_from_time);
        final Button spn_to_date = (Button) dialog.findViewById(R.id.spn_to_date);
        final Button spn_to_time = (Button) dialog.findViewById(R.id.spn_to_time);
        final TextView tv_email = (TextView) dialog.findViewById(R.id.tv_email);
        final EditText et_name = (EditText) dialog.findViewById(R.id.et_name);
        final EditText et_location = (EditText) dialog.findViewById(R.id.et_location);
        final AppCompatCheckBox cb_allday = (AppCompatCheckBox) dialog.findViewById(R.id.cb_allday);
        final AppCompatSpinner spn_timezone = (AppCompatSpinner) dialog.findViewById(R.id.spn_timezone);

        String[] timezones = getResources().getStringArray(R.array.timezone);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, timezones);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_timezone.setAdapter(array);
        spn_timezone.setSelection(0);

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.email = tv_email.getText().toString();
                event.name = et_name.getText().toString();
                event.location = et_location.getText().toString();
                event.from = spn_from_date.getText().toString() + " (" + spn_from_time.getText().toString() + ")";
                event.to = spn_to_date.getText().toString() + " (" + spn_to_time.getText().toString() + ")";
                event.is_allday = cb_allday.isChecked();
                event.timezone = spn_timezone.getSelectedItem().toString();
                displayDataResult(event);

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
