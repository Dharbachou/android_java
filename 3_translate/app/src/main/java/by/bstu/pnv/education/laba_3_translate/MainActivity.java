package by.bstu.pnv.education.laba_3_translate;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private final static String pathDirectory = "Dictionary";
    private final static String nameFileWithDictionary = "Dictionary.txt";
    private static File sdPath = null;
    private static File sdFile = null;
    private EditText engWord, rusWord;
    private TextView dictionary = null, error = null;
    private Button save;
    private Map<String, String> map = null;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + pathDirectory);
        sdFile = new File(sdPath, nameFileWithDictionary);
        if(!sdPath.isDirectory()){
            sdPath.mkdirs();
            Toast.makeText(getApplicationContext(), "Папка создана!!!", Toast.LENGTH_SHORT).show();
            if(!sdFile.exists()) {
                try {
                    boolean b = sdFile.createNewFile();
                    if(b){
                        Toast.makeText(getApplicationContext(), "Файл создан!!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            Toast.makeText(getApplicationContext(), "Папка уже существует!!!", Toast.LENGTH_SHORT).show();
            if (!sdFile.exists()) {
                try {
                    boolean b = sdFile.createNewFile();
                    if (b) {
                        Toast.makeText(getApplicationContext(), "Файл создан!!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(getApplicationContext(), "Файл уже создан!!!", Toast.LENGTH_SHORT).show();
            }
        }

        dbHelper = new DBHelper(this);

       /* writeToDB("молоко", "milk");
        writeToDB("яблоко", "apple");
        writeToDB("дерево", "tree");
        writeToDB("дом", "home");
        writeToDB("человек", "person");
        writeToDB("собака", "dog");
        writeToDB("кошка", "cat");
        writeToDB("глаз", "eye");
        writeToDB("хлеб", "bread");
        writeToDB("телефон", "phone");*/



        rusWord = (EditText)findViewById(R.id.rusWord);
        engWord = (EditText)findViewById(R.id.engWord);
        dictionary = (TextView)findViewById(R.id.dicrionary);
        error = (TextView)findViewById(R.id.error);
        save = (Button)findViewById(R.id.save);
        save.setEnabled(false);
    }

    public void onSave(View v){
        String eng = engWord.getText().toString();
        String rus = rusWord.getText().toString();
        if(!eng.isEmpty() && !rus.isEmpty()){
            WorkWithFille.write(sdFile, rus + " : " + eng);
        }else{
            Toast.makeText(getApplicationContext(), "Не все поля заполнены!!!", Toast.LENGTH_SHORT).show();
        }
        save.setEnabled(false);
    }

    public void onRead(View v){
        List<String> list  = WorkWithFille.reader(sdFile);
        dictionary.setText("Dictionary\n");
        for (String s : list){
            dictionary.append(s + "\n");
        }
    }

    public void onTranslate(View v){
        save.setEnabled(false);
        error.setText("");
        String s = rusWord.getText().toString();
        queryDB();
        if(!s.isEmpty()){
            String temp = equlsWord(s);
            if(temp.equals("error")){
                Toast.makeText(getApplicationContext(), "Вы ввели слово которого нет!!!", Toast.LENGTH_SHORT).show();
                return;
            } else if(!temp.equals(s)){
                rusWord.setText(temp);
                error.setText("Я нашел ошибку и исправил её!!!");
            }
            engWord.setText(map.get(temp));
            save.setEnabled(true);
        }else{
            Toast.makeText(getApplicationContext(), "Вы не ввели слово!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private String equlsWord(String s) {
        List<Integer> a = new ArrayList<>();
        for (String te : map.keySet()) {
            a.add(Levinshtein.equls(te, s));
        }
        List<String> str = new ArrayList<String>(map.keySet());
        if (a.contains(0)) {
            return str.get(a.indexOf(0));
        }else if(a.contains(1)) {
            return str.get(a.indexOf(1));
        } else if (a.contains(2)) {
            return str.get(a.indexOf(2));
        }else{
            return "error";
        }
    }


    private void writeToDB(String rus, String eng){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_RUS, rus);
        contentValues.put(DBHelper.KEY_ENG, eng);
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        dbHelper.close();
    }

    private void queryDB(){
        map = new TreeMap<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int eng = cursor.getColumnIndex(DBHelper.KEY_ENG);
            int rus = cursor.getColumnIndex(DBHelper.KEY_RUS);
            do {
                map.put(cursor.getString(rus),cursor.getString(eng));
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }
}
