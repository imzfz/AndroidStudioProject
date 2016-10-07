package cn.test.zfz.myapplication.Bluetooth_Controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2016/9/10.
 */
public class BlueTooth{
    private  BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private HashMap<Integer, BluetoothDevice> map_bluetoothdevice;

    private Context context;

    public BlueTooth(Context newContext){
        BA = BluetoothAdapter.getDefaultAdapter();

        pairedDevices = BA.getBondedDevices();

        this.context = newContext;

        /*setMap_bluetoothdevice();*/
    }

    public BluetoothAdapter getBluetoothAdapter(){
        return BA;
    }

    public Set<BluetoothDevice> getPairedDevices(){
        return pairedDevices;
    }

    /*Turn on the bluetooth device*/
    public Intent On(){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            Toast.makeText(context, "BlueTooth TurnOn", Toast.LENGTH_SHORT).show();//提示蓝牙打开
            return turnOn;
    }

    /*Get the address of these device, and put them into a list*/
    public ArrayList<String> getAddressOfBluetoothDevice(){
        ArrayList<String> list1 = new ArrayList<String>();

        for(BluetoothDevice btd : pairedDevices){
           Toast.makeText(context,btd.getAddress(),Toast.LENGTH_SHORT).show();
            list1.add(btd.getAddress());
        }

        return list1;
    }

    /*Get the names of BlueTooth Device, and put them into a List*/
    public ArrayList<String> getListOfBluetoothDevice(){
        ArrayList<String> list = new ArrayList<String>();

        for(BluetoothDevice btd : pairedDevices){
           /* Toast.makeText(context,btd.getName(),Toast.LENGTH_SHORT).show();*/
            list.add(btd.getName());
        }

        return list;
    }

    public void setMap_bluetoothdevice(){

        int i = 0;
        for(BluetoothDevice bluetoothDevice : pairedDevices){
            map_bluetoothdevice.put(i,bluetoothDevice);

            i++;
        }
    }

    public HashMap<Integer, BluetoothDevice> getMap_bluetoothdevice(){
        return map_bluetoothdevice;
    }

    public void connect(BluetoothDevice device) throws IOException{

        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);

        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
        socket.connect();

        //Toast.makeText(context,"has Conencted",Toast.LENGTH_SHORT).show();
    }

    public BluetoothDevice get(){
        BluetoothDevice bl = null;

        for(BluetoothDevice bld : pairedDevices){
            bl = bld;
            break;
        }

        return bl;
    }

}



