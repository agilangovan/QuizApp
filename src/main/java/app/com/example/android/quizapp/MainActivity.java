package app.com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //global variables
    int score=0;
    boolean question1=false;
    boolean question2=false;
    boolean question3=false;
    boolean question4=false;
    boolean question5=false;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String answer5;
    String resultsSummary;

    //button for submitting quiz
    public void submitQuiz(View view){

        //string for correct answer of question one. Will be used for matching with user input
        String questionOneCorrect=getString(R.string.john_adams);

        //Question  1: answer is 'John Adams' (case insensitive)
        EditText q1EditText = (EditText) findViewById(R.id.question_1_edit_text);
        String questionOneAnswer = q1EditText.getText().toString();


        //Question  2
        //first option(correct) José María Morelos
        CheckBox q2o1 = (CheckBox) findViewById(R.id.q2o1);
        boolean joMaria = q2o1.isChecked();

        //second option Guadalupe Victoria
        CheckBox q2o2 = (CheckBox) findViewById(R.id.q2o2);
        boolean guaVic = q2o2.isChecked();

        //third option (correct) Vicente Guerrero
        CheckBox q2o3 = (CheckBox) findViewById(R.id.q2o3);
        boolean viGuer = q2o3.isChecked();

        //fourth option (correct) Miguel Domínguez
        CheckBox q2o4 = (CheckBox) findViewById(R.id.q2o4);
        boolean migDom = q2o4.isChecked();


        //Question  3
        //first option(correct)  Sarvepalli Radhakrishnan
        CheckBox q3o1 = (CheckBox) findViewById(R.id.q3o1);
        boolean sarRad = q3o1.isChecked();

        //second option Varahagiri Venkata Giri
        CheckBox q3o2 = (CheckBox) findViewById(R.id.q3o2);
        boolean vvGiri = q3o2.isChecked();

        //third option(correct) Rajendra Prasad
        CheckBox q3o3 = (CheckBox) findViewById(R.id.q3o3);
        boolean rajPra = q3o3.isChecked();

        //fourth option(correct) Zakir Husain
        CheckBox q3o4 = (CheckBox) findViewById(R.id.q3o4);
        boolean zakHus = q3o4.isChecked();


        //Question  4
        //first option(correct option) 43
        RadioButton q4o1 = (RadioButton) findViewById(R.id.q4o1);
        boolean fortree = q4o1.isChecked();

        //second option 45
        RadioButton q4o2 = (RadioButton) findViewById(R.id.q4o2);
        boolean forfive = q4o2.isChecked();

        //third option 42
        RadioButton q4o3 = (RadioButton) findViewById(R.id.q4o3);
        boolean fortoo = q4o3.isChecked();

        //fourth option 44
        RadioButton q4o4 = (RadioButton) findViewById(R.id.q4o4);
        boolean forfor = q4o4.isChecked();


        //Question  5
        //first option(correct) 500,000
        CheckBox q5o1 = (CheckBox) findViewById(R.id.q5o1);
        boolean fiveHund = q5o1.isChecked();

        //second option 400,000
        CheckBox q5o2 = (CheckBox) findViewById(R.id.q5o2);
        boolean fourHund = q5o2.isChecked();

        //third option(correct) 800,000
        CheckBox q5o3 = (CheckBox) findViewById(R.id.q5o3);
        boolean eightHund = q5o3.isChecked();

        //fourth option(correct) 700,000
        CheckBox q5o4 = (CheckBox) findViewById(R.id.q5o4);
        boolean sevenHund = q5o4.isChecked();

        //boolean variables to validate user's answer for each quesiton
        question1=validateOne(questionOneAnswer, questionOneCorrect);
        question2=validateTwo(joMaria, guaVic, viGuer, migDom);
        question3=validateThree(sarRad, vvGiri, rajPra, zakHus);
        question4=validateFour(fortree, forfive, fortoo, forfor);
        question5=validateFive(fiveHund, fourHund, eightHund, sevenHund);

        //user's name
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String examineeName = nameEditText.getText().toString();

        //total score of user
        score=calculateScore(question1, question2,question3, question4,question5);

        //Toast messages
        //if user did not enter name
        if(examineeName.isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "You got 0/5. C'MON! You can't forget your name!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast= Toast.makeText(context, text, duration);
            toast.show();
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
        }

        //if user entered name
        if(!examineeName.isEmpty()){
            if(score==5){
                //if user got perfect score
                Context context = getApplicationContext();
                CharSequence perfectText = "You got 5/5. Good job " + examineeName + "!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast= Toast.makeText(context, perfectText, duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
            }else if(score <5 & score >0){
                Context context = getApplicationContext();
                CharSequence text = "You got " + score + "/5. Try again!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast= Toast.makeText(context, text, duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
            }else{
                Context context = getApplicationContext();
                CharSequence text = "You got " + score + "/5. Don't give up!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast= Toast.makeText(context, text, duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
            }
        }


    }
    //Button for grading quiz
    public void gradeQuiz(View view){

        //strings for correct and incorrect answers in results message
        String correct=getString(R.string.correct);
        String incorrect=getString(R.string.incorrect);
        String incorrectCheckBox=getString(R.string.incorrect_checkBox);

        //if user got each question right or wrong,
        // initialize answer string with with either correct or incorrect
        // depending on the boolean value in if statement
        if(question1){
            answer1=correct;
        }else{
            answer1=incorrect+ " "+ getString(R.string.john_adams);
        }

        if(question2){
            answer2=correct;
        }else{
            answer2=incorrectCheckBox+ " "+ getString(R.string.q2o1) +
                    ", "+ getString(R.string.q2o3) +
                    ", and "+ getString(R.string.q2o4);
        }

        if(question3){
            answer3=correct;
        }else{
            answer3=incorrectCheckBox+ " "+ getString(R.string.q3o3) +
                    ", "+ getString(R.string.q3o1) +
                    ", and "+ getString(R.string.q3o4);
        }

        if(question4){
            answer4=correct;
        }else{
            answer4=incorrect+ " "+ getString(R.string.q4o1);
        }

        if(question5){
            answer5=correct;
        }else{
            answer5=incorrectCheckBox+ " "+ getString(R.string.q5o1) +
                    ", "+ getString(R.string.q5o3) +
                    ", and "+ getString(R.string.q5o4);
        }

        //calculate score
        score=calculateScore(question1, question2,question3, question4,question5);

        //initialize resultsSummary string with results message
        resultsSummary= createMessage( answer1, answer2, answer3, answer4, answer5, score);

        //displaying results summary on app
        displayResults(resultsSummary);
    }

    //if user wants to retake quiz
    public void retakeQuiz(View view){

        //Question 1
        EditText q1EditText = (EditText) findViewById(R.id.question_1_edit_text);

        //clear user input for question 1
        q1EditText.setText("");

        //Question  2
        //first option(correct) José María Morelos
        CheckBox q2o1 = (CheckBox) findViewById(R.id.q2o1);
        boolean joMaria = q2o1.isChecked();

        //second option Guadalupe Victoria
        CheckBox q2o2 = (CheckBox) findViewById(R.id.q2o2);
        boolean guaVic = q2o2.isChecked();

        //third option (correct) Vicente Guerrero
        CheckBox q2o3 = (CheckBox) findViewById(R.id.q2o3);
        boolean viGuer = q2o3.isChecked();

        //fourth option (correct) Miguel Domínguez
        CheckBox q2o4 = (CheckBox) findViewById(R.id.q2o4);
        boolean migDom = q2o4.isChecked();


        //Question  3
        //first option(correct)  Sarvepalli Radhakrishnan
        CheckBox q3o1 = (CheckBox) findViewById(R.id.q3o1);
        boolean sarRad = q3o1.isChecked();

        //second option Varahagiri Venkata Giri
        CheckBox q3o2 = (CheckBox) findViewById(R.id.q3o2);
        boolean vvGiri = q3o2.isChecked();

        //third option(correct) Rajendra Prasad
        CheckBox q3o3 = (CheckBox) findViewById(R.id.q3o3);
        boolean rajPra = q3o3.isChecked();

        //fourth option(correct) Zakir Husain
        CheckBox q3o4 = (CheckBox) findViewById(R.id.q3o4);
        boolean zakHus = q3o4.isChecked();


        //Question  4
        //first option(correct option) 43
        RadioButton q4o1 = (RadioButton) findViewById(R.id.q4o1);
        boolean fortree = q4o1.isChecked();

        //second option 45
        RadioButton q4o2 = (RadioButton) findViewById(R.id.q4o2);
        boolean forfive = q4o2.isChecked();

        //third option 42
        RadioButton q4o3 = (RadioButton) findViewById(R.id.q4o3);
        boolean fortoo = q4o3.isChecked();

        //fourth option 44
        RadioButton q4o4 = (RadioButton) findViewById(R.id.q4o4);
        boolean forfor = q4o4.isChecked();


        //Question  5
        //first option(correct) 500,000
        CheckBox q5o1 = (CheckBox) findViewById(R.id.q5o1);
        boolean fiveHund = q5o1.isChecked();

        //second option 400,000
        CheckBox q5o2 = (CheckBox) findViewById(R.id.q5o2);
        boolean fourHund = q5o2.isChecked();

        //third option(correct) 800,000
        CheckBox q5o3 = (CheckBox) findViewById(R.id.q5o3);
        boolean eightHund = q5o3.isChecked();

        //fourth option(correct) 700,000
        CheckBox q5o4 = (CheckBox) findViewById(R.id.q5o4);
        boolean sevenHund = q5o4.isChecked();


        //Toggle each answer variable to unchecked if they are checked
        if(joMaria){
            q2o1.toggle();
        }
        if(guaVic){
            q2o2.toggle();
        }
        if(viGuer){
            q2o3.toggle();
        }
        if(migDom){
            q2o4.toggle();
        }
        if(sarRad){
            q3o1.toggle();
        }
        if(vvGiri){
            q3o2.toggle();
        }
        if(rajPra){
            q3o3.toggle();
        }
        if(zakHus){
            q3o4.toggle();
        }
        if(fortree){
            q4o1.setChecked(false);
        }
        if(forfive){
            q4o2.setChecked(false);
        }
        if(fortoo){
            q4o3.setChecked(false);
        }
        if(forfor){
            q4o4.setChecked(false);
        }
        if(fiveHund){
            q5o1.toggle();
        }
        if(fourHund){
            q5o2.toggle();
        }
        if(eightHund){
            q5o3.toggle();
        }
        if(sevenHund){
            q5o4.toggle();
        }

        //clear results summary
        resultsSummary="";
        displayResults(resultsSummary);
    }

    //validate question1
    public boolean validateOne(String questionOneAnswer, String questionOneCorrect){

        //if userinput equals the correct answer, return true
        //answer is not case sensitive
        if (questionOneCorrect.equalsIgnoreCase(questionOneAnswer)){
            return true;
        }

        return false;
    }
    //validate question2
    public boolean validateTwo(boolean a, boolean b, boolean c, boolean d){

        //if option b is unchecked and the rest are checked, return true
        if (a &&c &&d && !b){
            return true;
        }

        return false;
    }

    //validate question3
    public boolean validateThree(boolean a, boolean b, boolean c, boolean d){

        //if option b is unchecked and the rest are checked, return true
        if (a &&c &&d && !b){
            return true;
        }

        return false;
    }

    //validate question4
    public boolean validateFour(boolean a, boolean b, boolean c, boolean d){

        //if option a is checked, return true.
        //this question has radio buttons so only one option can be checked at a time
        if (a && !b && !c && !d){
            return true;
        }

        return false;
    }

    //validate question5
    public boolean validateFive(boolean a, boolean b, boolean c, boolean d){

        //if option b is unchecked and the rest are checked, return true
        if (a &&c &&d && !b){
            return true;
        }

        return false;

    }

    //create message for results summary
    public String createMessage(String a, String b, String c, String d, String e, int score){

        return "RESULTS" + "\n"+ "\n"+
                "Question 1: " + a + "\n"+ "\n"+
                "Question 2: " + b + "\n"+ "\n"+
                "Question 3: " + c + "\n"+ "\n"+
                "Question 4: " + d + "\n"+ "\n"+
                "Question 5: " + e + "\n"+ "\n"+
                "Your final score is: " + score + "/5";

    }

    //calculate the user's score
    public int calculateScore(boolean a, boolean b, boolean c, boolean d, boolean e){
        int answer1=0;
        int answer2=0;
        int answer3=0;
        int answer4=0;
        int answer5=0;

        //if user got a question right, 1 point will be assigned
        if(a){
            answer1=1;
        }
        if(b){
            answer2=1;
        }
        if(c){
            answer3=1;
        }
        if(d){
            answer4=1;
        }
        if(e){
            answer5=1;
        }

        return answer1+answer2+answer3+answer4+answer5;

    }

    //method to display the message on the app
    public void displayResults(String resultsSummary) {
        TextView resultsTextView = (TextView) findViewById(R.id.results_summary);
        resultsTextView.setText("" + resultsSummary);
    }

}
