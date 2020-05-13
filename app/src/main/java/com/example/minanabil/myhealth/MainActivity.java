package com.example.minanabil.myhealth;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;


import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.classifiers.trees.J48;
import weka.filters.supervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.RemoveType;
import weka.filters.unsupervised.attribute.StringToNominal;


import static com.example.minanabil.myhealth.R.raw.general;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MST_LENGTH_LIMIT = 5;
    public static final int RC_SIGN_IN = 1;
    private static final int RC_SENDER_PICKER =  2;


    private ListView mMeasurementListView;
    private MeasurementAdapter mMeasurementAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMeasurementEditText;
    private Button msubmitButton;
    private Button sendButton;
    private Measurement measurement;
    private List<AuthUI.IdpConfig> providers;
    final private List<EditText> EditTextMeasurement = new ArrayList<>();
    final private List<Object> MeasureText = new ArrayList<>();


    private String mUsername;
    private List<String> mKey;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMeasurementDatabaseReference, ref;
    private ChildEventListener mChildEventListener;
    private ValueEventListener mValueEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DataSnapshot mDataSnapshot;

    private DataSource source;
    private BufferedReader reader;
    private AttributeSelectedClassifier classifier;
    private CfsSubsetEval eval;
    private NaiveBayes base;
    private Evaluation evaluation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = ANONYMOUS;

        providers = new ArrayList<>();


    //    Log.v("summary",evaluation.toSummaryString());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        ref = mFirebaseDatabase.getInstance().getReference();
        // mMeasurementDatabaseReference = ref.child("Measurements");


        mMeasurementListView = (ListView) findViewById(R.id.my_list);

        msubmitButton = (Button) findViewById(R.id.btnSave);
      //  sendButton = (Button) findViewById(R.id.send_report);

        final List<Measurement> measurements = new ArrayList<>();
        mMeasurementAdapter = new MeasurementAdapter(this, R.layout.item_measurement, measurements);
        mMeasurementListView.setAdapter(mMeasurementAdapter);


        // mMeasurementEditText = (EditText) findViewById(R.id.inputMeasurementEditText);

        //  mMeasurementEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MST_LENGTH_LIMIT)});

        //Measurement mMeasurement = (EditText) getResourcesById(R.id.inputMeasurementEditText);
                
                
        msubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: submit values on click
                // if(measurement != null) {
//                    measurement = new Measurement("Heart Rate", Double.parseDouble(mMeasurementEditText.getText().toString()), mUsername);
//                    DatabaseReference HeartRef = mMeasurementDatabaseReference.child("heartrate");
//                    HashMap<String, Object> HeartUpdate = new HashMap<String, Object>();
//                    HeartUpdate.put("text", measurement.getText());
//                    HeartRef.updateChildren(HeartUpdate);

                //  }
//                int m = mMeasurementListView.getAdapter().getCount();
//                int n= mKey.size();
           //     ViewGroup trail = (ViewGroup) mMeasurementListView.getAdapter().getItem(1);
              //  int y = mMeasurementListView.getLastVisiblePosition();
              //Object x=   mMeasurementListView.getAdapter().getItem(4);


                //Object viewMeasurement =  mMeasurementListView.getAdapter().getItem(1);




                for (int i = 0; i < 14; i++) {
                 //   View viewMeasurement = mMeasurementListView.getChildAt(i);
//                    View viewMeasurement = (View) mMeasurementListView.getChildAt(mMeasurementListView.getFirstVisiblePosition());
//                    EditText editTextMeasurement = (EditText) viewMeasurement.findViewById(R.id.inputMeasurementEditText);
//                    EditTextMeasurement.add(editTextMeasurement);
                    MeasureText.add( mMeasurementAdapter.getItem(i).getText());

                }

               // Object x=   mMeasurementListView.getAdapter().getItem(2);
                //int z = MeasureText.size();
//                String textToBeSent = createSummaryReport(EditTextMeasurement);


                Map<String, Object> measurementUpdate = new HashMap<>();
                for (int n = 0 ; n < 14 ; n++) {
                    measurementUpdate.put(mKey.get(n) + "/text", MeasureText.get(n));
                }
