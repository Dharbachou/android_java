package by.bstu.pnv.education.laba_1_passworld;

import android.graphics.Rect;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView errorText=null;
    private EditText username=null;
    private EditText  password=null;
    private Button login;
    private int maxLimit = 3, Limit = 0, wrongLettes = 0;
    private static String passworldL = "admin", loginL = "admin";
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_main);
        errorText = (TextView)findViewById(R.id.errorText);
        login = (Button)findViewById(R.id.button1);
        username=(EditText) findViewById (R.id.editText1);
        password=(EditText) findViewById (R.id.editText2);
        username.setHint("Login");
        password.setHint("Password");
        password.setTransformationMethod(new MyPasswordTransformationMethod());

        username.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = loginL.substring(0, s.toString().length());
                Log.d("Hello", s1 + " " + s.toString());
                haveNot();
                check(s1,s.toString(), "login");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        password.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = passworldL.substring(0, s.toString().length());
                haveNot();
                check(s1,s.toString(), "password");
                try {
                    Uri notify = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notify);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void haveNot(){
        errorText.setVisibility(View.GONE);
        if((maxLimit - Limit) <= 0){
            username.setEnabled(false);
            password.setEnabled(false);
            login.setEnabled(false);
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("You have not try!!!");
            exit();
        }
    }

    private void exit(){
        try {
            finish();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void check(String s1, String s2, String type){
        if(s1.equals(s2)) {

        }else{
            ++Limit;
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("Wrong " +  type +"! You only have " + checkTry() + " try");
        }
    }

    private int checkTry(){
        int temp = (maxLimit - Limit);
        return  temp > 0 ? temp : 0;
    }


    public void login(View view) {
        if(username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")){
            Toast.makeText(getApplicationContext(),"You are welcome!",
                    Toast.LENGTH_SHORT).show();
            Limit = 0;
        }
        else {
            if(maxLimit - Limit == 2){
                maxLimit = 5;
            }
            Toast.makeText(getApplicationContext(),"Rong Credentials",
                    Toast.LENGTH_SHORT).show();
        }
    }
}



