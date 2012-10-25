package com.example.sqliteconnect;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.org.utils.R;


public class MainAct_1 extends Activity {
    
	private TextView text = null;
	public final String DEBUG_TAG = "MainAct_1";
	private static String SDCardRoot = Environment
            .getExternalStorageDirectory().getAbsolutePath() + File.separator;

//    private MainActivity_1 db;
//    private Cursor myCursor;
//    private ListView myListView;
//    private EditText myEditText;
//    private int _id;
//    protected final static int MENU_ADD=Menu.FIRST;
//    protected final static int MENU_EDIT=Menu.FIRST+1;
//    protected final static int MENU_DELETE=Menu.FIRST+2;
//    
//       @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // TODO Auto-generated method stub
//           super.onCreateOptionsMenu(menu);
//        menu.add(Menu.NONE, MENU_ADD, 0, R.string.ADD);
//        menu.add(Menu.NONE, MENU_EDIT, 0,R.string.EDIT);
//        menu.add(Menu.NONE, MENU_DELETE, 0,R.string.DELETE);
//         return true;
//    }
//    
//       @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // TODO Auto-generated method stub
//        
//        super.onOptionsItemSelected(item); 
//        switch (item.getItemId()) {
//        case MENU_ADD:
//            operation("add");
//            break;
//        case MENU_EDIT:
//            operation("edit");
//            break;
//        case MENU_DELETE:
//            operation("delete");
//            break;
//        default:
//            break;
//        }
//        return true;
//    }
       
       
       
    /** Called when the activity is first created. */
    @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()        
         .detectDiskReads()        
         .detectDiskWrites()        
         .detectNetwork()   // or .detectAll() for all detectable problems        
         .penaltyLog()        
         .build());        
         StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()        
         .detectLeakedSqlLiteObjects()     
         .penaltyLog()        
         .penaltyDeath()        
         .build());  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView1);
//        try {
//			Toast.makeText(getApplicationContext(), downloadUrl("http://dampce032.iteye.com/blog/975642"), Toast.LENGTH_LONG).show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        down();
//        saveImageToDisk();
//        myEditText=(EditText)findViewById(R.id.EditText1);
//        myListView=(ListView)findViewById(R.id.ListView1);
//        try {
//			db=new MainActivity_1(MainAct_1.this);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        myCursor=db.select();
//        SimpleCursorAdapter adpater=new SimpleCursorAdapter(this
//                , R.layout.test, myCursor,
//                new String[]{MainActivity_1.FIELD_TITLE},
//                new int[]{R.id.topTextView});
//        myListView.setAdapter(adpater);
//        
//        myListView.setOnItemClickListener(new OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                    long arg3) {
//                // TODO Auto-generated method stub
//                myCursor.moveToPosition(arg2);
//                _id=myCursor.getInt(0);
//                myEditText.setText(myCursor.getString(1));
//            }
//        });
//        
//        
//        myListView.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//         
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                    int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                SQLiteCursor sc=(SQLiteCursor)arg0.getSelectedItem();
//                _id=sc.getInt(0);
//                myEditText.setText(sc.getString(1));
//            }
//
//          
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//                
//            }
//        });
//    }
//    private void operation(String cmd)
//    {
//        if(myEditText.getText().toString().equals(""))
//        return;
//        if(cmd=="add")
//            db.insert( myEditText.getText().toString());
//        if(cmd=="edit")
//            db.update(_id,  myEditText.getText().toString());
//        if(cmd=="delete")
//            db.delete(_id);
//        myCursor.requery();
//        myListView.invalidateViews();
//        myEditText.setText("");
//        _id=0;
        
    } 
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
            
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;
            
        // Makes sure that the InputStream is closed after the app is
        // finished using it.
        } finally {
            if (is != null) {
                is.close();
            } 
        }
    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");        
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
//    public static InputStream getInputStream()
//	{
//		InputStream inputStream = null;
//		URL url = null;
//		HttpsURLConnection httpUrlConnection = null;
//		try {
////			url = new URL("http://zhangmenshiting.baidu.com/data2/music/23159922/23159920144000.mp3?xcode=c763e6f54954bfc5f08f3bb5c5f0aafe");
//			url = new URL("http://www.baidu.com/s?ie=utf-8&bs=android+classCastException%3Blib&f=8&rsv_bp=1&rsv_spt=3&wd=android+classCastException%3Blibcore.net.http.HttpURLConnectionIm&rsv_sug3=39&rsv_sug1=28&rsv_sug4=37971&inputT=41988");
//			try {
//				if(url != null)
//				{
//					httpUrlConnection = (HttpsURLConnection)url.openConnection();
//					httpUrlConnection.setConnectTimeout(3000);
//					httpUrlConnection.setRequestMethod("Get");
//					httpUrlConnection.setDoInput(true);
//					int response = httpUrlConnection.getResponseCode();
//					if(response == 200)
//					{
//						inputStream = httpUrlConnection.getInputStream();
//					}
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return inputStream;
//	}
//	public static void saveImageToDisk()
//	{
//		
//		InputStream inputStream = getInputStream();
//		byte[] data = new byte[1024];
//		int len = 0;
//		FileOutputStream fileOutputStream = null;
//		try {
//			fileOutputStream = new FileOutputStream(SDCardRoot );
//			while((len = inputStream.read(data))!=-1)
//			{
//				fileOutputStream.write(data, 0, len);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			if(inputStream!=null){
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(fileOutputStream!=null){
//					try {
//						
//						fileOutputStream.close();
//						
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	}
    public void down()
    {
    	Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().getName()+
				"\\6.mp3", Toast.LENGTH_LONG).show();
    	URL url=null;
		try {
			url = new URL("http://117.40.196.70:811/file/5a/fa/5afad554b4df46ca862edf3041cf9eb1/E%E6%A8%A1%E5%9D%97%E7%A0%B4%E8%A7%A3%E8%BD%AC%E6%8D%A2%E5%99%A8-%E5%90%BE%E7%88%B1%E7%A0%B4%E8%A7%A3%E5%86%B3v1.0%E7%89%88.rar?key=fe9a8f806c2084a60ee05a9d41f0c7f6&uid=0&id=1387355&t=1350984613&type=html");
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			BufferedInputStream bis = new BufferedInputStream(urlConn
					.getInputStream());

			FileOutputStream fos = new FileOutputStream(
					Environment.getExternalStorageDirectory().getName()+
					"\\6.mp3");
			
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[3 * 1024];

			int result = bis.read(buf);

			while (result != -1) {
				bos.write(buf, 0, result);
				result = bis.read(buf);
			}
			bos.flush();
			bis.close();
			fos.close();
			bos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}	

