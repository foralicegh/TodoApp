package example.s.orijinaru3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by s on 2016/11/25.
 */
public class SubjectPickActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String[] items = {"古典", "現代文", "代数", "幾何", "英語", "物理", "生物", "化学", "地学", "地理", "歴史", "公民"};
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_pick);
        listView = (ListView)findViewById(R.id.listView);
//        editText =(EditText)findViewById(R.id.editText);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String lSubject = arrayAdapter.getItem(i);

                Intent intent= new Intent(SubjectPickActivity.this,MainActivity.class);
                intent.putExtra("Subject",lSubject);
                startActivity(intent);
            }

        });


    }

    public void add(View v){
        String fSubject= String.valueOf(editText.getText());
        Intent intent = new Intent(SubjectPickActivity.this,MainActivity.class);
        intent.putExtra("Subject",fSubject);
        startActivity(intent);
    }




}



