package com.sheygam.masa_2018_g1_05_12_18_hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LIST_ACTIVITY = 0x01;
    private StoreProvider storeProvider;

    private EditText inputEmail, inputPassword;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storeProvider = new StoreProvider(this);
        super.onCreate(savedInstanceState);
        if(storeProvider.getToken()!=null){
            showList();
        }
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            saveCurrent();
            showList();
        }
    }

    private void showList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent,LIST_ACTIVITY);
    }

    private void saveCurrent() {
        String token = inputEmail.getText() + "&" + inputPassword.getText();
        storeProvider.saveToken(token);
    }

    private void clearLogin() {
        storeProvider.clearToken();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LIST_ACTIVITY){
            if(resultCode == RESULT_OK){
                clearLogin();
            }else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
