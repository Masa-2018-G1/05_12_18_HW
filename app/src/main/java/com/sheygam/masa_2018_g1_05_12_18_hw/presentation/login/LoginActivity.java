package com.sheygam.masa_2018_g1_05_12_18_hw.presentation.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sheygam.masa_2018_g1_05_12_18_hw.data.HttpProvider;
import com.sheygam.masa_2018_g1_05_12_18_hw.presentation.contactlist.ListActivity;
import com.sheygam.masa_2018_g1_05_12_18_hw.R;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.StoreProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LIST_ACTIVITY = 0x01;
    private StoreProvider storeProvider;

    private EditText inputEmail, inputPassword;
    private Button loginBtn;
    private FrameLayout progressFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storeProvider = StoreProvider.getInstance();
        super.onCreate(savedInstanceState);
        if(storeProvider.getToken()!=null){
            showList();
        }
        setContentView(R.layout.activity_login);
        progressFrame = findViewById(R.id.progressFrame);
        progressFrame.setOnClickListener(null);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            new LoginTask().execute();

        }
    }



    private void showList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent,LIST_ACTIVITY);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LIST_ACTIVITY){
            if(resultCode != RESULT_OK){
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class LoginTask extends AsyncTask<Void,Void,Void>{
        private String email, password;
        private boolean isSuccess = true;

        @Override
        protected void onPreExecute() {
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();
            progressFrame.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            String token = email + "&" + password;
//
//            StoreProvider.getInstance().saveToken(token);
            try {
                String token  = HttpProvider.getInstance().login(email,password);
                StoreProvider.getInstance().saveToken(token);
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressFrame.setVisibility(View.GONE);
            if(isSuccess) {
                showList();
            }else{
                Toast.makeText(LoginActivity.this, "Login error!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
