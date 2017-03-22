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
import java.util.List;

public class HelperAddActivity extends AppCompatActivity {
    ListView listview;
    EditText editText;
    ArrayAdapter adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_add);

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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HelperAddActivity.this);
                alertDialog.setMessage("選択した項目を削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                String removeHelper = (String) adapter.getItem(i);
                                adapter.remove(removeHelper);
                                list.remove(removeHelper);
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
        String newHelper = String.valueOf(editText.getText());
        adapter.add(newHelper);
        list.add(newHelper);
        editText.setText("");
    }

    public void done(View v) {
        try {
            FileOutputStream fos = openFileOutput("savehelper", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (Exception e) {

        }
        Intent intent = new Intent(HelperAddActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean readFile() {
        try {
            FileInputStream fis = openFileInput("savehelper");
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
            String[] helper = {"P", "No", "～P", "～No", "・"};
            for (int i = 0; i < 5; i++) {
                list.add(helper[i]);
            }
        }
        return true;

    }

}
