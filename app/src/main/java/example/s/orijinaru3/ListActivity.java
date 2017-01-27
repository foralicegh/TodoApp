package example.s.orijinaru3;

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

public class ListActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapter customAdapter;
    List<Homework> readhwList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (readhwList.size() != 0) {
                    try {
                        FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(readhwList);
                        oos.close();
                        fos.close();
                    } catch (Exception e) {

                    }
                }


                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_listactivity);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                delete();
                return false;
            }
        });


        readFile();
        customAdapter = new CustomAdapter(this, R.layout.subject_list_new, readhwList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

    }



    public boolean readFile() {
        try {
            readhwList = new ArrayList<Homework>();
            FileInputStream fis = openFileInput("mHomework");
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

    public boolean delete(){
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        for (int i=0;i<checked.size();i++){
            if(checked.valueAt(i)){
                Homework removeitem=(Homework)customAdapter.getItem(i);
                customAdapter.remove(removeitem);
            }
        }

        return true;
    }
}

