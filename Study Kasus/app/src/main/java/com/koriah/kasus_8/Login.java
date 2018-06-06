package com.koriah.kasus_8;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;
    Context context;
    AppCompatButton buttonLogin;
    Button linkweb;
    TextView btnregis;
    ProgressDialog progressDialog;
    SharedActivity sharedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = Login.this;


        progressDialog = new ProgressDialog(context);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);
        btnregis = (TextView) findViewById(R.id.btnregis);

        sharedActivity = new SharedActivity(this);

        if (sharedActivity.getSessionStatus()){
            startActivity(new Intent(Login.this, Menu.class));
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login. this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        linkweb = (Button) findViewById(R.id.linkweb);
        linkweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login. this, WebActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void login() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        progressDialog.setMessage("Login Progress");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.URL + "login.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Server.LOGIN_SUCCESS)) {
                            hideDialog();
                            sharedActivity.saveBool(sharedActivity.SESSION_STATUS, true);
                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                            finish();

                        } else {
                            hideDialog();
                            Toast.makeText(context, "Invalid Username dan Passowrd", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Toast.makeText(context, "the Server Unreacheble", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Server.KEY_EMAIL, email);
                params.put(Server.KEY_PASSWORD, password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void gotoMenu() {
        Intent intent = new Intent(context, Menu.class);
        startActivity(intent);
        finish();
    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
