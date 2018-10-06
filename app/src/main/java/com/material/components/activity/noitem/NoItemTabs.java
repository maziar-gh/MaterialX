package com.material.components.activity.noitem;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class NoItemTabs extends AppCompatActivity {

    public static String title_array[] = {
            "No Feed, yet",
            "No Friend, yet",
            "No Message, yet"
    };
    public static String brief_array[] = {
            "No post in your feed yet!\nTap button and add your first",
            "No friend in your friend list yet!\nSearch and discover new friend",
            "No messages in your inbox yet!\nStart chatting with your friends"
    };
    public static int images_array[] = {
            R.drawable.img_no_feed,
            R.drawable.img_no_friend,
            R.drawable.img_no_chat
    };

    private ViewPager view_pager;
    private TabLayout tab_layout;
    private FloatingActionButton fab_add;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_item_tabs);

        initToolbar();
        initComponent();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FEEDS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.orange_600);
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);

        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);

        view_pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeFabIcon(position);
                toolbar.setTitle(tab_layout.getTabAt(position).getText().toString());
                super.onPageSelected(position);
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                switch (view_pager.getCurrentItem()) {
                    case 0: {
                        text = "Add new post";
                        break;
                    }
                    case 1: {
                        text = "Add new friend";
                        break;
                    }
                    case 2: {
                        text = "Add new chat";
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PlaceholderFragment.newInstance(0), "FEEDS");
        adapter.addFragment(PlaceholderFragment.newInstance(1), "FRIENDS");
        adapter.addFragment(PlaceholderFragment.newInstance(2), "CHATS");
        viewPager.setAdapter(adapter);
    }

    private void changeFabIcon(final int index) {
        fab_add.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index) {
                    case 0: {
                        fab_add.setImageResource(R.drawable.ic_wrap_text);
                        break;
                    }
                    case 1: {
                        fab_add.setImageResource(R.drawable.ic_person_add);
                        break;
                    }
                    case 2: {
                        fab_add.setImageResource(R.drawable.ic_chat);
                        break;
                    }
                }
                fab_add.show();
            }
        }, 400);
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

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_no_item_tabs, container, false);

            int position = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView title = (TextView) rootView.findViewById(R.id.title);
            TextView brief = (TextView) rootView.findViewById(R.id.brief);
            ImageView image = (ImageView) rootView.findViewById(R.id.image);
            title.setText(title_array[position]);
            brief.setText(brief_array[position]);
            image.setImageResource(images_array[position]);

            return rootView;
        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
