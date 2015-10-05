package com.hw.corcow.samplesimplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;
    EditText inputView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText) findViewById(R.id.editText);


        btn = (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputView.getText().toString();
                mAdapter.add(text);
                listView.smoothScrollToPosition(mAdapter.getCount() - 1);
                inputView.setText("");
            }
        });

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });

        /*
        // Single Choice
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        */

        // Multiple Choice
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btn = (Button) findViewById(R.id.btn_choice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    int position = listView.getCheckedItemPosition();
                    String item = (String) listView.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this, "Checked " + item, Toast.LENGTH_SHORT).show();
                }else if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
                    SparseBooleanArray array = listView.getCheckedItemPositions();
                    StringBuilder sb = new StringBuilder();
                    for (int index=0; index < array.size(); index++) {
                        int position = array.keyAt(index);
                        if (array.get(position)) {
                            String text = (String) listView.getItemAtPosition(position);
                            sb.append(text).append(",");
                        }
                    }
                    Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        String[] items = getResources().getStringArray(R.array.list_item);
        for (String s : items) {
            mAdapter.add(s);
        }
    }

}
