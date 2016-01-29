package appewtc.masterung.crimeinformer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showNameTextView;
    private String nameString, surnameString, idString;
    private int idAnInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        bindWidget();

        //ShowName
        showName();

    }   // Main Method

    private void showName() {

        //Receive Value From Intent
        idString = getIntent().getStringExtra("ID");
        idAnInt = Integer.parseInt(idString);

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_NAME, null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(idAnInt - 1);
        nameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_NAME));
        surnameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_SURNAME));

        showNameTextView.setText(nameString + " " + surnameString);

    }   // showName

    private void bindWidget() {

        showNameTextView = (TextView) findViewById(R.id.textView7);

    }   // bindWidget

}   // Main Class
