package com.ucse.scrollable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ucse.scrollable.aggregates.ApiCallerService;
import com.ucse.scrollable.aggregates.Item;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {
    private final ApiCallerService service;

    MainActivity() {
        service = new ApiCallerService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Item> items = service.getItemsToList();
        //ArrayAdapter<Item> listDataAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, R.id.listRowTextView, items);
        MyArrayAdapter listDataAdapter = new MyArrayAdapter(this.getApplicationContext(), items);
        this.setListAdapter(listDataAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        // Get the list data adapter.
        ListAdapter listAdapter = listView.getAdapter();
        // Get user selected item object.
        Object selectItemObj = listAdapter.getItem(position);
        Item item = (Item)selectItemObj;

        // Create an AlertDialog to show.
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(item.getStrDrink());
        alertDialog.show();
    }

}
