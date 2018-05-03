package com.moqod.android.sample.diff;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.moqod.android.sample.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements EventListener {

    private Random mRandom = new Random();
    private ArrayList<SimpleViewModel> mTestData = new ArrayList<>();
    private DiffAdapter mDiffAdapter;

    {
        for (int i = 0; i < 5; i++) {
            mTestData.add(new SimpleViewModel(i, "Test item " + i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Math.max(mRandom.nextInt() % mTestData.size(), 0);
                int id = mTestData.size();
                mTestData.add(index, new SimpleViewModel(id, "Test item " + id));
                mDiffAdapter.setData(mTestData);
            }
        });

        mDiffAdapter = new DiffAdapter(this);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.content_main_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mDiffAdapter);

        mDiffAdapter.setData(mTestData);
    }

    @Override
    public void onDeleteClicked(SimpleViewModel viewModel) {
        mTestData.remove(viewModel);
        mDiffAdapter.setData(mTestData);
    }
}
