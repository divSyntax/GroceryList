package com.divsyntax.grocerylist.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.divsyntax.grocerylist.Model.Grocery;
import com.divsyntax.grocerylist.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Grocery> groceryList;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryList) {
        this.context = context;
        this.groceryList = groceryList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        Grocery grocery = groceryList.get(i);
        viewHolder.grocerynname.setText(grocery.getName());
        viewHolder.grocerycount.setText(grocery.getQuantity());
        viewHolder.date.setText(grocery.getDateAdded());

    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView grocerynname, grocerycount, date;
        public Button edit, delete;
        public int id;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);

            context = ctx;
            grocerynname = itemView.findViewById(R.id.groceryTxt);
            grocerycount = itemView.findViewById(R.id.quantityTxt);
            date = itemView.findViewById(R.id.dateTxt);
            edit = itemView.findViewById(R.id.editBtn);
            delete = itemView.findViewById(R.id.deleteBtn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }



        @Override
        public void onClick(View v) {

        }
    }
}
