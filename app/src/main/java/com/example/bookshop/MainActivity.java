package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int quantity = 0;
    Spinner spinner;
    List<String> spinnerItems;
    ArrayAdapter spinnerAdapter;
    Map<String, Double> goodsMap;
    Map<String, Integer> booksImg;
    String goodsName;
    double price;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerItems = new ArrayList<>();
        spinnerItems.add("Python");
        spinnerItems.add("Java");
        spinnerItems.add("JavaScript");

        goodsMap = new HashMap<>();
        goodsMap.put("Python", 15.0);
        goodsMap.put("Java", 17.0);
        goodsMap.put("JavaScript", 10.0);

        booksImg = new HashMap<>();
        booksImg.put("Python", R.drawable.book_python);
        booksImg.put("Java", R.drawable.book_java);
        booksImg.put("JavaScript", R.drawable.book_javascript);

        spinnerAdapter = new ArrayAdapter<>(this,
                                    android.R.layout.simple_spinner_item,
                                    spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseQuantity(View view) {
        TextView numberTextView = findViewById(R.id.numberTextView);
        numberTextView.setText(Integer.toString(++quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(Double.toString(quantity * price));
    }

    public void decreaseQuantity(View view) {
        if (quantity == 0) return;
        TextView numberTextView = findViewById(R.id.numberTextView);
        numberTextView.setText(Integer.toString(--quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(Double.toString(quantity * price));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(Double.toString(quantity * price));

        ImageView imageView = findViewById(R.id.bookImageView);
        imageView.setImageResource(booksImg.get(goodsName));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createOrder(View view) {
        Order order = new Order();
        editText = findViewById(R.id.editTextText);
        order.userName = editText.getText().toString();
        order.orderPrice = price * quantity;
        order.goodsName = goodsName;
        order.quantity = quantity;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", price);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);
    }
}