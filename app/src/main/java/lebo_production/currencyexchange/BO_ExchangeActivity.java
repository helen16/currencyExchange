package lebo_production.currencyexchange;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BO_ExchangeActivity extends AppCompatActivity {

    private static final String TAG = "Calendar";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Calendar userDate;


    private static final Double USD_BUY = 3.49;
    private static final Double USD_SELL = 3.51;

    private static final Double EUR_BUY = 4.16;
    private static final Double EUR_SELL = 4.18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_bo);
        final TextView greetings = (TextView) findViewById(R.id.greetings);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grt = getString(R.string.grt);
        greetings.setText(grt + ", " + name);


        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final EditText data = (EditText) findViewById(R.id.data);
        final TextView in = (TextView) findViewById(R.id.in);
        final Button calc = (Button) findViewById(R.id.calculate);
        final TextView res = (TextView) findViewById(R.id.viewResult);
        final ToggleButton tbtn = (ToggleButton) findViewById(R.id.toggleButton);
        final Spinner fcur = (Spinner) findViewById(R.id.firstCurrency);
        final Spinner scur = (Spinner) findViewById(R.id.secondCurrency);

        List categories = new ArrayList<>();
        categories.add("PLN");
        categories.add("USD");
        categories.add("EUR");

        ArrayAdapter dataAdapter = new ArrayAdapter(BO_ExchangeActivity.this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fcur.setAdapter(dataAdapter);
        scur.setAdapter(dataAdapter);
        scur.setSelection(2);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BO_ExchangeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                userDate = Calendar.getInstance();
                userDate.set(Calendar.YEAR, year);
                userDate.set(Calendar.MONTH, month);
                userDate.set(Calendar.DAY_OF_MONTH, day);
                mDisplayDate.setText(date);
            }
        };
        nextButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (userDate == null) {
                    String emptyDate = getString(R.string.emptyDate);
                    Toast.makeText(BO_ExchangeActivity.this,
                            emptyDate, Toast.LENGTH_LONG).show();
                    return;
                }

                Calendar todayBefore18 = Calendar.getInstance();
                todayBefore18.add(Calendar.YEAR, -14);
                if (todayBefore18.before(userDate)) {
                    String incompleteDate = getString(R.string.incompleteDate);
                    Toast.makeText(BO_ExchangeActivity.this,
                            incompleteDate, Toast.LENGTH_LONG).show();

                }
                if (todayBefore18.after(userDate)) {
                    data.setVisibility(View.VISIBLE);
                    fcur.setVisibility(View.VISIBLE);
                    scur.setVisibility(View.VISIBLE);
                    in.setVisibility(View.VISIBLE);
                    calc.setVisibility(View.VISIBLE);
                    res.setVisibility(View.VISIBLE);

                    mDisplayDate.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }
        });


        calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String errorMsg = getString(R.string.error);
                String sameCurr = getString(R.string.sameCurr);


                if (data.length() == 0) {
                    data.setError(errorMsg);
                    return;
                }

                String currency = data.getText().toString();
                Double curr = Double.valueOf(currency);
                if (data.length() != 0) {

                    /* jesli wybrane pln w 1 spinnerie*/
                    if (fcur.getSelectedItemPosition() == 0) {
                        if (scur.getSelectedItemPosition() == 0) {
                            res.setVisibility(View.VISIBLE);
                            res.setText(sameCurr);
                        }
                        if (scur.getSelectedItemPosition() == 1) {
                            long result = Math.round(curr / USD_SELL);
                            String resultText = result + "USD";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }
                        if (scur.getSelectedItemPosition() == 2) {
                            long result = Math.round(curr / EUR_SELL);
                            String resultText = result + "EUR";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }

                    }

                    /* jesli wybrane dolary w 1 spinnerie*/
                    if (fcur.getSelectedItemPosition() == 1) {
                        if (scur.getSelectedItemPosition() == 1) {
                            res.setVisibility(View.VISIBLE);
                            res.setText(sameCurr);
                        }
                        if (scur.getSelectedItemPosition() == 0) {
                            long result = Math.round(curr * USD_BUY);
                            String resultText = result + "PLN";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }
                        if (scur.getSelectedItemPosition() == 2) {
                            long result = Math.round(curr * USD_BUY / EUR_BUY);
                            String resultText = result + "EUR";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }

                    }

                    /*jesli wybrane euro*/
                    if (fcur.getSelectedItemPosition() == 2) {
                        if (scur.getSelectedItemPosition() == 2) {
                            res.setVisibility(View.VISIBLE);
                            res.setText(sameCurr);
                        }
                        if (scur.getSelectedItemPosition() == 0) {
                            long result = Math.round(curr * EUR_BUY);
                            String resultText = result + "PLN";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }
                        if (scur.getSelectedItemPosition() == 1) {
                            long result = Math.round(curr * EUR_BUY / USD_BUY);
                            String resultText = result + "EUR";
                            res.setVisibility(View.VISIBLE);
                            res.setText(resultText);
                            tbtn.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });
        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String on = getString(R.string.toggleButtonOn);
                String of = getString(R.string.toggleButtonOf);
                if (tbtn.isChecked()) {
                    res.setTextSize(30);
                    tbtn.setTextOn(on);
                    tbtn.setBackgroundColor(Color.rgb(255,204,66));
                } else {
                    res.setTextSize(12);
                    tbtn.setTextOff(of);

                    tbtn.setBackgroundColor(Color.WHITE);
                }

            }

        });
    }
}