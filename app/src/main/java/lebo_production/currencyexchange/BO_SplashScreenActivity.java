package lebo_production.currencyexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BO_SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_bo);

        Intent intent = new Intent(this, BO_MainActivity.class);
        startActivity(intent);
        finish();
    }
}
