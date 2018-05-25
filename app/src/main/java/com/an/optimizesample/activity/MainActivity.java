package com.an.optimizesample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.an.optimize.Optimize;
import com.an.optimizesample.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textLocal, textRemote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLocal = findViewById(R.id.text_local);
        textRemote = findViewById(R.id.text_remote);
    }



    public void handleButtonLocalClick(View view) {
        String stringData = getString(R.string.string_data);
        Integer integerData = 15;
        Boolean booleanData = false;
        Number floatData = 1.11;
        Double doubleData = 2.12;
        List<String> listData = Arrays.asList(getResources().getStringArray(R.array.list));

        String data = String.format("Local String: %s\nLocal Integer: %s\nLocal Boolean: %s\nLocal Float: %s\nLocal Double: %s\nLocal List: %s\n", stringData,
                String.valueOf(integerData), String.valueOf(booleanData),
                String.valueOf(floatData), String.valueOf(doubleData), TextUtils.join(", ", listData));

        textLocal.setText(data);
    }



    public void handleButtonRemoteClick(View view) {
        String stringData = Optimize.getInstance().getStringValue("string_data", "Default value is displayed");
        Integer integerData = Optimize.getInstance().getIntegerValue("periodic_timer_value", -1);
        Boolean booleanData = Optimize.getInstance().getBooleanValue("boolean_data", false);
        Number floatData = Optimize.getInstance().getNumberValue("float_data", -1.1);
        Double doubleData = Optimize.getInstance().getDoubleValue("double_data", 0.00);
        List<String> listData = Optimize.getInstance().getList("list_data", Collections.emptyList());

        String data = String.format("String Data: %s\nInteger Data: %s\nBoolean Data: %s\nFloat Data: %s\nDouble Data: %s\nList Data: %s\n", stringData,
                String.valueOf(integerData), String.valueOf(booleanData),
                String.valueOf(floatData), String.valueOf(doubleData), TextUtils.join(", ", listData));

        textRemote.setText(data);
    }
}
