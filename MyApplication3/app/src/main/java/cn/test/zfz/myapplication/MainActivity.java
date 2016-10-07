package cn.test.zfz.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {

    static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    private Button mybutton = null, mybutton1 = null;
    ListView listView ;
    SimpleAdapter simpleadapter;
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> address = new ArrayList<String>();
    private ListName list ;
    private Device ddd;
    private String nam;
    private BluetoothDevice sendDevice;
    private Set<BluetoothDevice> sentDevices;

    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton = (Button)findViewById(R.id.button1);
        mybutton.setOnClickListener(new ButtonListener());


        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new Device(listView, MainActivity.this));
    }


    private class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            listView = (ListView) findViewById(R.id.listView);
            list = new ListName(listView, MainActivity.this);
            ddd = new Device(listView, MainActivity.this);


            if (adapter != null) {
                Toast.makeText(MainActivity.this, "okok", Toast.LENGTH_LONG).show();
            }
            if (!adapter.isEnabled()) {
                startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
            }


            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);

            Set<BluetoothDevice> devices = adapter.getBondedDevices();
            if (devices.size() > 0 ) {
                for (Iterator iterator = devices.iterator(); iterator.hasNext(); ) {
                    BluetoothDevice device = (BluetoothDevice) iterator.next();
                    setList(device);
                    System.out.println(name.toString() + device.getName());
                    buildName(device.getName());
                    buildAddress(device.getAddress());
                    Toast.makeText(MainActivity.this, device.getName(), Toast.LENGTH_SHORT).show();
                }
                // 设置广播信息过滤
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
                intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
                intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
                intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
                // 注册广播接收器，接收并处理搜索结果
                registerReceiver(mReceiver, intentFilter);
                // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
                adapter.startDiscovery();
 //               adapter.cancelDiscovery();
                list.write(name, address);
                display();
            }
        }
    }





    /*----------------------分割线---------------------------*/


    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                System.out.println(device.getName());
                buildName(device.getName());
                buildAddress(device.getAddress());
                list.write(name, address);display();
            }
        }
    };

    public void buildName(String name){
        this.name.add(name);
    }
    public void buildAddress(String address){
        this.address.add(address);
    }

    public void display(){
        listView.setAdapter(list.getAdapter());
        list.getData();
    }

    public String getList(){
        return nam;
    }
    public void setList(BluetoothDevice device){
        nam = device.getName();
    }

    public BluetoothDevice getSendDeivce(){
        return sendDevice;
    }
    public void setSendDevice(BluetoothDevice device){
        sendDevice = device;
    }

    public BluetoothAdapter getAdapter(){
        return adapter;
    }

}