package com.hq.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.util.Log;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

  private static final String TAG = "CoffeeApp";

  private EditText etNumCoffee;
  private TextView tvFinalPrice;
  private final TextWatcher twNumCoffee =
      new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence ch, int start, int count, int after) {}

        @Override
        public void beforeTextChanged(CharSequence ch, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {
          try {
            Log.i(TAG, "afterTextChanged got called");
            if (s.length() == 0) NumCoffee = 0;
            else NumCoffee = Integer.parseInt(s.toString());
            UpdateTotalPrice();
          } catch (Exception e) {
            NumCoffee = 0;
          }
        }
      };

  private static int NumCoffee = 0;
  private String sPrice;
  private int iPrice;
  private int iTotal = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Log.i(TAG, "Initializing");
    etNumCoffee = (EditText) findViewById(R.id.edit_text_order_quantity);
    //    Connect the listener to the text view
    Log.i(TAG, "Adding TextChangeListener");
    etNumCoffee.addTextChangedListener(twNumCoffee);
    Log.i(TAG, "TextChangeListener was added");
    tvFinalPrice = (TextView) findViewById(R.id.text_total_amt);

    // Convert Price @string/price_val to int
    sPrice = getString(R.string.price_val);
    sPrice = sPrice.substring(1); // Strip "$"
    iPrice = Integer.parseInt(sPrice);
  }

  public void AddBtnAction(View view) {
    Log.i(TAG, "Add Btn got called");
    NumCoffee++;
    UpdateNumCoffeeDisp();
  }

  public void SubBtnAction(View view) {
    if (NumCoffee == 0) return;
    NumCoffee--;
    UpdateNumCoffeeDisp();
  }

  private void UpdateNumCoffeeDisp() {
    etNumCoffee.setText("" + NumCoffee);
  }

  private void UpdateTotalPrice() {
    iTotal = iPrice * NumCoffee;
    tvFinalPrice.setText("$" + iTotal);
  }
}
