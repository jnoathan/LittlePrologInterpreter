package com.example.user.assignment5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * The lists are the database, where it stores all the predicates, constants and queries
     */
    EditText editText;
    TextView textView;

    ArrayList<String> authorNames = new ArrayList<>();
    ArrayList<View> predicateList = new ArrayList<>();
    ArrayList<View> tmp = new ArrayList<>();
    ArrayList<EditText> predicate = new ArrayList<>();
    ArrayList<View> query = new ArrayList<>();
    ArrayList<EditText> constant = new ArrayList<>();

    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * to reset the activity when Reset button is clicked
         */
        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);

                    startActivity(intent);
                    overridePendingTransition(0, 0);
            }
        });

        /**
         // when the "Search Query" button is clicked, the query block will appear and the predicate block will
         disappear, and the button changes text to "Create predicate"
         **/
        findViewById(R.id.searchQueryBtn).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (findViewById(R.id.query).getVisibility() == View.GONE) {
                    Button button = (Button)findViewById(R.id.searchQueryBtn);
                    button.setText("Create Predicate");
                    findViewById(R.id.query).setVisibility(View.VISIBLE);
                    findViewById(R.id.predicate).setVisibility(View.GONE);
                }
                else {
                        Button button = (Button)findViewById(R.id.searchQueryBtn);
                        button.setText("Search Query");
                        findViewById(R.id.query).setVisibility(View.GONE);
                        findViewById(R.id.predicate).setVisibility(View.VISIBLE);
                }
            }
        });

        // This defines your touch listener
       class MyTouchListener implements View.OnTouchListener {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                            view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    //view.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        }
        findViewById(R.id.predicate).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.query).setOnTouchListener(new MyTouchListener());

        class MyDragListener implements View.OnDragListener {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                float coordX = event.getX();
                float coordY = event.getY();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        LayoutInflater inflater = getLayoutInflater();
                        View inflatedPredicate = inflater.inflate(R.layout.predicate, null);    //inflate the view (predicate block)
                        LayoutInflater inflater1 = getLayoutInflater();
                        View inflatedQuery = inflater1.inflate(R.layout.query, null);       //inflate the view (query block)
                        if (findViewById(R.id.predicate).getVisibility() == View.VISIBLE) {
                            RelativeLayout container = (RelativeLayout) v;
                            container.addView(inflatedPredicate);
                            inflatedPredicate.setX(coordX - inflatedPredicate.getWidth() / 2);      //so that the inflated view can appear on the drop target
                            inflatedPredicate.setY(coordY - inflatedPredicate.getHeight() / 2);     // so that the inflated view can appear on the drop target
                            inflatedPredicate.setVisibility(View.VISIBLE);                          // the inflated view (predicate) is set to visible on the drop target
                            predicateList.add(inflatedPredicate);                // the view (predicate) is then added into an arraylist as a database
                            View a = predicateList.get(0);      //the view is casted out to the layout
                            TableRow b = (TableRow) a;
                            EditText pred = (EditText) b.getChildAt(0);     //to get the edittext for the predicate input by user
                            EditText cons = (EditText) b.getChildAt(2);     //to get the edittex for the constant input by user
                            predicate.add(pred);        //this list stores only the predicates input by user
                            constant.add(cons);         //this list stores only constants input by user
                            tmp.add(inflatedPredicate);

                        } else {
                            RelativeLayout container1 = (RelativeLayout) v;
                            container1.addView(inflatedQuery);
                            inflatedQuery.setX(coordX - inflatedQuery.getWidth() / 2);
                            inflatedQuery.setY(coordY - inflatedQuery.getHeight() / 2);
                            inflatedQuery.setVisibility(View.VISIBLE);
                            query.add(inflatedQuery);
                            break;
                        }
                    case DragEvent.ACTION_DRAG_ENDED:
                    default:
                        break;
                }
                return true;
            }

        }
        //this function defines the home page animation
        findViewById(R.id.dropTarget).setOnDragListener(new MyDragListener());
        findViewById(R.id.startProlog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.homePage).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.homePage).setVisibility(View.GONE);
                    findViewById(R.id.metaInfoPage).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.createNewButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (findViewById(R.id.metaInfoPage).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.metaInfoPage).setVisibility(View.GONE);
                    EditText authorName = (EditText) findViewById(R.id.authorText);
                    String strAuthorName = authorName.getText().toString();
                    authorNames.add(strAuthorName);
                    TextView outputText = (TextView)findViewById(R.id.outputText);
                    outputText.append("Author: " + strAuthorName + "\n");
                }
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {Context context = getApplicationContext();
                CharSequence text = "Prolog file saved!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        //this function defines what happens when Run button is clicked
        findViewById(R.id.runButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View  v) {
                TextView outputText =(TextView) findViewById(R.id.outputText);
                try {
                    if (findViewById(R.id.predicate).getVisibility() == View.VISIBLE) {
                        View a = tmp.get(0);
                        tmp.remove(0);
                        TableRow b = (TableRow) a;      //view(predicate) is casted out from the database
                        EditText pred = (EditText) b.getChildAt(0);     //to get the predicate input by the user
                        EditText cons = (EditText) b.getChildAt(2);     //to get the constant input by the user
                        String string1 = pred.getText().toString();
                        String string2 = cons.getText().toString();
                        String string3 = string2.toLowerCase();
                        if (string1.isEmpty()|string2.isEmpty()) {      //this is to check if the user had actually input or entered something
                            outputText.append("Please enter predicate or constant first before running.\n");
                        } else {
                            outputText.append("Predicate " + pred.getText() + "(" + cons.getText() + "). has been created!\n");
                        }
                    } else if (findViewById(R.id.predicate).getVisibility() == View.GONE) {     //for the case while doing a query serach
                        View c = query.get(0);
                        TableRow d = (TableRow) c;  //view (query) casted out to the layout
                        EditText queryPred = (EditText) d.getChildAt(1);       //gets the predicate of the query entered by the user
                        EditText queryCons = (EditText) d.getChildAt(3);       // gets the constant of the query entered by the user
                        for (int i = 0; i < predicate.size(); i++) {        //loops through the database to check if query entered exists in the database
                            String pred1 = (predicate.get(i).getText()).toString();
                            String qPred = (queryPred.getText()).toString();
                            String cons1 = (constant.get(i).getText()).toString();
                            String qCons = (queryCons.getText()).toString();
                            if (pred1.equals(qPred) && cons1.equals(qCons)) {
                                outputText.append("true\n");
                                break;
                            } else {
                                outputText.append("false\n");
                            }
                        }
                    }
                } catch(IndexOutOfBoundsException e) {
                    outputText.append("Please enter predicate or constant first before running.\n");
                }
            }
        });

        findViewById(R.id.instructionHome).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view ){
                findViewById(R.id.predicateInstructionPage).setVisibility(View.VISIBLE);
                findViewById(R.id.exitInstruction).setVisibility(View.VISIBLE);
                findViewById(R.id.qSearchButton).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.qSearchButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (findViewById(R.id.predicateInstructionPage).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.queryInstructionPage).setVisibility(View.VISIBLE);
                    findViewById(R.id.predicateInstructionPage).setVisibility(View.GONE);
                    Button button = (Button)findViewById(R.id.qSearchButton);
                    button.setText("Create predicate instruction");
                } else if (findViewById(R.id.queryInstructionPage).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.queryInstructionPage).setVisibility(View.GONE);
                    findViewById(R.id.predicateInstructionPage).setVisibility(View.VISIBLE);
                    Button button = (Button)findViewById(R.id.qSearchButton);
                    button.setText("Query search instruction");
                }

            }
        });

        findViewById(R.id.exitInstruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                findViewById(R.id.queryInstructionPage).setVisibility(View.GONE);
                findViewById(R.id.predicateInstructionPage).setVisibility(View.GONE);
                findViewById(R.id.exitInstruction).setVisibility(View.GONE);
                findViewById(R.id.qSearchButton).setVisibility(View.GONE);
            }
        });

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
                System.exit(0);
            }
        });
    }};