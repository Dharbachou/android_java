package by.bstu.pnv.education.contentprovider;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student(this);
       // student.SetStudent("Dima", "9", "Hello");
        Progress progress = new Progress(this);
       // progress.SetProgress(1, 9);

        student.GetStudentAll();
        progress.GetProgressAll();

    }
}
