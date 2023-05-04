package com.example.lastwork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;

public class AddClassFragment extends Fragment {
private EditText studentClass;
private EditText studentName;
private EditText studentNum;
private EditText className;
private EditText classPoint ;
private Button classBtn ;

public void init(View view ){
    studentClass = view.findViewById(R.id.studentClass) ;
    studentName = view.findViewById(R.id.studentName) ;
    studentNum = view.findViewById(R.id.studentNum) ;
    className = view.findViewById(R.id.className) ;
    classPoint = view.findViewById(R.id.classPoint) ;
    classBtn = view.findViewById(R.id.studentBtn) ;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_class, container, false);
        init(view);


        classBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = studentClass.getText().toString() ;
                String s2 = studentName.getText().toString() ;
                String s3 = studentNum.getText().toString() ;
                String s4 = className.getText().toString() ;
                String s5 = classPoint.getText().toString() ;

                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getActivity(),"请完善信息！",Toast.LENGTH_SHORT).show();
                }else{
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            try {
                                SUDI.insert("user",s1,s2,s3,s4,s5) ;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    studentClass.setText("");
                    studentName.setText("");
                    studentNum.setText("");
                    className.setText("");
                    classPoint.setText("");
                    Toast.makeText(getActivity(),"成绩录入成功！",Toast.LENGTH_SHORT).show();;
                }
            }
        });
        return view  ;

    }
}