package com.divsyntax.grocerylist.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.divsyntax.grocerylist.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView item, quant, date;

    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        item = findViewById(R.id.DetailName);
        quant = findViewById(R.id.DetailQty);
        date = findViewById(R.id.DetailDate);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            item.setText(bundle.getString("name"));
            quant.setText(bundle.getString("amount"));
            date.setText(bundle.getString("date"));
            id = bundle.getInt("id");
        }

    }
}
