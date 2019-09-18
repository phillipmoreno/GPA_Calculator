/**
 * Name: Phillip Moreno
 *
 * Professor: Moaath Alrajab
 *
 * Course: BCS 421 - Android Mobile Development
 *
 * Date: September 17, 2019
 *
 * Description: A GPA Calculator that takes in the grade of five(5) courses and calculates the
 * average. Depending on the grade average, the user receives a message based on their standing and
 * the background color changes as well to symbolize the current grade scale.
 */
package com.example.gpa_calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // New View object created
    View view;

    // New TextView object created
    TextView TVGPA;

    // New Button object created
    Button compute;

    // EditText objects created
    EditText grade1Input;
    EditText grade2Input;
    EditText grade3Input;
    EditText grade4Input;
    EditText grade5Input;

    // Declaration of variables
    double grade1, grade2, grade3, grade4, grade5;

    // count set to 0 to be used in switch statement
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();

        // Linked TextView Object to ID
        TVGPA = findViewById(R.id.TVGPA);
        // Linked Button object to ID
        compute = findViewById(R.id.compute);

        // Linked EditText Objects to ID
        grade1Input = findViewById(R.id.grade1);
        grade2Input = findViewById(R.id.grade2);
        grade3Input = findViewById(R.id.grade3);
        grade4Input = findViewById(R.id.grade4);
        grade5Input = findViewById(R.id.grade5);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch statement used to navigate through button functions dependent on clicks
                // count
                switch (count) {
                    // Action performed on first button click
                    case 0:
                        // What will happen in the event that a field is missing?
                        if (grade1Input.getText().toString().equals("") || grade2Input.getText().toString().equals("") || grade3Input.getText().toString().equals("") || grade4Input.getText().toString().equals("") || grade5Input.getText().toString().equals("")) {
                            // User is notified that they are missing input in one or more fields
                            Toast.makeText(MainActivity.this, R.string.missing, Toast.LENGTH_SHORT).show();
                        } else {
                            // Input values are retrieved
                            grade1 = Double.valueOf(grade1Input.getText().toString());
                            grade2 = Double.valueOf(grade2Input.getText().toString());
                            grade3 = Double.valueOf(grade3Input.getText().toString());
                            grade4 = Double.valueOf(grade4Input.getText().toString());
                            grade5 = Double.valueOf(grade5Input.getText().toString());
                            // findAverage method is called
                            double avg = findAverage();
                            // changeColor Method is called
                            changeColor(avg);
                            TVGPA.setText("Your GPA is: " + avg);
                            // Once action is completed the text is changed from Compute GPA to
                            // Clear
                            compute.setText("Clear");
                            count = 1;
                        }
                        break;
                    // Action performed on second button click or after first action is completed
                    case 1:
                        grade1Input.setText("");
                        grade2Input.setText("");
                        grade3Input.setText("");
                        grade4Input.setText("");
                        grade5Input.setText("");
                        TVGPA.setText("");
                        view.setBackgroundResource(R.color.white);
                        compute.setText("Compute GPA");
                        // count is set back to 0 once second action is performed
                        count = 0;
                        break;
                }
            }
        });
    }

    // Method used to compute the gpa average and returns a double value
    public double findAverage() {
        double average = (grade1 + grade2 + grade3 + grade4 + grade5) / 5;
        return average;
    }

    // Method used to change the background color depending on the double value inside the parameter
    // and displays a message depending on the grading scale.
    public void changeColor(double avg) {
        if (avg <= 100 && avg >= 80) {
            view.setBackgroundResource(R.color.green);
            Toast.makeText(MainActivity.this, R.string.great, Toast.LENGTH_SHORT).show();
        } else if (avg < 80 && avg >= 60) {
            view.setBackgroundResource(R.color.yellow);
            Toast.makeText(MainActivity.this, R.string.okay, Toast.LENGTH_SHORT).show();
        } else {
            view.setBackgroundResource(R.color.red);
            Toast.makeText(MainActivity.this, R.string.bad, Toast.LENGTH_SHORT).show();
        }
    }
}

