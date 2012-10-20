package com.example.sqliteconnect;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle; 
import android.view.Menu;  
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainAct_1 extends Activity {
    
    private MainActivity_1 db;
    private Cursor myCursor;
    private ListView myListView;
    private EditText myEditText;
    private int _id;
    protected final static int MENU_ADD=Menu.FIRST;
    protected final static int MENU_EDIT=Menu.FIRST+1;
    protected final static int MENU_DELETE=Menu.FIRST+2;
    
       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
           super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_ADD, 0, R.string.ADD);
        menu.add(Menu.NONE, MENU_EDIT, 0,R.string.EDIT);
        menu.add(Menu.NONE, MENU_DELETE, 0,R.string.DELETE);
         return true;
    }
    
       @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        
        super.onOptionsItemSelected(item); 
        switch (item.getItemId()) {
        case MENU_ADD:
            operation("add");
            break;
        case MENU_EDIT:
            operation("edit");
            break;
        case MENU_DELETE:
            operation("delete");
            break;
        default:
            break;
        }
        return true;
    }
       
       
       
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEditText=(EditText)findViewById(R.id.EditText1);
        myListView=(ListView)findViewById(R.id.ListView1);
        try {
			db=new MainActivity_1(MainAct_1.this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        myCursor=db.select();
        SimpleCursorAdapter adpater=new SimpleCursorAdapter(this
                , R.layout.test, myCursor,
                new String[]{MainActivity_1.FIELD_TITLE},
                new int[]{R.id.topTextView});
        myListView.setAdapter(adpater);
        
        myListView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                myCursor.moveToPosition(arg2);
                _id=myCursor.getInt(0);
                myEditText.setText(myCursor.getString(1));
            }
        });
        
        
        myListView.setOnItemSelectedListener(new OnItemSelectedListener() {

         
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                SQLiteCursor sc=(SQLiteCursor)arg0.getSelectedItem();
                _id=sc.getInt(0);
                myEditText.setText(sc.getString(1));
            }

          
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    private void operation(String cmd)
    {
        if(myEditText.getText().toString().equals(""))
        return;
        if(cmd=="add")
            db.insert( myEditText.getText().toString());
        if(cmd=="edit")
            db.update(_id,  myEditText.getText().toString());
        if(cmd=="delete")
            db.delete(_id);
        myCursor.requery();
        myListView.invalidateViews();
        myEditText.setText("");
        _id=0;
        
    } 
}