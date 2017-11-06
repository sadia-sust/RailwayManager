package com.example.hp.railwaymanager;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ContributeActivity extends ActionBarActivity {
    TextView txt;
    SharedPreferences sharedPreferences;
    EditText address,googleAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);
        txt= (TextView)findViewById(R.id.txtcon);
        sharedPreferences = getSharedPreferences("shamim", MODE_PRIVATE);
        address = (EditText)findViewById(R.id.answer);
        googleAddress = (EditText)findViewById(R.id.answer2);
        txt.setText("(PLease use + sign insetead of whitespaces)");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contribute, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void contributeClicked(View v)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(address.getText().toString(), googleAddress.getText().toString());
        editor.commit();
        Toast.makeText(ContributeActivity.this,"Contributed!",Toast.LENGTH_SHORT ).show();
        address.setText("");
        googleAddress.setText("");


    }
}
