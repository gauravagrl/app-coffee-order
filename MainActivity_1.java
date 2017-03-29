package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int calculatePrice()
    {
        int price;
        price =quantity * 5;
        return price;
    }

    private String createOrderSummary(int price)
    {
        String priceMessage = "Name : Gaurav";
        priceMessage+= "\nQuantity : " + quantity;
        priceMessage+= "\nTotal :$" + price;
        priceMessage+= "\nThank You";
        return priceMessage;
    }
    public void submitOrder(View view) {
        int price;
        price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }
    public void increment(View view) {

        quantity++ ;
        display(quantity);
    }
    public void decrement(View view) {
        quantity-- ;
        display(quantity);
    }
   private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}