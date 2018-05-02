package com.koriah.kasus_8;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMenuMinuman extends ListActivity {
    String[] minuman = {
            "Es Teh Manis",
            "Es Tawar",
            "Air Putih",
            "Es Tejus Rasa Gula Batu",
            "Es Tejus Rasa Apel",
            "Es Chocolatos",
            "Es Pop Ice",
            "Es Nutrisari",
            "Es Good Day Freeze",
            "Es Susu Coklat",
            "Es Susu Putih",
            "Es Kacang Hijau"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_minuman);

        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.
                simple_list_item_checked, minuman));
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("makanan", getIntent().getStringExtra("makanan"));
        intent.putExtra("minuman", menu);
        startActivity(intent);
    }
}
