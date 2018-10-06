package com.material.components.activity.chip;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterContacts;
import com.material.components.data.DataGenerator;
import com.material.components.model.People;
import com.material.components.model.PeopleChip;
import com.material.components.utils.Tools;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;

import java.util.ArrayList;
import java.util.List;

public class ChipBasic extends AppCompatActivity {

    private ChipsInput mChipsInput;
    private List<PeopleChip> items = new ArrayList<>();
    private List<ChipInterface> items_added = new ArrayList<>();
    private List<People> items_people = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip_basic);

        initToolbar();
        iniComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void iniComponent() {
        items_people = DataGenerator.getPeopleData(this);
        ((ImageButton) findViewById(R.id.contacts)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogContacts();
            }
        });

        mChipsInput = (ChipsInput) findViewById(R.id.chips_input);
        getPeopleChipList();

        // chips listener
        mChipsInput.addChipsListener(new ChipsInput.ChipsListener() {
            @Override
            public void onChipAdded(ChipInterface chip, int newSize) {
                items_added.add(chip);
            }

            @Override
            public void onChipRemoved(ChipInterface chip, int newSize) {
                items_added.remove(chip);
            }

            @Override
            public void onTextChanged(CharSequence text) {
                //Log.e(TAG, "text changed: " + text.toString());
            }
        });
    }

    private void getPeopleChipList() {
        Integer id = 0;
        for (People p : items_people) {
            PeopleChip contactChip = new PeopleChip(id.toString(), p.imageDrw, p.name, p.email);
            // add contact to the list
            items.add(contactChip);
            id++;
        }
        // pass contact list to chips input
        mChipsInput.setFilterableList(items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chips, menu);
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

    private void dialogContacts() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_contacts);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterContacts _adapter = new AdapterContacts(this, items_people);
        recyclerView.setAdapter(_adapter);
        _adapter.setOnItemClickListener(new AdapterContacts.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int position) {
                mChipsInput.addChip(obj.imageDrw, obj.name, obj.email);
                dialog.hide();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
