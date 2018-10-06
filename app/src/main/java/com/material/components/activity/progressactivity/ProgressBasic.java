package com.material.components.activity.progressactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class ProgressBasic extends AppCompatActivity {

    private ProgressBar progress_determinate;
    private ProgressBar progress_indeterminate_circular;
    private ProgressBar progress_buffered;
    private ProgressBar progress_indeterminate_determinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_basic);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        progress_determinate = (ProgressBar) findViewById(R.id.progress_determinate);
        progress_indeterminate_circular = (ProgressBar) findViewById(R.id.progress_indeterminate_circular);
        progress_buffered = (ProgressBar) findViewById(R.id.progress_buffered);
        progress_indeterminate_determinate = (ProgressBar) findViewById(R.id.progress_indeterminate_determinate);

        runProgressDeterminate();

        runProgressBuffered();
        runProgressBufferedSecondary();
        runProgressIndeterminateDeterminate();

        runProgressDeterminateCircular();
    }

    private void runProgressDeterminate() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progress_determinate.getProgress() + 10;
                progress_determinate.setProgress(progress);
                if (progress > 100) {
                    progress_determinate.setProgress(0);
                }
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(runnable);
    }

    private void runProgressBuffered() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progress_buffered.getProgress() + 5;
                progress_buffered.setProgress(progress);
                if (progress > 100) {
                    progress_buffered.setProgress(0);
                }
                //if (progress_buffered.getSecondaryProgress() > 0) {
                mHandler.postDelayed(this, 500);
                //}
            }
        };
        mHandler.postDelayed(runnable, 500);
    }

    private void runProgressBufferedSecondary() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progress_buffered.getSecondaryProgress() + 5;
                progress_buffered.setSecondaryProgress(progress);
                if (progress > 100 && progress_buffered.getProgress() <= 10) {
                    progress_buffered.setSecondaryProgress(0);
                }
                mHandler.postDelayed(this, 250);

            }
        };
        mHandler.post(runnable);
    }

    private void runProgressIndeterminateDeterminate() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                if (progress_indeterminate_determinate.isIndeterminate()) {
                    progress_indeterminate_determinate.setIndeterminate(false);
                    mHandler.postDelayed(this, 1000);
                } else {
                    int progress = progress_indeterminate_determinate.getProgress() + 20;
                    progress_indeterminate_determinate.setProgress(progress);
                    if (progress > 100 ) {
                        progress_indeterminate_determinate.setProgress(0);
                        progress_indeterminate_determinate.setIndeterminate(true);
                        mHandler.postDelayed(this, 4000);
                    }else {
                        mHandler.postDelayed(this, 1000);
                    }
                }
            }
        };
        mHandler.post(runnable);
    }

    private void runProgressDeterminateCircular() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progress_indeterminate_circular.getProgress() + 10;
                progress_indeterminate_circular.setProgress(progress);
                if (progress > 100) {
                    progress_indeterminate_circular.setProgress(0);
                }
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(runnable);
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
