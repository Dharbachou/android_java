package by.bstu.pnv.education.laba_2_aes_encoding;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "Code.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
       read();
    }

    private void encoding(SecretKeySpec sks, byte[] line){
        if(line.length > 0){
            byte[] decodedBytes = null;
            try {
                Cipher c = Cipher.getInstance("AES");
                c.init(Cipher.DECRYPT_MODE, sks);
                decodedBytes = c.doFinal(line);
            } catch (Exception e) {
            Log.e("Crypto", "AES decryption error");
            }

            TextView decodedTextView = (TextView)findViewById(R.id.textViewDecoded);
            decodedTextView.setText("[DECODED]:\n" + new String(decodedBytes));
        }
    }

    private void read(){
        Gson gson = new Gson();

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d("Hello", "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        }
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            while ((str = br.readLine()) != null) {
                Code dataItems = gson.fromJson(str, Code.class);
                encoding(dataItems.getKey(), dataItems.getCode());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
