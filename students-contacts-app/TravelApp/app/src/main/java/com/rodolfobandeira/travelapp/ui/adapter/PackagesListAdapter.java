package com.rodolfobandeira.travelapp.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodolfobandeira.travelapp.R;
import com.rodolfobandeira.travelapp.model.TravelPackage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PackagesListAdapter extends BaseAdapter {

    private final List<TravelPackage> packages;
    private final Context context;

    public PackagesListAdapter(List<TravelPackage> packages, Context context) {
        this.packages = packages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return packages.size();
    }

    @Override
    public TravelPackage getItem(int position) {
        return packages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View createdView = LayoutInflater
                .from(context)
                .inflate(
                        R.layout.package_item,
                        parent,
                        false
                );

        TravelPackage packageItem = packages.get(position);

        populateCity(createdView, packageItem);
        populateImage(createdView, packageItem);
        populateDays(createdView, R.id.package_item_days, packageItem.getDays() + " days");
        populatePrice(createdView, packageItem);

        return createdView;
    }

    private void populateImage(View createdView, TravelPackage packageItem) {
        ImageView image = createdView.findViewById(R.id.package_item_image);
        Drawable drawableImagePackage = getDrawable(packageItem);
        image.setImageDrawable(drawableImagePackage);
    }

    private Drawable getDrawable(TravelPackage packageItem) {
        Resources resources = context.getResources();
        int drawableId = resources.getIdentifier(packageItem.getImage(), "drawable", context.getPackageName());
        return resources.getDrawable(drawableId);
    }

    private void populatePrice(View createdView, TravelPackage packageItem) {
        TextView price = createdView.findViewById(R.id.package_price);
        String currencyCa = formatMoney(packageItem);
        price.setText(currencyCa);
    }

    private String formatMoney(TravelPackage packageItem) {
        NumberFormat moneyFormat = DecimalFormat.getCurrencyInstance(new Locale("us", "ca"));
        return moneyFormat.format(packageItem.getPrice()).replace("$", "$ ");
    }

    private void populateDays(View createdView, int p, String s) {
        TextView days = createdView.findViewById(p);
        days.setText(s);
    }

    private void populateCity(View createdView, TravelPackage packageItem) {
        TextView city = createdView.findViewById(R.id.package_item_city);
        city.setText(packageItem.getCity());
    }
}
