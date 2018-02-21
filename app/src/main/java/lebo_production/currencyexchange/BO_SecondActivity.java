package lebo_production.currencyexchange;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

public class BO_SecondActivity extends AppCompatActivity {
    Toolbar toolbar;
    Spinner spinner;
    ConstraintLayout layout;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_bo);
        final TextView greetings = (TextView) findViewById(R.id.greetings);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        String grt = getString(R.string.grt);
        greetings.setText(grt + ", " + name);

        layout = (ConstraintLayout) findViewById(R.id.bg_color);
        final TextClock clock = (TextClock) findViewById(R.id.clock);
        final RadioButton galleryRadio = findViewById(R.id.gallery_item);
        final RadioButton mapsRadio = findViewById(R.id.maps_item);
        final RadioButton exchangeRadio = findViewById(R.id.exchange_item);
        final Button continueButton = findViewById(R.id.continue_button);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (galleryRadio.isChecked()) {
                    Intent g_intent = new Intent(BO_SecondActivity.this,
                            BO_GalleryActivity.class);
                    startActivity(g_intent);
                }
                if (mapsRadio.isChecked()) {
                    Intent m_intent = new Intent(BO_SecondActivity.this,
                            BO_MapsActivity.class);
                    startActivity(m_intent);
                }
                if (exchangeRadio.isChecked()) {
                    Intent e_intent = new Intent(BO_SecondActivity.this,
                            BO_ExchangeActivity.class);
                    e_intent.putExtra("name", name);
                    startActivity(e_intent);
                }
            }
        });



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner = (Spinner) findViewById(R.id.spinner);


        toolbar.setTitle(R.string.app_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BO_SecondActivity.this, R.layout.custom_spinner_item_bo, getResources().getStringArray(R.array.bg));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*Toast.makeText(BO_SecondActivity.this,
                        spinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT)
                        .show();*/
                if(spinner.getSelectedItemPosition() == 1) {
                    layout.setBackgroundColor(Color.BLACK);
                    greetings.setTextColor(Color.WHITE);
                    clock.setTextColor(Color.WHITE);
                    galleryRadio.setTextColor(Color.WHITE);
                    mapsRadio.setTextColor(Color.WHITE);
                    exchangeRadio.setTextColor(Color.WHITE);
                }
                if(spinner.getSelectedItemPosition() == 0) {
                    layout.setBackgroundColor(Color.WHITE);
                    greetings.setTextColor(Color.BLACK);
                    clock.setTextColor(Color.BLACK);
                    galleryRadio.setTextColor(Color.BLACK);
                    mapsRadio.setTextColor(Color.BLACK);
                    exchangeRadio.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
