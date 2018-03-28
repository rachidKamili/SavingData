package me.kamili.rachid.savingdata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etSharedPref;
    private TextView tvSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        etSharedPref = findViewById(R.id.etSharePref);
        tvSharedPref = findViewById(R.id.tvSharedPref);
    }

    public void handlingSharedPref(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE);

        switch (view.getId()) {
            case R.id.btnSavePref:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("data",etSharedPref.getText().toString());
                if (editor.commit() )
                    Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Data Not Saved",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRestorePref:
                tvSharedPref.setText(sharedPreferences.getString("data","defaultString"));
                break;
        }
    }
}