//

                mMeasurementDatabaseReference.updateChildren(measurementUpdate);

                Toast.makeText(getApplicationContext(),"your records has just been submitted and we will notify you soon by results",Toast.LENGTH_LONG).show();
              //  displayMessage(textToBeSent);


//


                try {
                    InputStream Tdata = getResources().openRawResource(R.raw.general2);



                    Instances Train;
                    Train = get_Instances(Tdata);
                    int v = Train.numAttributes();
                    Instances Test;
                    Test = getInstance(mMeasurementAdapter);
                    //Train.add(Test.instance(0));

                    try {
//                    AttributeSelection filter = new AttributeSelection();
//                        Log.e("my app","can do previous1 things");
//                    CfsSubsetEval eval = new CfsSubsetEval();
//                        Log.e("my app","can do previous2 things");
//                    GreedyStepwise search = new GreedyStepwise();
//                        Log.e("my app","can do previous3 things");
//                    search.setSearchBackwards(true);
//                        Log.e("my app","can do previous4 things");
//                    filter.setEvaluator(eval);
//                        Log.e("my app","can do previous5 things");
//                    filter.setSearch(search);
//                        Log.e("my app","can do previous6 things");
//                    filter.setInputFormat(Test);
//                        Log.e("my app","can do previous7 things");
//                  Instances newData = Filter.useFilter(Test, filter);
//                    Log.e("my app","can do previous8 things");
                       // boolean invert = true ;
                        Classifier base = new NaiveBayes();
                        //Classifier base2 = new NaiveBayesMultinomial();

//                        SelectedTag tag = new SelectedTag(Attribute.STRING,RemoveType.TAGS_ATTRIBUTETYPE);
//                        RemoveType rm = new RemoveType();
//                        rm.setAttributeType(tag);
//                        //rm.setInvertSelection(invert);
//                        rm.setInputFormat(Test);
//
//                        Instances TestNew = Filter.useFilter(Test,rm);
//                        rm.batchFinished();
//                         TestNew = Filter.useFilter(TestNew,rm);
//                        int g = TestNew.numAttributes();
//                        TestNew.setClassIndex(11);
                        boolean bool = true;
                        StringToNominal StoNomial = new StringToNominal();
                        StoNomial.setAttributeRange("12,13,14,15");
                        StoNomial.setInputFormat(Train);
                        Instances TrainNew = Filter.useFilter(Train,StoNomial);
                        StoNomial.batchFinished();
                        TrainNew= Filter.useFilter(TrainNew,StoNomial);
//                        Normalize tNormalize = new Normalize();
//                        tNormalize.setInputFormat(TrainNew);
//                        Instances TrainNew0 = Filter.useFilter(TrainNew,tNormalize);
//                        tNormalize.batchFinished();
//                        TrainNew0 = Filter.useFilter(TrainNew0,tNormalize);
//                        AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
//                        CfsSubsetEval eval1 = new CfsSubsetEval();
//                        GreedyStepwise search = new GreedyStepwise();
//                        search.setSearchBackwards(true);
//                        classifier.setClassifier(base);
//                        classifier.setEvaluator(eval1);
//                        classifier.setSearch(search);
//                        Evaluation evaluation = new Evaluation(TrainNew0);
//                        evaluation.evaluateModel(classifier, TrainNew0);
////
                        NominalToBinary NtoBinary = new NominalToBinary();
                        NtoBinary.setTransformAllValues(bool);
                        NtoBinary.setInputFormat(TrainNew);
                        Instances TrainNew2 = Filter.useFilter(TrainNew,NtoBinary);
                        NtoBinary.batchFinished();
                        TrainNew2 = Filter.useFilter(TrainNew2,NtoBinary);
                          //String Result = evaluation.toSummaryString("\nResults\n======\n",false);
//                        displayMessage(Result);
//
                        base.buildClassifier(TrainNew2);
//
                        Log.e("my app","can do previous1 things");

                        Evaluation eval = new Evaluation(TrainNew2);

                        Log.e("my app","can do previous2 things");
                        eval.evaluateModel(base,TrainNew2);
                        Log.e("my app","can do previous3 things");

                 //       String result = eval.toSummaryString("\nResults\n======\n",false);
                        eval.evaluateModel(base,Test);
                   //     String result2 = eval.toSummaryString("\nResults\n======\n",false);

//                        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream("classifier.model"));
//                        oos.writeObject(base);
//                        oos.writeObject(TrainNew2);
//                        oos.flush();
//                        oos.close();
                       //double result1 =  eval.correlationCoefficient();
//                        double clslabel1 = base.classifyInstance(TrainNew2.instance(100));
//                        double d2 = clslabel1;
//                        String Result2 = TrainNew2.attribute(14).value((int)clslabel1);

                        double clslabel = base.classifyInstance(Test.instance(0));
                        double d = clslabel;


                        String Result = Test.attribute(14).value((int)clslabel);
                        String Result1;
                        if (Double.parseDouble(MeasureText.get(5).toString())<=90 || Double.parseDouble(MeasureText.get(5).toString())>=120 || Double.parseDouble(MeasureText.get(6).toString())<=60 || Double.parseDouble(MeasureText.get(6).toString())>=80 || Double.parseDouble(MeasureText.get(7).toString())<=70 || Double.parseDouble(MeasureText.get(7).toString())>=140){
                             Result1= "abnormal";
                            displayMessage(Result1);

                        }else{
                            displayMessage(Result);
                        }
 //                       displayMessage(Result);
                        Log.e("my app","can do previous4 things");




//                        Remove rm4 = new Remove();
//                        rm4.setAttributeIndices("4");
//                        Remove rm5 = new Remove();
//                        rm5.setAttributeIndices("5");
//                        Remove rm15 = new Remove();
//                        rm15.setAttributeIndices("15");
//                        Filter StoNomial = new StringToNominal();
//                        StoNomial.setInputFormat(Test);


                      //  Classifier base = new NaiveBayes();

//                        FilteredClassifier fc = new FilteredClassifier();
//                        fc.setFilter(rm);
//                        fc.setFilter(rm4);
//                        fc.setFilter(rm5);
//                        fc.setFilter(rm15);
//                        fc.setClassifier(base);
//                        Log.e("my app","can do previous2 things");
////
////
//                        fc.buildClassifier(Test);
//                        Log.e("my app","can do previous3 things");



//                        base.buildClassifier(Test);
//                        Log.e("my app","can do previous2 things");

                        MeasureText.clear();
                }catch (Exception e) {
                    e.printStackTrace();
                    Log.e("my app","cannot do it");

                }
                    //Classifier base = new NaiveBayes();


//                    source = new DataSource(Tdata);
//                    Instances data = source.getDataSet();
//                  //  data.setClassIndex(data.numAttributes() - 1);
//                  //  Classifier cls = new NaiveBayes();
//                    //cls.buildClassifier(data);
//                    Log.e("my app","good yad ");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("my app","7omar yad ");
                }

