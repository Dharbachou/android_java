package by.bstu.pnv.education.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class Student {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;

    public Student(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
    }

    public void SetStudent(String nameSt, String dethSt, String addrSt){
        contentValues.put("NAME", nameSt);
        contentValues.put("GRADE", dethSt);
        contentValues.put("ADDRESS", addrSt);
        database.insert("students", null, contentValues);
    }

    public void GetStudentAll(){
        Cursor cursor = database.query("students", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idSt = cursor.getColumnIndex("IDSTUDENT");
            int nameSt = cursor.getColumnIndex("NAME");
            int dethSt = cursor.getColumnIndex("GRADE");
            int addrSt = cursor.getColumnIndex("ADDRESS");
            do {
                Log.d("mLog", "Student : "+ cursor.getInt(idSt)+" "+cursor.getString(nameSt)+" "+cursor.getString(dethSt)+" "+cursor.getString(addrSt));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();
    }
}
