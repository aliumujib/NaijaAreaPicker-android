package com.alium.naijaareapicker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alium.naijaareapicklib.interfaces.OnFilterInteractionListener;
import com.alium.naijaareapicklib.model.LocalGovernmentArea;
import com.alium.naijaareapicklib.model.State;
import com.alium.naijaareapicklib.ui.AreaPicker;

public class TestActivity extends AppCompatActivity implements AreaPicker.OnStateSelected, AreaPicker.OnLGASelected, OnFilterInteractionListener {

    AreaPicker mAreaPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAreaPicker = (AreaPicker) findViewById(R.id.area_pucker);
        mAreaPicker.setmOnLGASelected(this);
        mAreaPicker.setmOnStateSelected(this);
        mAreaPicker.setmOnFilterInteractionListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFiltersInteraction(State state, LocalGovernmentArea localGovernmentArea) {
        Toast.makeText(this, "STATE: " + state.getName() + " : " + "LGA: " + localGovernmentArea.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStateSelected(int position, State state) {
        Toast.makeText(this, "STATE: " + state.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLGASelected(int position, LocalGovernmentArea localGovernmentArea) {
        Toast.makeText(this, "LGA: " + localGovernmentArea.getName(), Toast.LENGTH_SHORT).show();

    }
}
