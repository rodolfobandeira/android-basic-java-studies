package com.rodolfobandeira.travelapp.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {
    public static final String DRAWABLE = "drawable";

    public static Drawable returnDrawable(Context context, String drawableText) {
        Resources resources = context.getResources();
        int dawableId = resources.getIdentifier(drawableText, DRAWABLE, context.getPackageName());

        return resources.getDrawable(dawableId);
    }
}
