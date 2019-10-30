package com.ucse.scrollable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.ucse.scrollable.aggregates.Item;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    public MyArrayAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.listRowTextView);
        ImageView itemImage = (ImageView) convertView.findViewById(R.id.listRowImageView);
        // Populate the data into the template view using the data object
        itemName.setText(item.getStrDrink());
        Picasso.get().load(item.getStrDrinkThumb()).into(itemImage);
        // Return the completed view to render on screen
        return convertView;
    }
}
