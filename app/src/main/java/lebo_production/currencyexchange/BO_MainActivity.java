package lebo_production.currencyexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BO_MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private static final int MAX_RANDOM = 10000;
    private static final int MIN_RANDOM = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bo);
        final AppCompatEditText guestName = findViewById(R.id.guestName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        final TextView passwordText = findViewById(R.id.password);
        final String password = BO_RandomGenerator.generateRandomPassword(MIN_RANDOM, MAX_RANDOM);
        passwordText.setText(password);

        final EditText confirmation = findViewById(R.id.confirmation);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String confText = confirmation.getText().toString();
                String passText = passwordText.getText().toString();
                String errorMsg = getString(R.string.error);
                String guestText = guestName.getText().toString();
                if (guestText.isEmpty()) {
                    guestName.setError(errorMsg);
                }
                if (confText.isEmpty()) {
                    confirmation.setError(errorMsg);
                }
                if (confText.equals(passText) && !guestText.isEmpty()) {
                    Intent intent = new Intent(BO_MainActivity.this, BO_SecondActivity.class);
                    intent.putExtra("name", guestText);
                    startActivity(intent);
                }
            }
        });
    }

}
