package com.example.snakhauspromax;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvTotal;
    private ListView listView;
    private ArrayList<Integer> imageList;
    private ArrayList<String> nameList, priceList, addOnsList, saltList, spiceList, qtyList;
    private CartAdapter cartAdapter;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);
        tvTotal = findViewById(R.id.textViewTotal);

        Intent intent = getIntent();
        imageList = intent.getIntegerArrayListExtra("images");
        nameList = intent.getStringArrayListExtra("name");
        priceList = intent.getStringArrayListExtra("price");
        addOnsList = intent.getStringArrayListExtra("addOns");
        saltList = intent.getStringArrayListExtra("salt");
        spiceList = intent.getStringArrayListExtra("spice");
        qtyList = intent.getStringArrayListExtra("qty");


        cartAdapter = new CartAdapter(this, nameList, priceList, addOnsList, saltList, spiceList, qtyList, imageList);
        listView.setAdapter(cartAdapter);

        calculateTotal();
    }

    public void calculateTotal() {
        double total = 0;
        for (int i = 0; i < priceList.size(); i++) {
            double price = Double.parseDouble(priceList.get(i));
            int quantity = Integer.parseInt(qtyList.get(i));
            total += price * quantity;
        }
        tvTotal.setText(String.format("Total: Php %.2f", total));
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putIntegerArrayListExtra("images", imageList);
        resultIntent.putStringArrayListExtra("name", nameList);
        resultIntent.putStringArrayListExtra("price", priceList);
        resultIntent.putStringArrayListExtra("addOns", addOnsList);
        resultIntent.putStringArrayListExtra("salt", saltList);
        resultIntent.putStringArrayListExtra("spice", spiceList);
        resultIntent.putStringArrayListExtra("qty", qtyList);
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
}