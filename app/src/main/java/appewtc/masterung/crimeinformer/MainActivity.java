package appewtc.masterung.crimeinformer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   // onCreate

    public void clickLogin(View view) {

    }

    public void clickRegister(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }


}   // Main Class
