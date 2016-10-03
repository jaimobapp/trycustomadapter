package com.example.jai.trycustomadapter;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> objTask=new ArrayList<Task>();
    /*ArrayAdapter<Task> taskAdapter=new CustomTaskAdapter();

    private ListView taskList;

    private EditText taskTitle;
    private EditText taskDesc;

    private ArrayList<String> tittleList;
    private ArrayList<String> descList;
    private ArrayList<String> dispList;
    private FileOutputStream outFile;
    private ArrayAdapter<String> dispListAdapter;*/
    ListView lstVwTask;
    ArrayAdapter<Task> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //taskTitle=(EditText)findViewById(R.id.iptitle);
        //taskDesc=(EditText)findViewById(R.id.ipdesc);

        /*taskList =(ListView)findViewById(R.id.tasklist);
        dispList=new ArrayList<>();
        dispListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,dispList);
        taskList.setAdapter(dispListAdapter);*/

        //readtasklist();

        taskAdapter=new CustomTaskAdapter();
        lstVwTask=(ListView)findViewById(R.id.lstvwidtask);
        lstVwTask.setAdapter(taskAdapter);

        populateTasks();
        setupListviewListener();


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("title", value);
        outState.putString("desc", value);
    }

    private void populateListView() {

        taskAdapter=new CustomTaskAdapter();
        lstVwTask=(ListView)findViewById(R.id.lstvwidtask);
        lstVwTask.setAdapter(taskAdapter);


    }
    public void writebtn(View view) {
        EditText taskTitle = (EditText) findViewById(R.id.iptitle);
        String titletobeadded = taskTitle.getText().toString();


        EditText taskDesc = (EditText) findViewById(R.id.ipdesc);
        String desctobeadded = taskDesc.getText().toString();
        if(titletobeadded!="" && desctobeadded!="")
        objTask.add(new Task(titletobeadded,desctobeadded));
        //objTask.add(new Task("t2","td2"));
        // String disp = titletobeadded + "\n" + desctobeadded;
       // dispList.add(disp);
        taskTitle.setText("");
        taskDesc.setText("");
        populateListView();
    }

    private void populateTasks() {
        objTask.add(new Task("t1","td1"));
        objTask.add(new Task("t2","td2"));

    }

    private void setupListviewListener(){
        ListView lstVwTask=(ListView)findViewById(R.id.lstvwidtask);
        lstVwTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                //dispList.remove(i);
                Task clickedTask=objTask.get(i);
                String msg="Item deleted at position"+i;
                objTask.remove(i);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
                // dispListAdapter.notifyDataSetChanged();
                return false;
            }
        });

       // ListView lstvwtsk=(ListView)findViewById(R.id.lstvwidtask);
       /* final ArrayAdapter<Task> taskAdapterLong=new CustomTaskAdapter();
        ListView lstVwTask=(ListView)findViewById(R.id.lstvwidtask);
        lstVwTask.setAdapter(taskAdapterLong);
      */

    }

    public void checkButtonClick(View view) {

        CheckBox cb = (CheckBox)findViewById(R.id.item_checkBtnSelect);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"inside check click",Toast.LENGTH_SHORT).show();

                SparseBooleanArray checkedItemPositions = lstVwTask.getCheckedItemPositions();
                int itemCount = lstVwTask.getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        Toast.makeText(MainActivity.this,i,Toast.LENGTH_SHORT).show();

                        taskAdapter.remove(objTask.get(i));

                        checkedItemPositions.clear();
                        taskAdapter.notifyDataSetChanged();
                    }
                }
                checkedItemPositions.clear();
                taskAdapter.notifyDataSetChanged();


            }
                /*for(int i=0;i<countryList.size();i++){
                    Country country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getName());
                    }

                Task clickedTask=objTask.get(i);
                String msg="Item deleted at position"+i;
                objTask.remove(i);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();

            }*/
        });
           // Button myButton = (Button) findViewById(R.id.item_checkBtnSelect);
            /*cb.setOnClickListener(new onClickListener()) {

                @Override
                public void onClick(View v) {

                    StringBuffer responseText = new StringBuffer();
                    responseText.append("The following were selected...\n");

                    ArrayList<Task> taskList = ;
                    for(int i=0;i<countryList.size();i++){
                        Country country = countryList.get(i);
                        if(country.isSelected()){
                            responseText.append("\n" + country.getName());
                        }
                    }

                    Toast.makeText(getApplicationContext(),
                            responseText, Toast.LENGTH_LONG).show();

                }
            });*/



    }


    private class CustomTaskAdapter extends ArrayAdapter<Task> {

        public CustomTaskAdapter() {
            super(MainActivity.this,R.layout.task_view,objTask);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View taskview=convertView;
            if(taskview==null)
                taskview=getLayoutInflater().inflate(R.layout.task_view,parent,false);
            Task currentTask=objTask.get(position);

            TextView itmdescview=(TextView)taskview.findViewById(R.id.item_txtdesc);
            itmdescview.setText(currentTask.getTaskDesc());

            TextView itmtitleview=(TextView)taskview.findViewById(R.id.item_txttitle);
            itmtitleview.setText(currentTask.getTaskTitle());

            return taskview;
           // return super.getView(position, convertView, parent);
        }
    }
}
