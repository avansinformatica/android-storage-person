package com.appsfromholland.track_3_sqlite_2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;
    private EditText emailEditText;
    private EditText imageUrlEditText;

    private PersonDBHandler pdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        imageUrlEditText = (EditText) findViewById(R.id.imageUrlEditText);

        pdb = new PersonDBHandler(getApplicationContext(), null, null, 1);

        //
        Button addBtn = (Button) findViewById(R.id.updateButton);
        addBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, "Add to DB Button click - Person toevoegen");

                Person p = new Person();

                p.setFirstName(firstNameEditText.getText().toString());
                p.setLastName(lastNameEditText.getText().toString());
                int age = 0;
                try {
                    age = Integer.parseInt(ageEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Log.i(TAG, "parse value is not valid : " + e);
                }
                p.setAge(age);
                p.setEmail(emailEditText.getText().toString());
                p.setImageUrl(imageUrlEditText.getText().toString());

                Log.i(TAG, "Adding Person " + p);
                pdb.addPerson(p);

                // Schermvelden leegmaken
                firstNameEditText.setText("");
                lastNameEditText.setText("");
                ageEditText.setText("");
                emailEditText.setText("");
            }
        });

        Button showBtn = (Button) findViewById(R.id.showButton);
        showBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, "Show DB Button click - All persons in database:");

//                pdb.getPersonByFirstName("Diederich");
                pdb.findAllPersons();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