//
//
//                Instances testing;
//                try {
//                    testing = getInstance(mMeasurementAdapter);
//
//                    try {
//                        double clstesting = base.classifyInstance(testing.instance(0));
//                        //String result = getInstance(mMeasurementAdapter).classAttribute().value((int) clstesting);
//                        //displayMessage(result);
//                        Log.e("my app", "done");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.e("my app", "fuckyoooooooooooooou ");
//                    }
//                } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }



//
//                try {
//                    InputStream Tdata = getResources().openRawResource(R.raw.general2);
//                    source = new DataSource(Tdata);
//                    Instances data = source.getDataSet();
//                   // int numInstances = data.numInstances();
//
//                //    Log.e("my app", ""+numInstances);
//
//
//                    Log.e("my app","can load file");
////
////    try {
////                       data.setClassIndex(numAtt-1);
////                   }catch (Exception e) {
////                       e.printStackTrace();
////                       Log.e("my app","cannot do dataindex ");
////
////                   }
//
////
////                    Log.e("my app","can do previous things");
////                    try {
////                        evaluation = new Evaluation(data);
////                        Log.e("my app","can create evaluation");
////                        try {
////                            evaluation.crossValidateModel(classifier,data,10,new Random(1));
////                            Log.e("my app","can run evuluation ");
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                            Log.e("my app","cannot run evuluation ");
////
////                        }
////
////
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                        Log.e("my app","cannot create evaluation");
////
////                    }
//
////
////      Log.e("my app","can create instanses");
//                try {
//                    AttributeSelection filter = new AttributeSelection();
//                    CfsSubsetEval eval = new CfsSubsetEval();
//                    GreedyStepwise search = new GreedyStepwise();
//                    search.setSearchBackwards(true);
//                    filter.setEvaluator(eval);
//                    filter.setSearch(search);
//                    filter.setInputFormat(data);
////                    Instances newData = Filter.useFilter(data, filter);
//                    Log.e("my app","can do previous things");
//                }catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e("my app","cannot do it");
//
//                }
////                    AttributeSelection filter = new AttributeSelection();
////                    CfsSubsetEval eval = new CfsSubsetEval();
////                    GreedyStepwise search = new GreedyStepwise();
////                    search.setSearchBackwards(true);
////                    filter.setEvaluator(eval);
////                    filter.setSearch(search);
////                    filter.setInputFormat(data);
////                    Instances newData = Filter.useFilter(data, filter);
////                    Log.e("my app","can do previous things");
//
//
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e("my app","cannot load file");
//
//                }
////                try {
////                     reader = new BufferedReader(new FileReader(String.valueOf(R.raw.general)));
////                    Log.e("my app ", "you read it right");
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                    Log.v("my app","can load file");
////
////
////                }
////                Instances data = null;
////                try {
////                    data = new Instances(reader);
////                    Log.e("my app ", "you created it right");
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////                    Log.e("my app ", "you cannot created it right");
////
////                }
////
////                try {
////                    reader.close();
////                    Log.e("my app ", "you did it right");
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////                    Log.e("my app ", "you didnot it right");
////
////                }
////                data.setClassIndex(data.numAttributes() - 1);




            }
        });
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendReport();
//            }
//        });



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    onSignedOutCleanup();
                    providers.clear();
                    providers.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
                    providers.add(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());
                    startActivityForResult(

                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        detachDatabaseReadListener();
        mMeasurementAdapter.clear();
    }
    public void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "sign in ! ", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "sign in canceled", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
        // TODO:1) make database of users and measurement is a child to each user

        //
        // todo:2) on signed is initalise check if user is found in data base i will just push new measurements if not i will create a new user
        // todo:3) weight and height is required but i need to understand how are not they variables and how to get it on initalizing
        // todo:4)notification handling
        // todo:5)time reference
        //todo:6)fetshing data according to user name and time

        mMeasurementDatabaseReference = ref.child("Measurements");
