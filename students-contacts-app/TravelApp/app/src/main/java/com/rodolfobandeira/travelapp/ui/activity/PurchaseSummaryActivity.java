package com.rodolfobandeira.travelapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodolfobandeira.travelapp.R;
import com.rodolfobandeira.travelapp.model.TravelPackage;
import com.rodolfobandeira.travelapp.util.CurrencyUtil;
import com.rodolfobandeira.travelapp.util.DaysUtil;
import com.rodolfobandeira.travelapp.util.ResourceUtil;

import java.math.BigDecimal;

public class PurchaseSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_summary);
        setTitle("Purchase Summary");
        loadSelectedPackage();

    }

    private void loadSelectedPackage() {
        // Here we capture the information received from the previous view!
        Intent intent = getIntent();
        if (intent.hasExtra("selectedPackage")) {
            TravelPackage selectedPackage = (TravelPackage) intent.getSerializableExtra("selectedPackage");

            // TravelPackage payment = new TravelPackage("Sao Paulo", "sao_paulo_sp", 2, new BigDecimal("243.99"));

            showCity(selectedPackage);
            showImage(selectedPackage);
            showPrice(selectedPackage);
            showDatePeriod(selectedPackage);
        }
    }

    private void showDatePeriod(TravelPackage payment) {
        TextView dateRange = findViewById(R.id.purchase_summary_date_period);
        String dateRangeText = DaysUtil.formatDateRange(payment.getDays());
        dateRange.setText(dateRangeText);
    }

    private void showPrice(TravelPackage payment) {
        TextView price = findViewById(R.id.purchase_summary_total_paid);
        price.setText(CurrencyUtil.formatToCanada(payment.getPrice()));
    }

    private void showImage(TravelPackage payment) {
        ImageView image = findViewById(R.id.purchase_summary_package_image);
        Drawable packageDrawable = ResourceUtil.returnDrawable(this, payment.getImage());
        image.setImageDrawable(packageDrawable);
    }

    private void showCity(TravelPackage payment) {
        TextView city = findViewById(R.id.purchase_summary_city);
        city.setText(payment.getCity());
    }
}