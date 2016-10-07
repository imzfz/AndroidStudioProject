package cn.test.zfz.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * Created by zfz on 16/9/15.
 */
class Device implements AdapterView.OnItemClickListener {
    private ListName list;
    private Context context;
    private ListView listView;
    private MainActivity main = new MainActivity();
    static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private static BluetoothSocket btSocket;
    private BluetoothAdapter adapter = main.getAdapter();
    private Set<BluetoothDevice> devices = adapter.getBondedDevices();

    Device(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listView = (ListView) view.findViewById(R.id.listView);
//        System.out.println(parent.getItemAtPosition(position).toString());
        Iterator iterator = devices.iterator();
        BluetoothDevice device = (BluetoothDevice) iterator.next();
        while (iterator.hasNext()) {
            if (parent.getItemAtPosition(position).toString().contains(device.getName())) {
                break;
            }
            device = (BluetoothDevice) iterator.next();
        }
        adapter.cancelDiscovery();
        connect(device);//连接设备
    }

    private void connect(BluetoothDevice btDev) {
        UUID uuid = UUID.fromString(SPP_UUID);
 /*       try {
            btSocket = btDev.createRfcommSocketToServiceRecord(uuid);
            Log.d("BlueToothTestActivity", "开始连接...");
            System.out.println(btSocket.toString());
            btSocket.connect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        BluetoothSocket socket = null;

        try {
            socket = btDev.createRfcommSocketToServiceRecord(uuid);
        } catch (Exception e) {
            Log.e("", "Error creating socket");
        }

        try {
            socket.connect();
            Log.e("", "Connected");
        } catch (IOException e) {
            Log.e("", e.getMessage());
            try {
                Log.e("", "trying fallback...");

                socket = (BluetoothSocket) btDev.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(btDev, 1);
                socket.connect();

                Log.e("", "Connected");
            } catch (Exception e2) {
                Log.e("", "Couldn't establish Bluetooth connection!");
            }
        }
    }

    public void setListView(ListView listView, Context context){
        this.listView = listView;
        this.context = context;
        System.out.println("11111111");
    }
}