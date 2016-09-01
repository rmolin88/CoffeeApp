package com.hq.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextWatcher;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

  private EditText etNumCoffee;
  private TextView tvFinalPrice;

  private static int NumCoffee = 0;
  private String sPrice;
  private int iPrice;
  private int iTotal = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etNumCoffee = (EditText) findViewById(R.id.edit_text_order_quantity);
    tvFinalPrice = (TextView) findViewById(R.id.text_total_amt);

    // Convert Price @string/price_val to int
    sPrice = getString(R.string.price_val);
    sPrice = sPrice.substring(1); // Strip "$"
    iPrice = Integer.parseInt(sPrice);
  }

  public void AddBtnAction(View view) {
    NumCoffee++;
    UpdateParams();
  }

  public void SubBtnAction(View view) {
    if (NumCoffee == 0) return;
    NumCoffee--;
    UpdateParams();
  }

  private void UpdateParams() {
    etNumCoffee.setText("" + NumCoffee);
    iTotal = iPrice * NumCoffee;
    tvFinalPrice.setText("$" + iTotal);
  }

  // looking into textwatcher
  public void OrderQtyAction(View view){
    try {
      NumCoffee = Integer.parseInt(etNumCoffee.getText().toString());
    } catch(Exception e) {
      NumCoffee = 0;
    }     
    UpdateParams();
  }
}
