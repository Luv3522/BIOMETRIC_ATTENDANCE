package com.example.studentpage;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Table;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class attendance_summary extends AppCompatActivity {

    private static final String TAG = "IMAD";
    PieChart pieChart;
    private Button send;
    private ArrayList<String> userList=new ArrayList<String>();
    private Map<String,String> m= new HashMap<>();
    private ArrayList<String> attendList=new ArrayList<String>();
    private File file;
    private FirebaseFirestore db;
    int present_count;
    int absent_count;
//    private EditText location;
    private Session session;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_attendance_summary);
        db=FirebaseFirestore.getInstance();
//        Intent intent = getIntent();
//        Map<String, String> m = (HashMap<String, String>)intent.getSerializableExtra("map");
//        Log.v("HashMapTest", hashMap.get("key"));

        pieChart = findViewById(R.id.pie_chart);
        getSupportActionBar().hide();
        send= findViewById(R.id.button_send);
//        location=findViewById(R.id.location);
//        Button button=findViewById(R.id.ExelWrite);
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        send.setOnClickListener(this::onClick);

        for(int i=0;i<attendList.size();i++){
            if(Objects.equals(attendList.get(i), "P")){

                present_count++;
            }else{
                absent_count++;
            }
        }




        ArrayList<PieEntry> pieEntries = new ArrayList<>();
//        for (int i=1; i<3; i++) {
//            //Convert to float
//            float value = (float) (i * 10.0);
//            //Initialize pie chart entry
//            PieEntry pieEntry = new PieEntry(i, value);
//            //Add values in array list
//            pieEntries.add(pieEntry);
//        }
        float value = (float) (36 * 10.0);
        PieEntry pieEntry1 = new PieEntry(1 , value);
        pieEntries.add(pieEntry1);

        float value1 = (float) (9 * 10.0);
        PieEntry pieEntry2 = new PieEntry(2 , value1);
        pieEntries.add(pieEntry2);

        float value2 = (float) (12 * 10.0);
        PieEntry pieEntry3 = new PieEntry(3 , value2);
        pieEntries.add(pieEntry3);



        //Initialize pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Student");
        //Set colors
        pieDataSet.setColors (ColorTemplate.COLORFUL_COLORS);
//Set pie data
        pieChart. setData (new PieData (pieDataSet));
        //Set animation
        pieChart.animateXY( 4000,  4000);
        //Hide description
        pieChart.getDescription().setEnabled(false);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();

        String date=java.time.LocalDate.now().toString();

        CollectionReference user=db.collection(date);
        user.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(TAG, document.getId() + " => " + document.getData());
                    String email= (String) document.get("email");
                    Boolean attendance= document.getBoolean(
                            "attendance taken"
                    );
                    if(Boolean.TRUE.equals(attendance)){
                        m.put(email,"P");

                    }

                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
        CollectionReference user_absent=db.collection("student_details");

        user_absent.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(TAG, document.getId() + " => " + document.getData());
                    String email= (String) document.get("email");
                    if(!m.containsKey(email)){
                        m.put(email,"A");
                    }


                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });

        userList.addAll(m.keySet());
        attendList.addAll(m.values());
        for(int i=0;i<attendList.size();i++){
            Log.d("imad", attendList.get(0));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View v) {


//        String date=java.time.LocalDate.now().toString();
//
//        CollectionReference user=db.collection(date);
//        user.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
////                            Log.d(TAG, document.getId() + " => " + document.getData());
//                    String email= (String) document.get("email");
//                    Boolean attendance= document.getBoolean(
//                            "attendance taken"
//                    );
//                    if(Boolean.TRUE.equals(attendance)){
//                        m.put(email,"P");
//
//                    }
//
//                }
//            } else {
//                Log.d(TAG, "Error getting documents: ", task.getException());
//            }
//        });
//        CollectionReference user_absent=db.collection("student_details");
//
//        user_absent.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
////                            Log.d(TAG, document.getId() + " => " + document.getData());
//                    String email= (String) document.get("email");
//                    if(!m.containsKey(email)){
//                        m.put(email,"A");
//                    }
//
//
//                }
//            } else {
//                Log.d(TAG, "Error getting documents: ", task.getException());
//            }
//        });
//
//        userList.addAll(m.keySet());
//        attendList.addAll(m.values());
//        for(int i=0;i<attendList.size();i++){
//            if(Objects.equals(attendList.get(i), "P")){
//
//                present_count++;
//            }else{
//                absent_count++;
//            }
//        }

        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();



        //Now we are creating sheet
        Sheet sheet=null;
        sheet = wb.createSheet("Nameofsheet");
        //Now column and row
        Row row =sheet.createRow(0);

        cell= (Cell) row.createCell(0);

        ((Cell) cell).setCellValue("Roll No");
        ((Cell) cell).setCellStyle(cellStyle);

        cell= (Cell) row.createCell(1);
        ((Cell) cell).setCellValue("Attendance");
        ((Cell) cell).setCellStyle(cellStyle);

        sheet.setColumnWidth(0,(20*200));
        sheet.setColumnWidth(1,(20*200));

        for (int i = 0; i < userList.size(); i++) {
            Row row1 = sheet.createRow(i + 1);

            cell = (Cell) row1.createCell(0);
            ((Cell) cell).setCellValue(userList.get(i));

            cell = (Cell) row1.createCell(1);
            ((HSSFCell) cell).setCellValue(attendList.get(i));
            //  cell.setCellStyle(cellStyle);


            sheet.setColumnWidth(0,(20*200));
            sheet.setColumnWidth(1,(20*200));


        }

        file = new File(getExternalFilesDir(null),System.currentTimeMillis() +".xls");
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            Toast.makeText(getApplicationContext(),"OK\n"+file,Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"NO OK",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // EMAIL

        Sendmail  sm= new Sendmail();
        sm.execute();


    }
    public class Sendmail extends AsyncTask {
        private Session session;
        private ProgressDialog progressDialog;

        @Override
        protected Object doInBackground(Object[] objects) {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("20ucs072@lnmiit.ac.in","gautam@kr" );

                }
            });


            //Step 2 : compose the message [text,multi media]

            Log.d("Test","here");
            try {
                MimeMessage msg = new MimeMessage(session);
                Log.d("Test","here1");
                msg.setFrom(new InternetAddress("20ucs072@lnmiit.ac.in"));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress("20ucs129@lnmiit.ac.in"));
                msg.setSubject("Attendance"); Log.d("Test","here2");

                Multipart emailContent = new MimeMultipart();

                //Text body part
                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText("Today attendance of students are attached below:");
                Log.d("Test","here3");
                //Attachment body part.
                MimeBodyPart pdfAttachment = new MimeBodyPart();


                pdfAttachment.attachFile(file);
                Log.d("Test","here4");
                //Attach body parts
                emailContent.addBodyPart(textBodyPart);
                emailContent.addBodyPart(pdfAttachment);

                Log.d("Test","here5");
                msg.setContent(emailContent);
                Log.d("Test","here6");

                Transport.send(msg);
                Log.d("Test","here7");
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
                Log.d("Test","here8");
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}