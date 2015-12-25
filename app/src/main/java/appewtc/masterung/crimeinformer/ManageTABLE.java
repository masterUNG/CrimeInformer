package appewtc.masterung.crimeinformer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 12/25/15 AD.
 */
public class ManageTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_NAME = "userTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SURNAME = "Surname";
    public static final String COLUMN_ID_CARD = "ID_card";
    public static final String COLUMN_PHONENUMBER = "PhoneNumber";
    public static final String COMUMN_EMAIL = "Email";


    public ManageTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addNewValue(String strUser,
                            String strPassword,
                            String strName,
                            String strSurname,
                            String strID_card,
                            String strPhoneNumber,
                            String strEmail) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_NAME, strName);
        objContentValues.put(COLUMN_SURNAME, strSurname);
        objContentValues.put(COLUMN_ID_CARD, strID_card);
        objContentValues.put(COLUMN_PHONENUMBER, strPhoneNumber);
        objContentValues.put(COMUMN_EMAIL, strEmail);

        return writeSqLiteDatabase.insert(TABLE_NAME, null, objContentValues);
    }

}   // Main Class
