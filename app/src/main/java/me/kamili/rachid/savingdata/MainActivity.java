package me.kamili.rachid.savingdata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.kamili.rachid.savingdata.data.LocalDataConstract;
import me.kamili.rachid.savingdata.data.LocalDataSource;
import me.kamili.rachid.savingdata.model.Person;

public class MainActivity extends AppCompatActivity {

    private EditText etSharedPref;
    private TextView tvDatabase;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etGender;
    private TextView tvSharedPref;
    private TextView tvAllPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        etSharedPref = findViewById(R.id.etSharePref);
        tvSharedPref = findViewById(R.id.tvSharedPref);

        //SQLite side
        tvDatabase = findViewById(R.id.tvDatabase);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etGender = findViewById(R.id.etGender);
        tvAllPersons = findViewById(R.id.tvAllPersons);

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

    public void handlingSGLite(View view) {

        LocalDataSource dataSource = new LocalDataSource(this);

        switch (view.getId()) {
            case R.id.btnSavePerson:
                Person person = new Person(
                        etFirstName.getText().toString(),
                        etLastName.getText().toString(),
                        etGender.getText().toString()
                );

                long rowNumber = dataSource.savePerson(person);

                Toast.makeText(this, String.valueOf(rowNumber), Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnRetrievePerson:
                tvAllPersons.setText(dataSource.getAllPersons().toString());
                break;
        }

    }
}
