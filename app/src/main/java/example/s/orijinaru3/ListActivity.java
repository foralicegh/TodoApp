package example.s.orijinaru3;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import java.util.Collections;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapter customAdapter;
    List<Homework> readhwList;
    List<Homework> saveList;
    Homework addHomework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_listactivity);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        try {
                            FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(readhwList);
                            oos.close();
                            fos.close();
                        } catch (Exception e) {

                        }
                        Intent intent = new Intent(ListActivity.this, SubjectAddActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_help:
                        try {
                            FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(readhwList);
                            oos.close();
                            fos.close();
                        } catch (Exception e) {

                        }
                        Intent intent2 = new Intent(ListActivity.this, HelperAddActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                }

                return false;
            }
        });


//        toolbar.inflateMenu(R.menu.menu_listactivity);

//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                delete();
//                return false;
//            }
//        });
        readhwList = new ArrayList<Homework>();
        readFile();
//        addHomework();


        customAdapter = new CustomAdapter(this, R.layout.subject_list_new, readhwList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListActivity.this);
                alertDialog.setMessage("選択した項目を削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                Homework delete = (Homework) customAdapter.getItem(i);
                                customAdapter.remove(delete);
                                readhwList.remove(delete);
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

    @Override
    public void onUserLeaveHint() {
        try {
            FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(readhwList);
            oos.close();
            fos.close();
        } catch (Exception e) {

        }
    }

    public boolean readFile() {
        try {
            FileInputStream fis = openFileInput("lHomework");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readhwList = (ArrayList<Homework>) ois.readObject();
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

//    public boolean addHomework() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            String addsubject = intent.getStringExtra("addSubject");
//            String addcontent = intent.getStringExtra("addContent");
//            long adddiffday = intent.getLongExtra("addDiffday", 0);
//            addHomework = new Homework(addsubject, addcontent, adddiffday);
//            readhwList.add(addHomework);
//            Collections.sort(readhwList, new HomewWorkComparator());
//        }
//        return true;
//    }


    public boolean save() {

        try {
//                CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
//                    for (int i = 0, length = adapter.getCount(); i < length; i++) {
//                        saveList.add(adapter.getItem(i));
//                }
            FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(readhwList);
            oos.close();
            fos.close();
        } catch (Exception e) {

        }


        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        return true;
    }

}








