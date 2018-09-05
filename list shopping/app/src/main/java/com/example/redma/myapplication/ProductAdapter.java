package com.example.redma.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

class ProductAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.productList = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product product = productList.get(position);

        viewHolder.nameView.setText(product.getName());

        viewHolder.notBoughtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.totalView.setText("Not Bought");
            }
        });
        viewHolder.boughtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.totalView.setText("Bought");
            }
        });

        viewHolder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Edit this item");

                final EditText et = new EditText(v.getContext());
                builder.setView(et);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewHolder.nameView.setText(et.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        return convertView;
    }

    private String formatValue(int count, String unit){
        return String.valueOf(count) + " " + unit;
    }
    private class ViewHolder {
        final Button boughtButton, notBoughtButton;
        final TextView nameView, totalView;
        ViewHolder(View view){
            boughtButton = (Button) view.findViewById(R.id.boughtButton);
            notBoughtButton = (Button) view.findViewById(R.id.notBoughtButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
            totalView = (TextView) view.findViewById(R.id.countView);
        }
    }
}