//        HashMap<String, Measurement> Measurements = new HashMap<String, Measurement>();
//        Measurements.put("heartrate"+mUsername,new Measurement("Heart Rate",0.0,mUsername));
//        Measurements.put("sugarrate"+mUsername,new Measurement("Sugar Rate",0.0,mUsername));
//        Measurements.put("temperature"+mUsername,new Measurement("Temperature",0.0,mUsername));
//
//        mMeasurementDatabaseReference.setValue(Measurements);

        mKey = new ArrayList<>();
        HashMap<String,Object> Measurements = new HashMap<String,Object>();
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(0),new Measurement("Gender","0",mUsername));

        //mMeasurementDatabaseReference.push().setValue(new Measurement("Heart Rate", 0.0, mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(1),new Measurement("Age","0",mUsername));
        //mMeasurementDatabaseReference.push().setValue(new Measurement("Sugar Rate", 0.0, mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(2),new Measurement("Weight","0",mUsername));
        //mMeasurementDatabaseReference.push().setValue(new Measurement("Temperature", 0.0, mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());

        Measurements.put(mKey.get(3),new Measurement("Diseases","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(4),new Measurement("Smoker","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(5),new Measurement("Blood pressure sys","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(6),new Measurement("Blood pressure DIA","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(7),new Measurement("Gulco","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(8),new Measurement("Temperature","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(9),new Measurement("Pulse","0",mUsername));
        mMeasurementDatabaseReference.updateChildren(Measurements);
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(10),new Measurement("GSR cond","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(11),new Measurement("GSR res","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(12),new Measurement("GSR volt","0",mUsername));
        mKey.add(mMeasurementDatabaseReference.child("Measurements").push().getKey());
        Measurements.put(mKey.get(13),new Measurement("Oximeter spo2","0",mUsername));



        mMeasurementDatabaseReference.updateChildren(Measurements);

        attachDatabaseReadListener();
        // attachDatabaseReadListener2();

    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        detachDatabaseReadListener();
        //detachDatabaseReadListener2();
        mMeasurementAdapter.clear();

    }

    //    private void attachDatabaseReadListener2() {
//        if(mValueEventListener == null) {
//            mValueEventListener = new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Measurement measurement = dataSnapshot.getValue(Measurement.class);
//                    mMeasurementAdapter.add(measurement);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//
//            };
//            mMeasurementDatabaseReference.addValueEventListener(mValueEventListener);
//        }
//
//    }
//    private void detachDatabaseReadListener2(){
//        if(mValueEventListener!=null) {
//            mMeasurementDatabaseReference.removeEventListener(mValueEventListener);
//        }
//    }
    private void attachDatabaseReadListener() {

        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Measurement measurement = dataSnapshot.getValue(Measurement.class);
                    mMeasurementAdapter.add(measurement);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //measurement = dataSnapshot.getValue(Measurement.class);
                    // mMeasurementAdapter.add(measurement);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mMeasurementDatabaseReference.orderByChild("userName").equalTo(mUsername).limitToLast(14).addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mMeasurementDatabaseReference.removeEventListener(mChildEventListener);
        }
    }

//    private void sendReport() {
//       // String textToBeSent = createSummaryReport(EditTextMeasurement);
//        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
//        // sendIntent.setType("text/plain");
//
//        sendIntent.setData(Uri.parse("mail to"));
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "checkup report of " + mUsername);
//        sendIntent.putExtra(Intent.EXTRA_TEXT,textToBeSent );
////       if (sendIntent.resolveActivity(getPackageManager()) != null) {
////          startActivityForResult(Intent.createChooser(sendIntent, "Complete action using"),RC_SENDER_PICKER);
////          // startActivity(Intent.createChooser(sendIntent, "Send email"));
////        }
//        if (sendIntent.resolveActivity(getPackageManager()) != null) {
//            //startActivityForResult(Intent.createChooser(sendIntent, "Complete action using"),RC_SENDER_PICKER);
//            startActivity(sendIntent);
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "You don't have any email apps to contact us.", Toast.LENGTH_SHORT).show();
//        }
//
//
//
//    }

