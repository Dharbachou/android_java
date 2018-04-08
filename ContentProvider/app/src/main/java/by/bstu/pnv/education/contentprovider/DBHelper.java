package by.bstu.pnv.education.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "STUDENTSBD_BSTU";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        final String LOG_TAG = "myLogs";
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table students("
                + "IDSTUDENT integer unique primary key AUTOINCREMENT,"
                + "NAME text not null,"
                + "GRADE text not null,"
                + "ADDRESS text not null);"
        );
        // создаем таблицу с полями
        db.execSQL("create table progress("
                + "IDSTUDENT integer not null,"
                + "MARK integer not null,"
                + "constraint MARK_ch check (MARK < 11),"
                + "foreign key(IDSTUDENT) references students(IDSTUDENT)"
                + "on delete cascade on update cascade);"
        );
    }
    public void onConfigure(SQLiteDatabase db)
        {
            db.setForeignKeyConstraintsEnabled(true);
        };

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists progress");
            db.execSQL("drop table if exists students");
            onCreate(db);
    }
}