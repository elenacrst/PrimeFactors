package com.example.elena.primefactors;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> mPrimeFactorsArray = new ArrayList<>();
    private Button mDisplayButton;
    private TextView mDisplayTextView;
    private EditText mNumberEditText;
    private RecyclerView mRecyclerView;

    private NumberAdapter mNumberAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInputValid()) {
                    mDisplayTextView.setText(getString(R.string.prime_factors_result));
                    mDisplayTextView.setTextColor(getResources().getColor(R.color.black));
                    int inputNumber = Integer.valueOf(mNumberEditText.getText().toString());
                    calculatePrimeFactors(inputNumber);
                    setupRecycler();


                }
                hideKeyboard();
                mNumberEditText.clearFocus();
            }
        });


    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mNumberEditText.getWindowToken(),0);
    }


    private boolean isInputValid(){
        int inputNumber;
        if(mNumberEditText.getText().toString().length() == 0){
            mDisplayTextView.setText(getString(R.string.error_empty_text));
            mDisplayTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            mRecyclerView.setVisibility(View.INVISIBLE);
            return false;
        }
        try{
            inputNumber= Integer.valueOf(mNumberEditText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            mRecyclerView.setVisibility(View.INVISIBLE);
            mDisplayTextView.setText(getString(R.string.error_input));
            mDisplayTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            return false;

        }
        if (inputNumber == 0){
            mRecyclerView.setVisibility(View.INVISIBLE);
            mDisplayTextView.setText(getString(R.string.error_zero));
            mDisplayTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            return false;
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        return true;
    }

    private void calculatePrimeFactors(int number){
        mPrimeFactorsArray.clear();
        int oldNumber = number;
        for(int factor = 2; factor <= oldNumber; factor++){
            while(number % factor ==0){
                mPrimeFactorsArray.add(factor);
                number /= factor;
            }
        }
    }

    private void setupRecycler(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mNumberAdapter = new NumberAdapter();
        mNumberAdapter.setNumbersList(mPrimeFactorsArray);
        mRecyclerView.setAdapter(mNumberAdapter);
    }

    private void findViews(){
        mDisplayButton = (Button) findViewById(R.id.button_display);
        mDisplayTextView = (TextView) findViewById(R.id.text_view_display);
        mNumberEditText = (EditText) findViewById(R.id.edit_text_number);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }






}
