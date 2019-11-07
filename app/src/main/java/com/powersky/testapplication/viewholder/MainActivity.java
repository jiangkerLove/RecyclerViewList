package com.powersky.testapplication.viewholder;


import android.os.Bundle;

import com.powersky.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private List<TreeItem> mTreeItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addlist();
        TreeListAdapter adapter = new TreeListAdapter(this,mTreeItems);

        mRecyclerView = findViewById(R.id.id_listview);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void addlist(){
        mTreeItems.add(new TreeItem("Game")
                .addSons(new TreeItem("Steam")
                        .addSons(new TreeItem("CHi"))
                        .addSons(new TreeItem("Sha")
                                .addSons(new TreeItem("bbbb"))
                                .addSons(new TreeItem("cccc"))))
                .addSons(new TreeItem("LOL"))
                .addSons(new TreeItem("Car")));

    }
}
