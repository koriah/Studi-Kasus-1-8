package com.koriah.kasus_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
    SharedActivity sharedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sharedActivity = new SharedActivity(this);

        ImageView menu = (ImageView) findViewById(R.id.menu);
        ImageView order = (ImageView) findViewById(R.id.order);
        ImageView profile = (ImageView) findViewById(R.id.user);
        ImageView logout = (ImageView) findViewById(R.id.logout);

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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu. this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              sharedActivity = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

                sharedActivity.saveBool(sharedActivity.SESSION_STATUS, false);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
//    @Override
//    public void onBackPressed(){
//        Intent intent = new Intent(Menu.this, Login.class);
//        finish();
//        startActivity(intent);
//    }
}
