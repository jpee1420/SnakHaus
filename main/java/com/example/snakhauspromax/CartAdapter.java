package com.example.snakhauspromax;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter {
    Context context;
    ArrayList<String> nameList;
    ArrayList<String> priceList;
    ArrayList<String> addOnsList;
    ArrayList<String> saltList;
    ArrayList<String> spiceList;
    ArrayList<String> qtyList;
    ArrayList<Integer> imageList;



    public CartAdapter(Context context, ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> addOnsList, ArrayList<String> saltList, ArrayList<String> spiceList, ArrayList<String> qtyList, ArrayList<Integer> imageList) {
        super(context, R.layout.custom_list_view, nameList);
        this.context = context;
        this.nameList = nameList;
        this.priceList = priceList;
        this.addOnsList = addOnsList;
        this.saltList = saltList;
        this.spiceList = spiceList;
        this.qtyList = qtyList;
        this.imageList = imageList;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.custom_list_view, parent, false);

        ImageView imageView = rowView.findViewById(R.id.imageView);
        TextView textView = rowView.findViewById(R.id.textView);
        Button btnDelete = rowView.findViewById(R.id.btnDelete);

        imageView.setImageResource(imageList.get(position));
        textView.setText("Name: " + nameList.get(position) + "\nPrice/meal: " + priceList.get(position) + "\nAdds On: " + addOnsList.get(position)
                + "\nSalt: " + saltList.get(position) + "% | Spice: " + spiceList.get(position) + "%" + "\nNo. of orders: " + qtyList.get(position));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.remove(position);
                priceList.remove(position);
                addOnsList.remove(position);
                saltList.remove(position);
                spiceList.remove(position);
                qtyList.remove(position);
                imageList.remove(position);

                notifyDataSetChanged();
                ((MainActivity2) context).calculateTotal();
            }
        });
        return rowView;
    }
}