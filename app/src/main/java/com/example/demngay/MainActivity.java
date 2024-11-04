package com.example.demngay;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText date1;
    private EditText date2;
    private Button btnCalculate;
    private TextView tvResult;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(date1);
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(date2);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Date d1 = sdf.parse(date1.getText().toString());
                    Date d2 = sdf.parse(date2.getText().toString());

                    if (d2 != null && d1 != null) {
                        if (d2.before(d1)) {
                            Toast.makeText(MainActivity.this, "Ngày thứ 2 phải lớn hơn ngày thứ 1", Toast.LENGTH_SHORT).show();
                        } else {
                            long diffInMillies = Math.abs(d2.getTime() - d1.getTime());
                            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                            tvResult.setText("Số ngày cách nhau là: " + diffInDays);
                        }
                    }
                } catch (ParseException e) {
                    tvResult.setText("Vui lòng nhập đúng định dạng ngày (dd/MM/yyyy)." );
                }
            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editText.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
