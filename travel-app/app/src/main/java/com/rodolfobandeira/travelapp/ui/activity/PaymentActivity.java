package com.rodolfobandeira.travelapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rodolfobandeira.travelapp.R;
import com.rodolfobandeira.travelapp.model.TravelPackage;
import com.rodolfobandeira.travelapp.util.CurrencyUtil;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");
        loadSelectedPackage();
    }

    private void loadSelectedPackage() {
        Intent intent = getIntent();
        if (intent.hasExtra("selectedPackage")) {
            TravelPackage selectedPackage = (TravelPackage) intent.getSerializableExtra("selectedPackage");

            showPrice(selectedPackage);
            finishPaymentButtonHandler(selectedPackage);
        }
    }

    private void showPrice(TravelPackage selectedPackage) {
        TextView price = findViewById(R.id.payment_amount);
        price.setText(CurrencyUtil.formatToCanada(selectedPackage.getPrice()));
    }

    private void finishPaymentButtonHandler(TravelPackage selectedPackage) {
        Button finishPaymentButton = findViewById(R.id.button2);
        finishPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, PurchaseSummaryActivity.class);
                intent.putExtra("selectedPackage", selectedPackage);
                startActivity(intent);
            }
        });
    }
}