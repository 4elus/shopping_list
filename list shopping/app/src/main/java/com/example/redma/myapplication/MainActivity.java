package com.example.redma.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        AlertDialog.Builder ab = new AlertDialog.Builder(this);


        if(products.size()==0){
            products.add(new Product("Apples", "Not Bought"));
            products.add(new Product("Tea", "Not Bought"));
            products.add(new Product("Eggs", "Not Bought"));
            products.add(new Product("Milk", "Not Bought"));
            products.add(new Product("Pasta", "Not Bought"));
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
                    products.add(new Product(newItem, "Not Bought"));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        txtOut = (EditText) findViewById(R.id.editText2);
        Button btn_Remove = (Button) findViewById(R.id.btn_remove);


        btn_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   int ind = Integer.parseInt(txtOut.getText().toString());
                   products.remove(ind-1);
                   adapter.notifyDataSetChanged();
               } catch (NumberFormatException e){
                   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                   builder.setTitle("Error")
                           .setMessage("Enter number position!")
                           .setCancelable(false)
                           .setNegativeButton("OK",
                                   new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int id) {
                                           dialog.cancel();
                                       }
                                   });
                   AlertDialog alert = builder.create();
                   alert.show();
               } catch (IndexOutOfBoundsException er){
                   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                   builder.setTitle("Error")
                           .setMessage("Position doesn't exist")
                           .setCancelable(false)
                           .setNegativeButton("OK",
                                   new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int id) {
                                           dialog.cancel();
                                       }
                                   });
                   AlertDialog alert = builder.create();
                   alert.show();
               }
            }
        });

        Button btn_removeAll = (Button) findViewById(R.id.btn_removeAll);

        btn_removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              products.clear();
              adapter.notifyDataSetChanged();

            }
        });

    }
}
