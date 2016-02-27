package appewtc.masterung.crimeinformer;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UserHistoryListView extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private TextView showUserTextView;
    private ListView crimeListView;
    private Button backButton, readAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_list_view);

        //Bind Widget
        bindWidget();

        //Create ListView
        //createListView();

        //Button Controller
        buttonController();

    }   // Main Method

    private void createListView() {

        //Receive Name from Intent
        String strName = getIntent().getStringExtra("Name");    // Name of User Login

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);



    }   // createListView

    private void buttonController() {

        backButton.setOnClickListener(this);
        readAllButton.setOnClickListener(this);

    }   // buttonController

    private void bindWidget() {

        showUserTextView = (TextView) findViewById(R.id.textView15);
        crimeListView = (ListView) findViewById(R.id.listView);
        backButton = (Button) findViewById(R.id.button8);
        readAllButton = (Button) findViewById(R.id.button9);

    }   // bindWidget

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button8:
                finish();
                break;
            case R.id.button9:
                break;
        }   // switch

    }   // onClick

}   // Main Class
