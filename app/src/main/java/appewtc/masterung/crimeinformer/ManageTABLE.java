package appewtc.masterung.crimeinformer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String crime_table = "crimeTABLE";
    public static final String column_Informer = "Informer";
    public static final String column_Date = "Date";
    public static final String column_Lat = "Lat";
    public static final String column_Lng = "Lng";
    public static final String column_Category = "Category";
    public static final String column_Crime = "Crime";
    public static final String column_Detail = "Detail";


    public ManageTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addCrime(String strInformer,
                         String strDate,
                         String strLat,
                         String strLng,
                         String strCategory,
                         String strCrime,
                         String strDetail) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Informer, strInformer);
        contentValues.put(column_Date, strDate);
        contentValues.put(column_Lat, strLat);
        contentValues.put(column_Lng, strLng);
        contentValues.put(column_Category, strCategory);
        contentValues.put(column_Crime, strCrime);
        contentValues.put(column_Detail, strDetail);

        return writeSqLiteDatabase.insert(crime_table, null, contentValues);
    }

    public String[] searchUser(String strUser) {

        try {

            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_NAME,
                    new String[]{COLUMN_ID, COLUMN_USER, COLUMN_PASSWORD,
                            COLUMN_NAME, COLUMN_SURNAME, COLUMN_ID_CARD,
                            COLUMN_PHONENUMBER, COMUMN_EMAIL},
                    COLUMN_USER + "=?",
                    new String[]{String.valueOf(strUser)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    strResult = new String[objCursor.getColumnCount()];
                    for (int i = 0; i < objCursor.getColumnCount(); i++) {
                        strResult[i] = objCursor.getString(i);
                    }   //for
                }   // if2
            }   // if1
            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }


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
