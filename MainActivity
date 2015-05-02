package com.example.kevin.chevysqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView vehicle_Id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,VehicleInfo.class);
            intent.putExtra("vehicle_Id",0);
            startActivity(intent);

        }else if(view==findViewById(R.id.btnGetAll)){

            VehicleTable tab = new VehicleTable(this);

            ArrayList<HashMap<String, String>> vehicleList =  tab.getVehicleList();
            if(vehicleList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        vehicle_Id = (TextView) view.findViewById(R.id.vehicle_Id);
                        String vehicleId = vehicle_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),VehicleInfo.class);
                        objIndent.putExtra("vehicle_Id", Integer.parseInt( vehicleId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,vehicleList, R.layout.activity_view_vehicleinfo, new String[] { "id","model"}, new int[] {R.id.vehicle_Id, R.id.vehicle_Model});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No Vehicles!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
