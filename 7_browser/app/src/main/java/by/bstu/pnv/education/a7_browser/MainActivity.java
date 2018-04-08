package by.bstu.pnv.education.a7_browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listVIew;
    TextView tv;
    Intent intent;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.modtext);
        listVIew = (ListView)findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        arrayList.add("http://google.com");
        arrayList.add("http://yandex.by");
        arrayList.add("http://youtube.com");
        arrayList.add("http://tut.by");
        arrayList.add("http://learn.javascript.ru");
        intent = new Intent(this, WebActivity.class);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listVIew.setAdapter(adapter);
        listVIew.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String selectedItem = arrayList.get(position);
                intent.putExtra("url", selectedItem);
                startActivity(intent);
            }
        });
    }
}
