package com.koriah.kasus_8;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * Created by kori'ahtun on 4/11/2018.
 */
public class ListMenuMakanan extends ListActivity {
    String[] makanan = {
            "Nasi",
            "Sayur Asem",
            "Sayur Lodeh",
            "Sop",
            "Pedesan Entog",
            "Rumabah",
            "Lotek",
            "Pindang Gombyang",
            "Ikan Bandeng Goreng",
            "Ikan Buntal Goreng",
            "Sirip Ikan Hiu",
            "Ayam Goreng",
            "Pepes Ayam",
            "Pepes Jamur",
            "Telur Dadar",
            "Telur Ceplok",
            "Tumis Kangkung",
    };

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenumakan);

        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.
                simple_list_item_checked, makanan));

    }
    public void onListItemClick(
            ListView parent, View view, int position, long id)
    {

    }
    public void onClick (View view){
        ListView listView = getListView();
        String menu = "";
        for (int i = 0; i < listView.getCount() ; i++){
            if (listView.isItemChecked(i)) {
                menu += listView.getItemAtPosition(i)+"\n";
            }
        }
        Intent intent = new Intent(this, ListMenuMinuman.class);
        intent.putExtra("makanan", menu);
        startActivity(intent);
    }
}
