package com.example.snakhauspromax;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnMeal1, btnMeal2, btnMeal3;
    Button btnAddCart, btnViewCart;
    Button btnAddQty, btnSubQty;
    ImageView imageView;
    SeekBar seekBarSalt, seekBarSpice;
    TextView textViewName, textViewDesc, textViewPrice;
    TextView textViewSalt, textViewSpice, textViewQty;
    ToggleButton toggleButton;

    private ArrayList<Integer> imageList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> priceList = new ArrayList<>();
    private ArrayList<String> addOnsList = new ArrayList<>();
    private ArrayList<String> saltList = new ArrayList<>();
    private ArrayList<String> spiceList = new ArrayList<>();
    private ArrayList<String> qtyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnMeal1 = findViewById(R.id.btnMeal1);
        btnMeal2 = findViewById(R.id.bntMeal2);
        btnMeal3 = findViewById(R.id.btnMeal3);
        imageView = findViewById(R.id.imageView);
        textViewName = findViewById(R.id.textViewName);
        textViewDesc = findViewById(R.id.textViewDesc);
        textViewPrice = findViewById(R.id.textViewPrice);
        seekBarSalt = findViewById(R.id.seekBarSalt);
        textViewSalt = findViewById(R.id.textViewSalt);
        seekBarSpice = findViewById(R.id.seekBarSpice);
        textViewSpice = findViewById(R.id.textViewSpice);
        toggleButton = findViewById(R.id.toggleButtonSoup);
        btnAddQty = findViewById(R.id.btnAddQty);
        btnSubQty = findViewById(R.id.btnSubQty);
        textViewQty = findViewById(R.id.textViewQty);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnViewCart = findViewById(R.id.btnViewCart);

        btnMeal1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                textViewName.setText("Pork Chop Meal");
                textViewDesc.setText("with Potato side dish");
                textViewPrice.setText("Php 125.20");
                imageView.setImageResource(R.drawable.pork);
            }
        });
        btnMeal2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                textViewName.setText("Chicken Meal");
                textViewDesc.setText("with Caesar Salad");
                textViewPrice.setText("Php 120.50");
                imageView.setImageResource(R.drawable.chicken);
            }
        });
        btnMeal3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                textViewName.setText("Fish Fillet Meal");
                textViewDesc.setText("with Grilled Mango");
                textViewPrice.setText("Php 122.20");
                imageView.setImageResource(R.drawable.fish);
            }
        });

        seekBarSalt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSalt.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarSpice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSpice.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        toggleButton.setText("No Soup");
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean toggle = buttonView.isChecked();
                if (toggle) {
                    ((ToggleButton)buttonView).setTextOn("With Soup");
                } else {
                    ((ToggleButton)buttonView).setTextOff("No Soup");
                }
            }
        });

        int[] value = {0};
        btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value[0]++;
                textViewQty.setText(String.valueOf(value[0]));
            }
        });

        btnSubQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value[0] > 0) {
                    value[0]--;
                    textViewQty.setText(String.valueOf(value[0]));
                }
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = Integer.parseInt(textViewQty.getText().toString());

                if (quantity == 0) {
                    showAlertDialog("Order Quantity Error", "Please add at least one quantity per order");
                } else {
                    String name = textViewName.getText().toString();
                    String price = textViewPrice.getText().toString().replace("Php", "").trim();
                    String addOns = toggleButton.getText().toString();
                    String salt = textViewSalt.getText().toString().replace("%", "").trim();
                    String spice = textViewSpice.getText().toString().replace("%", "").trim();
                    String qty = textViewQty.getText().toString();
    
                    nameList.add(name);
                    priceList.add(price);
                    addOnsList.add(addOns);
                    saltList.add(salt);
                    spiceList.add(spice);
                    qtyList.add(qty);

                    switch (name) {
                        case "Pork Chop Meal":
                            imageList.add(R.drawable.pork);
                            break;
                        case "Chicken Meal":
                            imageList.add(R.drawable.chicken);
                            break;
                        case "Fish Fillet Meal":
                            imageList.add(R.drawable.fish);
                            break;
                        default:

                            break;
                    }

                    showAlertDialog("Order Added", "Successfully added an order");
                }
            }
        });

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartIsEmpty()) {
                    showAlertDialog("Error", "Your cart is empty. Please add items before viewing the cart. ");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putIntegerArrayListExtra("images", imageList);
                    intent.putStringArrayListExtra("name", nameList);
                    intent.putStringArrayListExtra("price", priceList);
                    intent.putStringArrayListExtra("addOns", addOnsList);
                    intent.putStringArrayListExtra("salt", saltList);
                    intent.putStringArrayListExtra("spice", spiceList);
                    intent.putStringArrayListExtra("qty", qtyList);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageList = data.getIntegerArrayListExtra("images");
            nameList = data.getStringArrayListExtra("name");
            priceList = data.getStringArrayListExtra("price");
            addOnsList = data.getStringArrayListExtra("addOns");
            saltList = data.getStringArrayListExtra("salt");
            spiceList = data.getStringArrayListExtra("spice");
            qtyList = data.getStringArrayListExtra("qty");
        }
    }

    private boolean cartIsEmpty() {
        return nameList.isEmpty() || priceList.isEmpty() || qtyList.isEmpty();
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}