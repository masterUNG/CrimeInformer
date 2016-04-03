package appewtc.masterung.crimeinformer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ReadAllAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_admin);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM crimeTABLE", null);
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
        }
        cursor.close();

        UserAdapter userAdapter = new UserAdapter(this, crimeStrings,
                categoryStrings, dateStrings);
        ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(userAdapter);

    }   // Main Method

}   // Main Class
