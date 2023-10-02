package com.example.controllapp.menu.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Tasks extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabItem toDo, complete;
    private ViewPager vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

    }// on create

    public void aMenuFromTask(View v){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }
}// class