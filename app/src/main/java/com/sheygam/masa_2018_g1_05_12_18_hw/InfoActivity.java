package com.sheygam.masa_2018_g1_05_12_18_hw;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    public static final int VIEW_MODE = 0x01;
    public static final int EDIT_MODE = 0x02;

    private Contact curr;

    private ViewGroup editWrapper, textWrapper;
    private TextView nameTxt, emailTxt, phoneTxt, addressTxt, descTxt;
    private EditText inputName, inputEmail, inputPhone, inputAddress,inputDesc;

    private MenuItem doneItem, deleteItem, editItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        editWrapper = findViewById(R.id.editWrapper);
        textWrapper = findViewById(R.id.textWrapper);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        addressTxt = findViewById(R.id.addressTxt);
        descTxt = findViewById(R.id.descTxt);
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputAddress = findViewById(R.id.inputAddress);
        inputDesc = findViewById(R.id.inputDesc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info,menu);
        doneItem = menu.findItem(R.id.done_item);
        editItem = menu.findItem(R.id.edit_item);
        deleteItem = menu.findItem(R.id.delete_item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.done_item){
            saveCurrent();
            finish();
        }else if(item.getItemId() == R.id.edit_item){
            showEditMode();
            //TODO
        }else if(item.getItemId() == R.id.delete_item){
            deleteCurrent();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteCurrent() {

    }

    private void saveCurrent() {

    }

    private void showEditMode(){
        editWrapper.setVisibility(View.VISIBLE);
        textWrapper.setVisibility(View.GONE);
        deleteItem.setVisible(true);
        doneItem.setVisible(true);
        editItem.setVisible(false);
    }

    private void showViewMode(){
        editWrapper.setVisibility(View.GONE);
        textWrapper.setVisibility(View.VISIBLE);
        deleteItem.setVisible(false);
        doneItem.setVisible(false);
        editItem.setVisible(true);
    }

    private void setCurrentData(){
        nameTxt.setText(curr.getName());
        emailTxt.setText(curr.getEmail());
        phoneTxt.setText(curr.getPhone());
        addressTxt.setText(curr.getAddress());
        descTxt.setText(curr.getDescription());
    }

    private boolean getCurrentData(){
        boolean res = false;
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String address = inputAddress.getText().toString();
        String desc = inputDesc.getText().toString();
        Contact tmp = new Contact(name,email,phone,address,desc);
        if(!valid(tmp)){
            new AlertDialog.Builder(this)
                    .setTitle("Invalidate data!")
                    .setMessage("All fields need be fill!")
                    .setPositiveButton("Ok",null)
                    .setCancelable(false)
                    .create()
                    .show();
        }else{
            curr = tmp;
            res = true;
        }
        return  res;
    }

    private boolean valid(Contact tmp) {
        return !tmp.getName().trim().isEmpty()
                && !tmp.getEmail().trim().isEmpty()
                && !tmp.getPhone().trim().isEmpty()
                && !tmp.getAddress().trim().isEmpty()
                && !tmp.getDescription().trim().isEmpty();
    }
}