//    private String createSummaryReport(List<EditText> editTextMeasurement) {
//        String sendReportMessage;
//        sendReportMessage = mUsername+"'s report";
//        sendReportMessage = sendReportMessage + "\nmy usual checkup";
//        sendReportMessage = sendReportMessage + "\n    my heart rate is " + editTextMeasurement.get(0).getText().toString();
//        if (Double.parseDouble(editTextMeasurement.get(0).getText().toString())<60.0 || Double.parseDouble(editTextMeasurement.get(0).getText().toString())>85){
//            sendReportMessage = sendReportMessage + "\n ----> your heart rate is not normal ";
//        }else {
//            sendReportMessage = sendReportMessage + "\n ----> your heart rate is normal ";
//        }
//        sendReportMessage = sendReportMessage + "\n    my sugar rate is " + editTextMeasurement.get(1).getText().toString();
//        if (Double.parseDouble(editTextMeasurement.get(1).getText().toString())<60.0 || Double.parseDouble(editTextMeasurement.get(1).getText().toString())>130.0){
//            sendReportMessage = sendReportMessage + "\n ----> your sugar rate is not normal ";
//        }else {
//            sendReportMessage = sendReportMessage + "\n ----> your sugar rate is normal ";
//        }
//        sendReportMessage = sendReportMessage + "\n    my temperature is " + editTextMeasurement.get(2).getText().toString();
//        if (Double.parseDouble(editTextMeasurement.get(2).getText().toString())<37 || Double.parseDouble(editTextMeasurement.get(2).getText().toString())>37.5){
//            sendReportMessage = sendReportMessage + "\n ----> your temperature is not normal ";
//        }else {
//            sendReportMessage = sendReportMessage + "\n ----> your temperature  is normal ";
//        }
//        sendReportMessage = sendReportMessage + "\nthank you ";
//
//        return sendReportMessage;
//    }
    private void displayMessage(String message) {
        TextView reportsummaryTextView = (TextView) findViewById(R.id.report_summary_text_view);
        reportsummaryTextView.setText(message);
        reportsummaryTextView.setMovementMethod(new ScrollingMovementMethod());
    }
    private Instances getInstance(MeasurementAdapter measurementAdapter) throws FileNotFoundException {
        int numOfatt = 15;
        int numOfinstance = 1;


        Attribute att1 = new Attribute("age");
        Attribute att2 = new Attribute("weight");
        Attribute att3 = new Attribute("Blood pressure sys");
        Attribute att4 = new Attribute("Blood pressure DIA");
        Attribute att5 = new Attribute("Gulco");
        Attribute att6 = new Attribute("Temperature");
        Attribute att7 = new Attribute("Pulse");
        Attribute att8 = new Attribute("GSR cond");
        Attribute att9 = new Attribute("GSR res");
        Attribute att10 = new Attribute("GSR volt");
        Attribute att11 = new Attribute("Oximeter spo2");
        Attribute att12 = new Attribute("gender",(FastVector)null);
        Attribute att13 = new Attribute("Diseases",(FastVector)null);
        Attribute att14 = new Attribute("Smoker",(FastVector)null);
        FastVector fvClassVal = new FastVector(2);
        fvClassVal.addElement("normal");
        fvClassVal.addElement("abnormal");

        Attribute attClass = new Attribute("class",fvClassVal);

        FastVector fwekaAtt = new FastVector(numOfatt);
        fwekaAtt.addElement(att1);
        fwekaAtt.addElement(att2);
        fwekaAtt.addElement(att3);
        fwekaAtt.addElement(att4);
        fwekaAtt.addElement(att5);
        fwekaAtt.addElement(att6);
        fwekaAtt.addElement(att7);
        fwekaAtt.addElement(att8);
        fwekaAtt.addElement(att9);
        fwekaAtt.addElement(att10);
        fwekaAtt.addElement(att11);
        fwekaAtt.addElement(att12);
        fwekaAtt.addElement(att13);
        fwekaAtt.addElement(att14);
        fwekaAtt.addElement(attClass);

        Instances test = new Instances("Rel", fwekaAtt, numOfinstance);
        test.setClassIndex(14);

        Instance ourPatient = new DenseInstance(15);


        ourPatient.setValue(att1, Double.parseDouble(measurementAdapter.getItem(1).getText()));
        ourPatient.setValue(att2, Double.parseDouble(measurementAdapter.getItem(2).getText()));

        ourPatient.setValue(att3, Double.parseDouble(measurementAdapter.getItem(5).getText()));
        ourPatient.setValue(att4, Double.parseDouble(measurementAdapter.getItem(6).getText()));
        ourPatient.setValue(att5, Double.parseDouble(measurementAdapter.getItem(7).getText()));
        ourPatient.setValue(att6, Double.parseDouble(measurementAdapter.getItem(8).getText()));
        ourPatient.setValue(att7,Double.parseDouble(measurementAdapter.getItem(9).getText()));
        ourPatient.setValue(att8,Double.parseDouble(measurementAdapter.getItem(10).getText()));
        ourPatient.setValue(att9, Double.parseDouble(measurementAdapter.getItem(11).getText()));
        ourPatient.setValue(att10, Double.parseDouble(measurementAdapter.getItem(12).getText()));
        ourPatient.setValue(att11, Double.parseDouble(measurementAdapter.getItem(13).getText()));
        ourPatient.setValue(att12, measurementAdapter.getItem(0).getText());
        ourPatient.setValue(att13, measurementAdapter.getItem(3).getText());
        ourPatient.setValue(att14, measurementAdapter.getItem(4).getText());
       // ourPatient.setValue(attClass, measurementAdapter.getItem(15).getText());
        test.add(ourPatient);
        return test;


    }

    private Instances get_Instances(InputStream is) throws FileNotFoundException {

        int numOfatt = 15;
        int numOfinstance = 120;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is , Charset.forName("UTF-8"))
        );
        //step over header
        Attribute att1 = new Attribute("age");
        Attribute att2 = new Attribute("weight");
        Attribute att3 = new Attribute("Blood pressure sys");
        Attribute att4 = new Attribute("Blood pressure DIA");
        Attribute att5 = new Attribute("Gulco");
        Attribute att6 = new Attribute("Temperature");
        Attribute att7 = new Attribute("Pulse");
        Attribute att8 = new Attribute("GSR cond");
        Attribute att9 = new Attribute("GSR res");
        Attribute att10 = new Attribute("GSR volt");
        Attribute att11 = new Attribute("Oximeter spo2");
        Attribute att12 = new Attribute("gender",(FastVector)null);
        Attribute att13 = new Attribute("Diseases",(FastVector)null);
        Attribute att14 = new Attribute("Smoker",(FastVector)null);
        Attribute attClass = new Attribute("class",(FastVector)null);

        FastVector fwekaAtt = new FastVector(numOfatt);
        fwekaAtt.addElement(att1);
        fwekaAtt.addElement(att2);
        fwekaAtt.addElement(att3);
        fwekaAtt.addElement(att4);
        fwekaAtt.addElement(att5);
        fwekaAtt.addElement(att6);
        fwekaAtt.addElement(att7);
        fwekaAtt.addElement(att8);
        fwekaAtt.addElement(att9);
        fwekaAtt.addElement(att10);
        fwekaAtt.addElement(att11);
        fwekaAtt.addElement(att12);
        fwekaAtt.addElement(att13);
        fwekaAtt.addElement(att14);
        fwekaAtt.addElement(attClass);

        Instances test = new Instances("Rel", fwekaAtt, numOfinstance);
        test.setClassIndex(14);
        try {
            reader.readLine();

        String line = "";

        while ((line = reader.readLine())!= null) {

            String[] Tokens = line.split(",");
            Instance ourPatient = new DenseInstance(15);
            String m = Tokens[0];

            ourPatient.setValue(att1, Double.parseDouble(Tokens[2]));
            ourPatient.setValue(att2, Double.parseDouble(Tokens[3]));

            ourPatient.setValue(att3, Double.parseDouble(Tokens[6]));
            ourPatient.setValue(att4, Double.parseDouble(Tokens[7]));
            ourPatient.setValue(att5, Double.parseDouble(Tokens[8]));
            ourPatient.setValue(att6, Double.parseDouble(Tokens[9]));
            ourPatient.setValue(att7,Double.parseDouble(Tokens[10]));
            ourPatient.setValue(att8,Double.parseDouble(Tokens[11]));
            ourPatient.setValue(att9, Double.parseDouble(Tokens[12]));
            ourPatient.setValue(att10, Double.parseDouble(Tokens[13]));
            ourPatient.setValue(att11, Double.parseDouble(Tokens[14]));
            ourPatient.setValue(att12, Tokens[1]);
            ourPatient.setValue(att13, Tokens[4]);
            ourPatient.setValue(att14, Tokens[5]);
            ourPatient.setValue(attClass, Tokens[15]);
            test.add(ourPatient);
        }




//        test.add(ourPatient);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return test;
    }

}

