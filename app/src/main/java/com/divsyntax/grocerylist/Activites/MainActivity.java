package com.divsyntax.grocerylist.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.divsyntax.grocerylist.Data.DBHandler;
import com.divsyntax.grocerylist.Model.Grocery;
import com.divsyntax.grocerylist.R;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private EditText item, amount;
    private Button saveB;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new DBHandler(this);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                popupCreate();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void popupCreate()
    {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);
        item = view.findViewById(R.id.itemTxt);
        amount = view.findViewById(R.id.qytTxt);
        saveB = view.findViewById(R.id.saveBtn);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();


        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!item.getText().toString().isEmpty() && !amount.getText().toString().isEmpty() )
                {
                    saveGrocery(v);
                }

            }
        });

    }

    private void saveGrocery(View v) {

        Grocery grocery = new Grocery();

        String groceryItem = item.getText().toString();
        String amountItem = amount.getText().toString();

        grocery.setName(groceryItem);
        grocery.setQuantity(amountItem);

        dbHandler.addGrocery(grocery);

        Snackbar.make(v,"Item saved", Snackbar.LENGTH_LONG).show();
        Log.d("ITEMS", String.valueOf(dbHandler.getCount()));

    }
}




















