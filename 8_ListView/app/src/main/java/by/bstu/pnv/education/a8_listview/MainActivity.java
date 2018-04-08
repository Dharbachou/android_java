package by.bstu.pnv.education.a8_listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView listView ;
    private ImageView wv1;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.Btn);

        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] valuesq = new String[] {"Belgium",
                "Canada",
                "Denmark",
                "England",
                "Germany"
        };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, valuesq);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        //listView.setOnItemClickListener(new OnItemClickListener() {

        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override



            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                // ListView Clicked item index
                int itemPosition     = arg2; //position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(arg2/*position*/);
                switch(itemPosition)
                {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra(EXTRA_MESSAGE, "Belgium");
                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                        intent1.putExtra(EXTRA_MESSAGE, "Canada");
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                        intent2.putExtra(EXTRA_MESSAGE, "Denmark");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, Main2Activity.class);
                        intent3.putExtra(EXTRA_MESSAGE, "England");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, Main2Activity.class);
                        intent4.putExtra(EXTRA_MESSAGE, "Germany");
                        startActivity(intent4);
                        break;

                }

                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(MainActivity.this, Main3Activity.class);

                startActivity(intent6);

            }
        });
    }

}

