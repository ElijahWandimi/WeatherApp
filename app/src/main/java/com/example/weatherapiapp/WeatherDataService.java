package com.example.weatherapiapp;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    public static final String QUERY_URL = "https://metaweather.com/api/location/search/?query=";
    public static final String QUERY_WEATHER_BY_ID = "https://metaweather.com/api/location/";
    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    // data weather service interfaces
    public interface VolleyResponseListener {
        void  onError(String message);

        void onResponse(String response);
    }

    public interface forecastByIDResponseListener {
        void  onError(String message);

        void onResponse(List<WeatherReportModel> weatherReport);
    }

    public interface forecastByNameCallback {
        void onError(String message);

        void onResponse(List<WeatherReportModel> reportModels);
    }

    public void getCityID(String cityName, final VolleyResponseListener responseListener) {
        String url = QUERY_URL + cityName;
        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    cityID = "";
                    try {
                        JSONObject cityInfo = response.getJSONObject(0);
                        cityID = cityInfo.getString("woeid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    responseListener.onResponse(cityID);
                },
                error -> responseListener.onError("Network Error")
        );
        SingletonRequest.getInstance(context).addToRequestQueue(jRequest);
    }

    public void   getWeatherForecastById(String cityId, final forecastByIDResponseListener fidListener) {
        String url = QUERY_WEATHER_BY_ID + cityId;
        final List<WeatherReportModel> weatherReportModels = new ArrayList<>();
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray consolidatedWeatherList = response.getJSONArray("consolidated_weather");
                        for (int i=0; i<consolidatedWeatherList.length(); i++) {
                            WeatherReportModel aDay = new WeatherReportModel();
                            JSONObject firstDayFromApi = (JSONObject) consolidatedWeatherList.get(i);

                            aDay.setId(firstDayFromApi.getInt("id"));
                            aDay.setWeatherStateName(firstDayFromApi.getString("weather_state_name"));
                            aDay.setWeatherStateAbbr(firstDayFromApi.getString("weather_state_abbr"));
                            aDay.setWindDirectionCompass(firstDayFromApi.getString("wind_direction_compass"));
                            aDay.setApplicableDate(firstDayFromApi.getString("applicable_date"));
                            aDay.setMinTemp((float) firstDayFromApi.getDouble("min_temp"));
                            aDay.setMaxTemp((float) firstDayFromApi.getDouble("max_temp"));
                            aDay.setTheTemp((float) firstDayFromApi.getDouble("the_temp"));
                            aDay.setWindSpeed((float) firstDayFromApi.getDouble("wind_speed"));
                            aDay.setWindDirection((float) firstDayFromApi.getDouble("wind_direction"));
                            aDay.setAirPressure(firstDayFromApi.getInt("air_pressure"));
                            aDay.setHumidity(firstDayFromApi.getInt("humidity"));
                            aDay.setVisibility((float) firstDayFromApi.getDouble("visibility"));
                            aDay.setPredictability(firstDayFromApi.getInt("predictability"));
                            weatherReportModels.add(aDay);
                        }
                        fidListener.onResponse(weatherReportModels);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> fidListener.onError("Network Error!")
        );
        SingletonRequest.getInstance(context).addToRequestQueue(objRequest);
    }

    public void getWeatherForecastByName(String cityName, final forecastByNameCallback fnCallback) {
        getCityID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                fnCallback.onError("Network Error!");
            }

            @Override
            public void onResponse(String response) {
                getWeatherForecastById(cityID, new forecastByIDResponseListener() {
                    @Override
                    public void onError(String message) {
                        fnCallback.onError("Network Error!");
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReport) {
                        fnCallback.onResponse(weatherReport);
                    }
                });
            }
        });
    }
}
