package com.example.redma.myapplication;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList();
    private EditText txtInput;
    private EditText txtOut;
    ListView productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(products.size()==0){
            products.add(new Product("Картофель", "кг."));
            products.add(new Product("Чай", "шт."));
            products.add(new Product("Яйца", "шт."));
            products.add(new Product("Молоко", "л."));
            products.add(new Product("Макароны", "кг."));
        }
        productList = (ListView) findViewById(R.id.productList);
        final ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, products);
        productList.setAdapter(adapter);

        txtInput = (EditText) findViewById(R.id.editText);
        Button btn_Add = (Button) findViewById(R.id.btn_add);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = txtInput.getText().toString();

                if(!newItem.isEmpty()){
                    products.add(new Product(newItem, "шт."));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        txtOut = (EditText) findViewById(R.id.editText2);
        Button btn_Remove = (Button) findViewById(R.id.btn_remove);



        btn_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int ind = Integer.parseInt(txtOut.getText().toString());
                products.remove(ind-1);
                adapter.notifyDataSetChanged();
            }
        });

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, selected_item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
