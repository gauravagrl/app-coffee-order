package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int calculatePrice(boolean addWhippedCream , boolean addChocolate)
    {
        int price;
        int basePrice = 5;
        if(addWhippedCream)
            basePrice++;
        if(addChocolate)
            basePrice+=2;
        price =quantity * basePrice ;
        return price;    }

    private String createOrderSummary(int price , boolean addWhippedCream , boolean addChocolate , String name)
    {
        String priceMessage = "Name : " + name;
        if(addWhippedCream)
        priceMessage+= "\nWhipped Cream";
        if(addChocolate)
        priceMessage+= "\nChocolate" ;
        priceMessage+= "\nQuantity : " + quantity;
        priceMessage+= "\nTotal :$" + price;
        priceMessage+= "\nThank You";
        return priceMessage;
    }

    public void submitOrder(View view)
    {
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.Name_EditText);
        String name = nameEditText.getText().toString();
        int price;
        price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Coffee Order for "+ name );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }


    public void increment(View view) {
        if (quantity == 100)
        {
            Toast.makeText(getApplicationContext() , "You cannot have more than 100 coffee" , Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++ ;
        display(quantity);
    }
    public void decrement(View view) {
        if(quantity ==1)
        {
            Toast.makeText(getApplicationContext() , "You cannot have less than 1 coffee" , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity-- ;
        display(quantity);
    }
   private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}
