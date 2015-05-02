package com.example.kevin.chevysqlite;

/**
 * Created by Kevin on 4/29/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehicleInfo extends Activity implements android.view.View.OnClickListener{

    Button btnSave, btnDelete, btnClose;
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextType;
    private int _Vehicle_Id=0;
    MediaPlayer deleteSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleinfo);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextModel = (EditText) findViewById(R.id.editTextModel);
        editTextYear = (EditText) findViewById(R.id.editTextYear);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextType = (EditText) findViewById(R.id.editTextType);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Vehicle_Id =0;
        Intent intent = getIntent();
        _Vehicle_Id =intent.getIntExtra("vehicle_Id", 0);
        VehicleTable tab = new VehicleTable(this);
        Vehicle vehicle = new Vehicle();
        vehicle = tab.getVehicleById(_Vehicle_Id);

        editTextYear.setText(String.valueOf(vehicle.year));
        editTextPrice.setText(String.valueOf(vehicle.price));
        editTextModel.setText(vehicle.model);
        editTextType.setText(vehicle.type);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            VehicleTable tab = new VehicleTable(this);
            Vehicle vehicle = new Vehicle();
            vehicle.year= Integer.parseInt(editTextYear.getText().toString());
            vehicle.price= Integer.parseInt(editTextPrice.getText().toString());
            vehicle.type= editTextType.getText().toString();
            vehicle.model= editTextModel.getText().toString();
            vehicle.vehicle_Id= _Vehicle_Id;

            if (_Vehicle_Id==0){
                _Vehicle_Id = tab.insert(vehicle);
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.rev);
                mp.start();
                Toast.makeText(this,"New Vehicle Created",Toast.LENGTH_SHORT).show();
            }else{

                tab.update(vehicle);
                Toast.makeText(this,"Vehicle Record Updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            VehicleTable tab = new VehicleTable(this);
            tab.delete(_Vehicle_Id);
            Toast.makeText(this, "Vehicle Record Deleted", Toast.LENGTH_SHORT);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.cash);
            mp.start();
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }
    }
}
