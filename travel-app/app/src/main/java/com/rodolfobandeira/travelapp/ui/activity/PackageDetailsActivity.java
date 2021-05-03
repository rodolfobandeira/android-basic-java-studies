package com.rodolfobandeira.travelapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodolfobandeira.travelapp.R;
import com.rodolfobandeira.travelapp.model.TravelPackage;
import com.rodolfobandeira.travelapp.util.CurrencyUtil;
import com.rodolfobandeira.travelapp.util.DaysUtil;
import com.rodolfobandeira.travelapp.util.ResourceUtil;

import java.math.BigDecimal;

public class PackageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);
        setTitle("Package Details");


        // Here we capture the information received from the previous view!
        Intent intent = getIntent();
        if (intent.hasExtra("selectedPackage")) {
            TravelPackage selectedPackage = (TravelPackage) intent.getSerializableExtra("selectedPackage");

            showCity(selectedPackage);
            showImage(selectedPackage);
            showDays(selectedPackage);
            showPrice(R.id.package_details_price, CurrencyUtil.formatToCanada(selectedPackage.getPrice()));
            showDatePeriod(selectedPackage);

            paymentButtonHandler(selectedPackage);
        }
    }

    private void paymentButtonHandler(TravelPackage selectedPackage) {
        Button paymentButton = findViewById(R.id.button);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackageDetailsActivity.this, PaymentActivity.class);
                intent.putExtra("selectedPackage", selectedPackage);
                startActivity(intent);
            }
        });
    }

    private void showDatePeriod(TravelPackage travelPackage) {
        TextView dateRange = findViewById(R.id.package_details_period);
        String dateRangeText = DaysUtil.formatDateRange(travelPackage.getDays());
        dateRange.setText(dateRangeText);
    }

    private void showPrice(int p, String s) {
        TextView price = findViewById(p);
        price.setText(s);
    }

    private void showDays(TravelPackage travelPackage) {
        TextView days = findViewById(R.id.package_details_days);
        String formatedDays = DaysUtil.formatDays(travelPackage.getDays());
        days.setText(formatedDays);
    }

    private void showImage(TravelPackage travelPackage) {
        ImageView image = findViewById(R.id.package_details_image);
        Drawable packageDrawable = ResourceUtil.returnDrawable(this, travelPackage.getImage());
        image.setImageDrawable(packageDrawable);
    }

    private void showCity(TravelPackage travelPackage) {
        TextView city = findViewById(R.id.package_details_city);
        city.setText(travelPackage.getCity());
    }
}