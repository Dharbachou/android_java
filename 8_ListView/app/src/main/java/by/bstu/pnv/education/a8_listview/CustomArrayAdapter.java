package by.bstu.pnv.education.a8_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Dima on 30.03.2018.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] stringValues;

    public CustomArrayAdapter (Context context, String[] stringValues)
    {
        super(context, R.layout.list_item, stringValues);
        this.context = context;
        this.stringValues = stringValues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.colors);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llColors);
        textView.setText(stringValues[position]);
        String s = stringValues[position];

        int i = 1 + (int)(Math.random() * 7);
        switch (i) {
            case 1: linearLayout.setBackgroundResource(R.color.red);            break;
            case 2: linearLayout.setBackgroundResource(R.color.orange);              break;
            case 3: linearLayout.setBackgroundResource(R.color.yellow);             break;
            case 4: linearLayout.setBackgroundResource(R.color.green);            break;
            case 5: linearLayout.setBackgroundResource(R.color.light_blue);           break;
            case 6: linearLayout.setBackgroundResource(R.color.blue);          break;
            case 7: linearLayout.setBackgroundResource(R.color.purple);            break;
            case 8: linearLayout.setBackgroundResource(R.color.white);   break;
        }


        return view;
    }
}