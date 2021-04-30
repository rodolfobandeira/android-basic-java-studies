package com.rodolfobandeira.travelapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.rodolfobandeira.travelapp.R;
import com.rodolfobandeira.travelapp.dao.TravelPackageDAO;
import com.rodolfobandeira.travelapp.model.TravelPackage;
import com.rodolfobandeira.travelapp.ui.adapter.PackagesListAdapter;

import java.util.List;

public class PackagesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_list);
        ListView packagesList = findViewById(R.id.packages_listview);

        List<TravelPackage> packages = new TravelPackageDAO().list();

        packagesList.setAdapter(new PackagesListAdapter(packages, this));
    }
}