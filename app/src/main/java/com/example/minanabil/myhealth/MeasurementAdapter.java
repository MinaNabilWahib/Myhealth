package com.example.minanabil.myhealth;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MeasurementAdapter extends ArrayAdapter<Measurement> {
    Context mContext;

    LayoutInflater inflater;

    List<Measurement> MeasurementS;

    public MeasurementAdapter(Context context, int resource, List<Measurement> Measurements) {
        super(context, resource);
        this.mContext = context;
        this.MeasurementS = Measurements;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    static class ViewHolder {
        TextView userName;
        TextView userMeasure;
        EditText inputMeasure;
        int position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder  holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_measurement, null);
            holder = new ViewHolder();
//            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_measurement, parent, false);
//          LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = vi.inflate(R.layout.item_measurement, null);
            holder.inputMeasure = (EditText) convertView.findViewById(R.id.inputMeasurementEditText);
            holder.userName = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.userMeasure = (TextView) convertView.findViewById(R.id.measurementTextView);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

//        TextView measurementTextView = (TextView) convertView.findViewById(R.id.measurementTextView);
//        EditText inputMeasurementText= (EditText) convertView.findViewById(R.id.inputMeasurementEditText);
//        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        final Measurement measurement = getItem(position);

        holder.userMeasure.setText(measurement.getName());;
        holder.userName.setText(measurement.getUserName());
        holder.inputMeasure.setText(measurement.getText());
//        holder.inputMeasure.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    measurement.setText(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        holder.inputMeasure.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    measurement.setText(Caption.getText().toString());


                }

            }
        });
//        measurementTextView.setText(measurement.getName());
//        inputMeasurementText.setText(measurement.getText().toString());
//        nameTextView.setText(measurement.getUserName());

        return convertView;
    }



}
