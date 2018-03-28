package me.kamili.rachid.savingdata.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.PeriodicSync;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.savingdata.model.Person;

/**
 * Created by Admin on 3/28/2018.
 */

public class LocalDataSource  extends SQLiteOpenHelper{

    public LocalDataSource(Context context) {
        super(context, LocalDataConstract.NAME, null, LocalDataConstract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LocalDataConstract.CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long savePerson(Person person){
        SQLiteDatabase database = getWritableDatabase();

        //create the content value for saving the object
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataConstract.Person.FIRST_NAME,person.getFirstName());
        contentValues.put(LocalDataConstract.Person.LAST_NAME,person.getLastName());
        contentValues.put(LocalDataConstract.Person.GENDER,person.getGender());

        //insert the object in the table
        long rowNumber = database.insert(LocalDataConstract.Person.TABLE,null,contentValues);
        database.close();
        return rowNumber;

    }

    public List<Person> getAllPersons(){
        SQLiteDatabase database = getWritableDatabase();

        List<Person> personList = new ArrayList<>();

        Cursor cursor = database.rawQuery(LocalDataConstract.GETALL,null);

        if(cursor.moveToFirst()){
            do{
                Person person = new Person(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                personList.add(person);
            }while(cursor.moveToNext());
        }

        database.close();
        return personList;
    }

}
