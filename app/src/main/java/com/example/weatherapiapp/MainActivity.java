package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // UI components control variables
    Button btnGetCityId, btnGetWeatherByName, btnGetWeatherById;
    EditText txtCityDetailInput;
    ListView lsvCityWeatherReport;
    final String NET_ERROR = "NETWORK ERROR!";
    WeatherDataService wdService = new WeatherDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign the values to the ui controls
        btnGetCityId = findViewById(R.id.btnGetCityId);
        btnGetWeatherById = findViewById(R.id.btnGetWeatherByCityId);
        btnGetWeatherByName = findViewById(R.id.btnGetWeatherByCityName);

        txtCityDetailInput = findViewById(R.id.txtCityDetailInput);
        lsvCityWeatherReport = findViewById(R.id.lst_vw_cities);

        // click listeners for each button
        btnGetCityId.setOnClickListener(view -> wdService.getCityID(txtCityDetailInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, NET_ERROR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "ID: " + response, Toast.LENGTH_SHORT).show();
            }
        }));

        btnGetWeatherById.setOnClickListener(view -> wdService.getWeatherForecastById(txtCityDetailInput.getText().toString(), new WeatherDataService.forecastByIDResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, NET_ERROR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<WeatherReportModel> report) {
                ArrayAdapter<WeatherReportModel> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, report);
                lsvCityWeatherReport.setAdapter(arrayAdapter);
                Toast.makeText(MainActivity.this, report.toString(), Toast.LENGTH_SHORT).show();
            }
        }));

        btnGetWeatherByName.setOnClickListener(view -> wdService.getWeatherForecastByName(txtCityDetailInput.getText().toString(), new WeatherDataService.forecastByNameCallback() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, NET_ERROR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<WeatherReportModel> report) {
                ArrayAdapter<WeatherReportModel> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, report);
                lsvCityWeatherReport.setAdapter(arrayAdapter);
            }
        }));
    }
}