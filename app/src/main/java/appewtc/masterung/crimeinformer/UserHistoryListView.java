package appewtc.masterung.crimeinformer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        //Delete SQLite
        deleteSQLite();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

        //Create ListView
        createListView();

        //Button Controller
        buttonController();

    }   // Main Method

    private void clickReadAllAdmin() {

        int intStatus = Integer.parseInt(getIntent().getStringExtra("Status"));

        if (intStatus == 1) {
            //Admin
            startActivity(new Intent(UserHistoryListView.this, ReadAllAdmin.class));
        } else {
            //User
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.nagativeDialog(this, "ขอสงวนสิทธ์",
                    "ขอสงวนสิทธ์ สำหรับ Admin เท่านั้นคะ");
        }

    }   // clickReadAll

    @Override
    protected void onResume() {
        super.onResume();

        deleteSQLite();
        synJSONtoSQLite();

    }

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(ManageTABLE.crime_table, null, null);
    }

    private void synJSONtoSQLite() {

        //Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        InputStream inputStream = null;
        String strJSON = null;
        String strURL = "http://swiftcodingthai.com/jar/php_get_crime.php";

        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(strURL);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(strLine);
            }
            inputStream.close();
            strJSON = stringBuilder.toString();

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String strInformer = jsonObject.getString(ManageTABLE.column_Informer);
                String strDate = jsonObject.getString(ManageTABLE.column_Date);
                String strLat = jsonObject.getString(ManageTABLE.column_Lat);
                String strLng = jsonObject.getString(ManageTABLE.column_Lng);
                String strCategory = jsonObject.getString(ManageTABLE.column_Category);
                String strCrime = jsonObject.getString(ManageTABLE.column_Crime);
                String strDetail = jsonObject.getString(ManageTABLE.column_Detail);

                ManageTABLE manageTABLE = new ManageTABLE(this);
                manageTABLE.addCrime(strInformer, strDate, strLat,
                        strLng, strCategory, strCrime, strDetail);

            }   // for

        } catch (Exception e) {
            Log.d("2April", "myError ==> " + e.toString());
        }



    }   // synJSON

    private void createListView() {

        //Receive Name from Intent
        String strName = getIntent().getStringExtra("Name");    // Name of User Login

        showUserTextView.setText("ข้อมูลการแจ้งเหตุของ " + strName);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM crimeTABLE WHERE Informer = " + "'" + strName + "'", null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();
        String[] crimeStrings = new String[intCount];
        String[] categoryStrings = new String[intCount];
        String[] dateStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {

            crimeStrings[i] = cursor.getString(6);
            categoryStrings[i] = cursor.getString(5);
            dateStrings[i] = cursor.getString(2);

            cursor.moveToNext();
        } // for
        cursor.close();

        UserAdapter userAdapter = new UserAdapter(this, crimeStrings,
                categoryStrings, dateStrings);

        crimeListView.setAdapter(userAdapter);

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
                clickReadAllAdmin();
                break;
        }   // switch

    }   // onClick

}   // Main Class
