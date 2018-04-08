package by.bstu.pnv.education.contentproviderreader;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";
    static final String AUTHORITY = "by.bstu.pnv.education.StudentsList";
    static final String CONTACT_PATH = "students/progress";
    private static EditText EditText1, EditText2, EditText3, EditText4;
    private static ListView listView;

    final Uri CONTACT_URI = Uri
            .parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lvContact);

        Cursor cursor = getContentResolver().query(CONTACT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String[] temp = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            int i = 0 ;
            int idSt = cursor.getColumnIndex("IDSTUDENT");
            int nameSt = cursor.getColumnIndex("NAME");
            int dethSt = cursor.getColumnIndex("GRADE");
            int addrSt = cursor.getColumnIndex("ADDRESS");
            do {
                Log.d("mLog", "Student : "+ cursor.getInt(idSt)+" "+cursor.getString(nameSt)+" "+cursor.getString(dethSt)+" "+cursor.getString(addrSt));
                temp[i++] = "Student : "+ cursor.getInt(idSt)+" "+cursor.getString(nameSt)+" "+cursor.getString(dethSt)+" "+cursor.getString(addrSt);
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, temp);

        listView.setAdapter(adapter);

        EditText1 = (EditText) findViewById(R.id.editText);
        EditText2 = (EditText) findViewById(R.id.editText2);
        EditText3 = (EditText) findViewById(R.id.editText3);
        EditText4 = (EditText) findViewById(R.id.editText4);
    }

    public void onClickInsert(View v) {
        ContentValues cv = new ContentValues();
        cv.put("NAME", EditText1.getText().toString());
        cv.put("GRADE", EditText2.getText().toString());
        cv.put("ADDRESS", EditText3.getText().toString());
        Uri newUri = getContentResolver().insert(CONTACT_URI, cv);
        Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
    }

    public void onClickRead(View v) {
        Cursor cursor = getContentResolver().query(CONTACT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String[] temp = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            int i = 0 ;
            int idSt = cursor.getColumnIndex("IDSTUDENT");
            int nameSt = cursor.getColumnIndex("NAME");
            int dethSt = cursor.getColumnIndex("GRADE");
            int addrSt = cursor.getColumnIndex("ADDRESS");
            do {
                Log.d("mLog", "Student : "+ cursor.getInt(idSt)+" "+cursor.getString(nameSt)+" "+cursor.getString(dethSt)+" "+cursor.getString(addrSt));
                temp[i++] = "Student : "+ cursor.getInt(idSt)+" "+cursor.getString(nameSt)+" "+cursor.getString(dethSt)+" "+cursor.getString(addrSt);
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, temp);

        listView.setAdapter(adapter);
    }

    public void onClickUpdate(View v) {
        ContentValues cv = new ContentValues();

        cv.put("NAME", EditText1.getText().toString());
        cv.put("GRADE", EditText2.getText().toString());
        cv.put("ADDRESS", EditText3.getText().toString());
        Uri uri = ContentUris.withAppendedId(CONTACT_URI, Integer.parseInt(EditText4.getText().toString()));
        int cnt = getContentResolver().update(uri, cv, null, null);
        Log.d(LOG_TAG, "update, count = " + cnt);
    }

    public void onClickDelete(View v) {
        Uri uri = ContentUris.withAppendedId(CONTACT_URI, Integer.parseInt(EditText4.getText().toString()));
        int cnt = getContentResolver().delete(uri, null, null);
        Log.d(LOG_TAG, "delete, count = " + cnt);
    }

    public void onClickError(View v) {
        Uri uri = Uri.parse("content://ru.startandroid.providers.AdressBook/phones");
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        } catch (Exception ex) {
            Log.d(LOG_TAG, "Error: " + ex.getClass() + ", " + ex.getMessage());
        }

    }
}