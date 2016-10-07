package cn.test.zfz.myapplication;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zfz on 16/9/15.
 */
public class ListName {
    private ListView listView;
    private SimpleAdapter adapter;
    private Context context;
    private String[] str= new String[] {"textView1","textView2"};
    private int[] id = new int[] {R.id.textView1,R.id.textView2};


    HashMap<String, String> map;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();


    public ListName(){
        adapter = new SimpleAdapter(context,getData(),R.layout.item,str,id);
    }

    public ListName(ListView listView, Context context){
        this.listView = listView;
        this.context = context;
        adapter = new SimpleAdapter(context,getData(),R.layout.item,str,id);
    }

    public ListAdapter getAdapter(){
        return adapter;
    }

    public void setListView(ListView listView){
        this.listView = listView;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public ArrayList<HashMap<String, String>> getData(){
        return list;
    }



    public void write (ArrayList<String> name, ArrayList<String> address){
        for(int i = 0; i < name.size(); i++) {
            map = new HashMap<String, String>();
            map.put("textView1", name.get(i));
            map.put("textView2", address.get(i));
            list.add(map);
        }

 //     System.out.println(list.toString());
    }
}
