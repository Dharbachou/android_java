package by.bstu.pnv.education.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class Progress {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;

    public Progress(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
    }

    public void SetProgress(int idSt, int markPro){
        contentValues.put("IDSTUDENT", idSt);
        contentValues.put("MARK", markPro);
        database.insert("progress", null, contentValues);
    }

    public void GetProgressAll(){
        Cursor cursor = database.query("progress", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idSt = cursor.getColumnIndex("IDSTUDENT");
            int markPro = cursor.getColumnIndex("MARK");
            do {
                Log.d("mLog", "Progress : "+ cursor.getInt(idSt)+" "+cursor.getInt(markPro));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();
    }
}
