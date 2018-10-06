package com.material.components.activity.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.fragment.DialogFullscreenFragment;
import com.material.components.model.Event;
import com.material.components.utils.Tools;

public class DialogFullscreen extends AppCompatActivity {

    public static final int DIALOG_QUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fullscreen);

        initToolbar();

        ((AppCompatButton) findViewById(R.id.fullscreen_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFullscreen();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FullScreen");
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

    private void showDialogFullscreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFullscreenFragment newFragment = new DialogFullscreenFragment();
        newFragment.setRequestCode(DIALOG_QUEST_CODE);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new DialogFullscreenFragment.CallbackResult() {
            @Override
            public void sendResult(int requestCode, Object obj) {
                if (requestCode == DIALOG_QUEST_CODE) {
                    displayDataResult((Event) obj);
                }
            }
        });
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

}
