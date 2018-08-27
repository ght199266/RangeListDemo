package com.lly.rangelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lly.library.RangeTools;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    RangeTools rangeTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new LvDividerDecoration(10));
        recycler_view.setAdapter(new ListAdapter(recycler_view));
        rangeTools = new RangeTools(recycler_view, R.id.rv_view);
    }

}

