package com.androidexample.mvc;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;

public class FirstScreen extends Activity {
	public static ArrayList<ModelProducts> productList = new ArrayList<ModelProducts>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);

		final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
		final Button secondBtn = (Button) findViewById(R.id.second);

		// Get Global Controller Class object (see application tag in
		// AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();

		/****************** Create Dummy Products Data ***********/

		ModelProducts productObject = null;
			//String [] names = {"unga","milk","sugar"};
		
		for (int i = 1; i <= 1000; i++) {
			int price = 10 + i;
			// Create product model class object
			productObject = new ModelProducts("Product " + i,   "Description "
					+ i, price);

			// store product object to arraylist in controller
			aController.setProducts(productObject);

		}

		/****************** Products Data Creation End ***********/

		/******* Create view elements dynamically and show on activity ******/

		// Product arraylist size
		int ProductsSize = aController.getProductsArraylistSize();

		// create the layout params that will be used to define how your
		// button will be displayed
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		/******** Dynamically create view elements - Start **********/

		
		productList.add(new ModelProducts("Unga", "maize flour", 120));
		productList.add(new ModelProducts("Tuzo", "maize flour", 120));
		productList.add(0, new ModelProducts("Mumias", "maize flour", 120));
		productList.add(new ModelProducts("rice", "Dawati", 170));
		productList.add(new ModelProducts("soap", "Geisha", 120));
		productList.add(0, new ModelProducts("Bread", "supa loaf", 50));
		productList.add(new ModelProducts("keniget", "maize flour", 120));
		productList.add(new ModelProducts("ilara", "maize flour", 120));
		productList.add(0, new ModelProducts("sony", "maize flour", 120));
		productList.add(new ModelProducts("sugar", "Dawati", 170));
		productList.add(new ModelProducts("salt", "Geisha", 120));
		productList.add(0, new ModelProducts("biscuits", "supa loaf", 50));
		
		for (int j = 0; j < productList.size(); j++) {
			// Get probuct data from product data arraylist
			ModelProducts product = productList.get(j);
			
			String pName = product.getProductName();
			int pPrice = product.getProductPrice();

			// Create LinearLayout to view elemnts
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);

			TextView productNameTextView = new TextView(this);
			productNameTextView.setText(" " + pName + " ");

			// Add textView to LinearLayout
			ll.addView(productNameTextView);
			
			
							


			TextView price = new TextView(this);
			price.setText("  Ksh" + pPrice + "     ");

			// Add textView to LinearLayout
			ll.addView(price);

			final Button btn = new Button(this);
			btn.setId(j + 1);
			btn.setText("Add To Cart");

			final Button btndelete = new Button(this);
			btndelete.setId(j + 1);
			btndelete.setText("Delete");

			// set the layoutParams on the button
			btndelete.setLayoutParams(params);

			// set the layoutParams on the button
			btn.setLayoutParams(params);

			final int index = j;

			// Create click listener for dynamically created button
			btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					// Clicked button index
					Log.i("TAG", "index :" + index);

					// Get product instance for index
					ModelProducts tempProductObject = aController
							.getProducts(index);

					// Check Product already exist in Cart or Not
					if (!aController.getCart().checkProductInCart(
							tempProductObject)) {
						btn.setText("Added");

						// Product not Exist in cart so add product to
						// Cart product arraylist
						aController.getCart().setProducts(tempProductObject);

						Toast.makeText(
								getApplicationContext(),
								"Now Cart size: "
										+ aController.getCart().getCartSize(),
								Toast.LENGTH_SHORT).show();
					} else {
						// Cart product arraylist contains Product
						Toast.makeText(
								getApplicationContext(),
								"Product " + (index + 1)
										+ " already added in cart.",
								Toast. LENGTH_SHORT).show();
					}
				}
			});
			btndelete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();


					// Clicked button index
					Log.i("TAG", "index :" + index);
					// Get product instance for index
					ModelProducts tempProductObject = aController
							.getProducts(index);  
			         
			      aController.getCart().deleteProducts(tempProductObject);
			      if (!aController.getCart().checkProductInCart(
							tempProductObject)) {
			    	  btn.setText("Add To cart");
			      }
			      

				}
			});
			
			// Add button to LinearLayout
						ll.addView(btn);
						
			// Add button to LinearLayout
			ll.addView(btndelete);

			

			// Add LinearLayout to XML layout
			lm.addView(ll);
		}

		/******** Dynamically create view elements - End **********/

		secondBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(getBaseContext(), SecondScreen.class);
				startActivity(i);
			}
		});
	}
}
