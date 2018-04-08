package by.bstu.pnv.education.a8_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadScreen();
    }
    private Button.OnClickListener MyRestartButtonOnClickListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            loadScreen();
        }
    };
    private void loadScreen(){
        setContentView(R.layout.activity_main3);
        SetupListView();
        Button MyRestartButton = (Button)findViewById(R.id.myRestartButton);
        MyRestartButton.setOnClickListener(MyRestartButtonOnClickListener);
    }
    private void SetupListView()
    {
        String colors[] = {"Каждый", "Охотник", "Желает", "Знать", "Где", "Сидит", "Фазан"};

        lv = (ListView) findViewById(R.id.myListView);
        CustomArrayAdapter listAdapter = new CustomArrayAdapter(this, colors);
        lv.setAdapter(listAdapter);
    }
}

