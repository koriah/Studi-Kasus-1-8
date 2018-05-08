package com.koriah.kasus_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageView menu = (ImageView) findViewById(R.id.menu);
        ImageView order = (ImageView) findViewById(R.id.order);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ItemActivity.class);
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu. this, ListMenuMakanan.class);
                startActivity(intent);
            }
        });
    }
    /*public void itemactivity() {
        Intent item = new Intent(Menu.this, ItemActivity.class);
        startActivity(item);
        finish();
    }*/
}
