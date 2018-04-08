package by.bstu.pnv.education.a8_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    ListView listView2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView2 = (ListView) findViewById(R.id.list2);
        String[] valuesq = new String[] {"Brussels",
                "Antwerp",
                "Liège",
                "Ghent",
                "Charleroi",
                "Namur",
                "Hasselt"
        };
        String[] valuesq2 = new String[] {"Canadian Cool",
                "Toronto",
                "Vancouver",
                "Montreal",
                "Niagara Falls",
                "Victoria",
                "Halifax",
                "Quebec City"
        };
        String[] valuesq3 = new String[] {"Hovedstadsområdet[",
                "Aarhus",
                "Odense",
                "Aalborg",
                "Esbjerg",
                "Randers",
                "Kolding",
                "Vejle",
                "Horsens",
                "Roskilde"

        };
        String[] valuesq4 = new String[] {"Aberdeen",
                "Armagh",
                "Bangor",
                "Bath",
                "Hereford",
                "Lancaster",
                "Lichfield",
                "Manchester",
                "Oxford",
                "Preston"
        };
        String[] valuesq5 = new String[] {"Aachen",
                "Amöneburg ",
                "Bad Wünnenberg",
                "Dohna",
                "Ebeleben",
                "Delbrück",
                "Düren",
                "Goch",
                "Lichtenfels ",
                "Lüneburg",
                "Oederan",
                "Rödental"
        };

        Intent intent = getIntent();
        String message = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);
        if(message.equals("Belgium") )
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, valuesq);


            // Assign adapter to ListView
            listView2.setAdapter(adapter);
        }
        else if (message.equals("Canada") )
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, valuesq2);


            // Assign adapter to ListView
            listView2.setAdapter(adapter);
        }
        else if (message.equals("Denmark") )
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, valuesq3);


            // Assign adapter to ListView
            listView2.setAdapter(adapter);
        }
        else if (message.equals("England") )
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, valuesq4);


            // Assign adapter to ListView
            listView2.setAdapter(adapter);
        }
        else if (message.equals("Germany") )
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, valuesq5);


            // Assign adapter to ListView
            listView2.setAdapter(adapter);
        }
    }
}
