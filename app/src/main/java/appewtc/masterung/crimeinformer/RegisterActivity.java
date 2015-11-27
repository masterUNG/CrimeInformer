package appewtc.masterung.crimeinformer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surnameEditText, idCardEditText, phoneEditText, eMailEditText;
    private String nameString, surnameString, idCardString, phoneString, eMailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bind Widget
        bindWidget();


    }   // Main Method

    public void clickOK(View view) {

        //Get Value ot String
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        idCardString = idCardEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        eMailString = eMailEditText.getText().toString().trim();

        //Check Zero
        if (nameString.equals("") ||
                surnameString.equals("") ||
                idCardString.equals("") ||
                phoneString.equals("") ||
                eMailString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.nagativeDialog(RegisterActivity.this, "มีช่องว่าง", "กรุณากรอก ให้ครบทุกช่อง คะ");

        } else {

            //No Space
            confirmDialog();

        }


    }   // clickOK

    private void confirmDialog() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("กรุณาตรวจข้อมูล");
        objBuilder.setMessage("ชื่อ = " + nameString + "\n" +
        "นามสกุล = " + surnameString + "\n" +
        "หมายเลขบัตรประชาชน = " + idCardString + "\n" +
        "เบอร์โทรศัพย์ = " + phoneString + "\n" +
        "eMail = " + eMailString);
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateValueToServer();
                dialogInterface.dismiss();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        objBuilder.show();


    }   // confirmDialog

    private void updateValueToServer() {

    }

    public void clickCancel(View view) {
        finish();
    }


    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        idCardEditText = (EditText) findViewById(R.id.editText3);
        phoneEditText = (EditText) findViewById(R.id.editText4);
        eMailEditText = (EditText) findViewById(R.id.editText5);
    }

}   // Main Class
