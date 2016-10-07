package cn.test.zfz.myapplication;
import cn.test.zfz.myapplication.Bluetooth_Controller.*;
import cn.test.zfz.myapplication.List_Controller.*;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends Activity {
    ListView listView;
    Button button_Scanner;
    BlueTooth blueTooth = new BlueTooth(this);
    List_WifiName list_wifiName;

    HashMap<Integer, BluetoothDevice> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        map = blueTooth.getMap_bluetoothdevice();

        list_wifiName = new List_WifiName(listView,this);

        button_Scanner = (Button)findViewById(R.id.button);


        button_Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blueTooth.getBluetoothAdapter().isEnabled()){//turn on the bluetooth if it is Un-active
                    startActivity(blueTooth.On());
                }
                list_wifiName.addData(blueTooth.getListOfBluetoothDevice(),blueTooth.getAddressOfBluetoothDevice());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if(position == 0){
                        blueTooth.connect(blueTooth.get());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


