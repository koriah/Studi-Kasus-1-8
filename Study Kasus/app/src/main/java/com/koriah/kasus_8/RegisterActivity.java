package com.koriah.kasus_8;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button btnregis;
    EditText textemail, textpassword, textfirst, textlast;
    Intent intent;

    int success;
    ConnectivityManager connectivityManager;

    private String url = Server.URL + "register.php";

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (connectivityManager.getActiveNetworkInfo() != null
                    && connectivityManager.getActiveNetworkInfo().isAvailable()
                    && connectivityManager.getActiveNetworkInfo().isConnected()){

            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
        btnregis = (Button) findViewById(R.id.btnregis);
        textemail = (EditText) findViewById(R.id.emailregis);
        textpassword = (EditText) findViewById(R.id.passregis);
        textlast = (EditText) findViewById(R.id.lastregis);
        textfirst = (EditText) findViewById(R.id.firstregis);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textemail.getText().toString();
                String password = textpassword.getText().toString();
                String firstname = textfirst.getText().toString();
                String lastname = textlast.getText().toString();


                if (connectivityManager.getActiveNetworkInfo() != null
                        && connectivityManager.getActiveNetworkInfo().isAvailable()
                        && connectivityManager.getActiveNetworkInfo().isConnected()){
                    checkRegister(email, password, firstname, lastname);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkRegister(final String email, final String password, final String firstname, final String lastname ) {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Register");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response : " + response.toString());
                hideDialog();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.e("Successfully Register", jsonObject.toString());
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        textemail.setText("");
                        textpassword.setText("");
                        textfirst.setText("");
                        textlast.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    //json error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Register Error " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("firstname", firstname);
                params.put("lastname", lastname);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
    private void showDialog(){
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog(){
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
    @Override
    public void onBackPressed(){
        intent = new Intent(RegisterActivity.this, Login.class);
        finish();
        startActivity(intent);
    }
}
