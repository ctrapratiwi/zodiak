package com.example.com.zodiak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");
        initImageBitmaps();
        showRecyclerList();

    }

    private void initImageBitmaps(){
        ZodiakData zodiakData = new ZodiakData();
        zodiakData.initZodiakData();

        mNames = zodiakData.getmNames();
        mImageUrls = zodiakData.getmImageUrls();
        mDate = zodiakData.getmDate();
        mDescription = zodiakData.getmDescription();
    }

    private void showRecyclerList(){
        Log.d(TAG, "showRecyclerList: showrecyclerlist.");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        ListAdapter adapter = new ListAdapter(this, mNames, mImageUrls, mDate, mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showRecyclerGrid(){
        Log.d(TAG, "showRecyclerGrid: showrecyclergrid");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        GridAdapter adapter = new GridAdapter(this, mNames, mImageUrls, mDate, mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void showRecyclerCardView(){
        Log.d(TAG, "showRecyclerCardView: showrecyclercardview");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        CardViewAdapter adapter = new CardViewAdapter(this, mNames, mImageUrls, mDate, mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                setActionBarTitle("Mode List");
                showRecyclerList();
                break;
            case R.id.action_grid:
                setActionBarTitle("Mode Grid");
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                setActionBarTitle("Mode CardView");
                showRecyclerCardView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

}