package com.example.SQLprac2;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Comment comment = null;

    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        final EditText et = (EditText) findViewById(R.id.editText);
        final ListView lv = (ListView) findViewById(R.id.listView);
        Button a = (Button) findViewById(R.id.button);



        database = new Database(this);
        database.open();
        final List<Comment> values = database.getAllComments();

        final List<String> converter = new ArrayList<String>();


        for (int i = 0; i < values.size(); i++){
            converter.add(values.get(i).toString());

        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, converter);
        lv.setAdapter(adapter);




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.createComment(et.getText().toString());
                converter.add(et.getText().toString());

                adapter.notifyDataSetChanged();
            }
        });


    }


}
