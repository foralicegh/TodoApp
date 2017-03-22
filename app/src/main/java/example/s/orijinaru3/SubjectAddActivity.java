package example.s.orijinaru3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubjectAddActivity extends AppCompatActivity {

    ListView listview;
    EditText editText;
    ArrayAdapter adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.clear_white_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        readFile();
        firstStarting();
        listview = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);
        for (int i = 0; i < list.size(); i++) {
            adapter.add(String.valueOf(list.get(i)));
        }
        listview.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SubjectAddActivity.this);
                alertDialog.setMessage("選択した項目を削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                String removeSubject = (String) adapter.getItem(i);
                                adapter.remove(removeSubject);
                                list.remove(removeSubject);
                            }
                        })
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                alertDialog.create().show();
            }
        });

    }

    public void add(View v) {
        String newSubject = String.valueOf(editText.getText());
        adapter.add(newSubject);
        list.add(newSubject);
        editText.setText("");
    }

    public void done(View v) {
        try {
            FileOutputStream fos = openFileOutput("savesubject", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (Exception e) {

        }
        Intent intent = new Intent(SubjectAddActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean readFile() {
        try {
            FileInputStream fis = openFileInput("savesubject");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<String>) ois.readObject();
            ois.close();
            fis.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean firstStarting() {
        if (list.size() == 0) {
            String[] subject = {"古典", "現代文", "数I", "数Ⅱ", "英語", "英語表現",
                    "コミュ英語", "物理", "化学", "生物", "地学", "地理", "世界史", "日本史"};
            for (int i = 0; i < 3; i++) {
                list.add(subject[i]);
            }
//            list.addAll(Arrays.asList(subject));
        }
        return true;

    }

}

