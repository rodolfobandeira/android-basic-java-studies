package com.rodolfobandeira.travelapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        setupPackageList();
    }

    private void setupPackageList() {
        ListView packagesList = findViewById(R.id.packages_listview);
        final List<TravelPackage> packages = new TravelPackageDAO().list();
        packagesList.setAdapter(new PackagesListAdapter(packages, this));

        /* Here is where we define the listener that will perform an action if a specific
        item is touched (or clicked)
         */
        packagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* The following line, will call the package details activity view.
                * Intent parameters are "Activity FROM", "Activity TO (Destination)"
                * */
                Intent intent = new Intent(PackagesListActivity.this, PackageDetailsActivity.class);

                TravelPackage selectedPackage = packages.get(position);
                intent.putExtra("selectedPackage", selectedPackage);

                startActivity(intent);
            }
        });
    }
}