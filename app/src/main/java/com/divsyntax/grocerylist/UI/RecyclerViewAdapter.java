package com.divsyntax.grocerylist.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.divsyntax.grocerylist.Activites.DetailsActivity;
import com.divsyntax.grocerylist.Data.DBHandler;
import com.divsyntax.grocerylist.Model.Grocery;
import com.divsyntax.grocerylist.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Grocery> groceryList;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private LayoutInflater inflater;

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
        public Button edit, delete,testB;
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
                    int position = getAdapterPosition();
                    Grocery grocery = groceryList.get(position);
                    Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("name", grocery.getName());
                    intent.putExtra("amount", grocery.getQuantity());
                    intent.putExtra("date", grocery.getDateAdded());
                    intent.putExtra("id", grocery.getId());
                    context.startActivity(intent);

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Grocery grocery = groceryList.get(pos);
                    deletItem(grocery.getId());
                    Log.d("TITLE","CLICKED");
                }
            });

        }

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.deleteBtn:
                    int pos = getAdapterPosition();
                    Grocery grocery = groceryList.get(pos);
                        deletItem(grocery.getId());
                    Log.d("TITLE","CLICKED");
                    break;

                case R.id.editBtn:

                    break;
            }
        }

        public void deletItem(final int id)
        {
            builder = new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.confirmation, null);

            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

            Button nobutton = itemView.findViewById(R.id.noBtn);
            Button yesbutton = itemView.findViewById(R.id.yesBtn);


            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                }
            });
//
//            yesbutton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DBHandler db = new DBHandler(context);
//                    db.deleteGrocery(id);
//                    groceryList.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//                    alertDialog.dismiss();
//                }
//            });
        }
    }
}



















