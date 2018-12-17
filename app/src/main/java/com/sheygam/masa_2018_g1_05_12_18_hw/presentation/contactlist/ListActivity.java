package com.sheygam.masa_2018_g1_05_12_18_hw.presentation.contactlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sheygam.masa_2018_g1_05_12_18_hw.data.HttpProvider;
import com.sheygam.masa_2018_g1_05_12_18_hw.presentation.info.InfoActivity;
import com.sheygam.masa_2018_g1_05_12_18_hw.R;
import com.sheygam.masa_2018_g1_05_12_18_hw.data.StoreProvider;
import com.sheygam.masa_2018_g1_05_12_18_hw.presentation.model.Contact;

import java.io.IOException;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView contactList;
    private ContactListAdapter adapter;
    private TextView emptyTxt;
    private FrameLayout progressFrame;
    private boolean isProgeress = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        progressFrame = findViewById(R.id.progressFrame);
        contactList = findViewById(R.id.contactList);
        emptyTxt = findViewById(R.id.emptyTxt);
        progressFrame.setOnClickListener(null);
        contactList.setOnItemClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new LoadTask().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra("MODE",InfoActivity.VIEW_MODE);
        intent.putExtra("POS",position);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_item && !isProgeress){
            addNewContact();
        }else if(item.getItemId() == R.id.logout_item && !isProgeress){
            new LogoutTask().execute();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewContact() {
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra("MODE",InfoActivity.EDIT_MODE);
        startActivity(intent);
    }

    private class LogoutTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            isProgeress = true;
            progressFrame.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            StoreProvider.getInstance().clearToken();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            isProgeress = false;
            progressFrame.setVisibility(View.GONE);
            setResult(RESULT_OK);
            finish();
        }
    }

    private class LoadTask extends AsyncTask<Void,Void,Void>{
        private List<Contact> list;

        @Override
        protected void onPreExecute() {
            progressFrame.setVisibility(View.VISIBLE);
            isProgeress = true;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String token = StoreProvider.getInstance().getToken();
            try {
                list = HttpProvider.getInstance().getContacts(token).getContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressFrame.setVisibility(View.GONE);
            isProgeress = false;
            if(list.isEmpty()){
                emptyTxt.setVisibility(View.VISIBLE);
                contactList.setVisibility(View.GONE);
            }else{
                emptyTxt.setVisibility(View.GONE);
                contactList.setVisibility(View.VISIBLE);
                adapter = new ContactListAdapter(list);
                contactList.setAdapter(adapter);
            }
        }
    }
}
