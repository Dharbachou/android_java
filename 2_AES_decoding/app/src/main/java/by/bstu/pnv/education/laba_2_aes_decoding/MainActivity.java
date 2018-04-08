package by.bstu.pnv.education.laba_2_aes_decoding;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "Code.txt";

    private SecretKeySpec sks = null;
    private String line = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        line = ((EditText)findViewById(R.id.LineForEncoding)).getText().toString();
        if(!line.isEmpty()) {
            newKey();
            encoding();
        }else{
            Toast.makeText(getApplicationContext(), "Line is empty!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void encoding(){
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(line.getBytes());
        } catch (Exception e) {
            Log.e("Crypto", "AES encryption error");
        }
        TextView encodedTextView = (TextView)findViewById(R.id.textViewEncoded);
        encodedTextView.setText("[ENCODED]:\n" +
                Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

        writeToFile(encodedBytes);
    }

    private void newKey(){
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Crypto", "AES secret key spec error");
        }
    }

    private void writeToFile(byte[] s){
        Gson gson = new Gson();
        String jsonString = gson.toJson(new Code(sks, s));
        Log.d("Hello", jsonString);

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d("Hello", "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        sdPath.mkdirs();
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            bw.write(jsonString);
            bw.close();
            Toast.makeText(getApplicationContext(), "File write!!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
