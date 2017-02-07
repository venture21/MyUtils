package com.venture.android.myutils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements View.OnClickListener {

    Boolean debug=false;
    Button btnNum0, btnNum1, btnNum2, btnNum3;
    Button btnNum4, btnNum5, btnNum6, btnNum7;
    Button btnNum8, btnNum9, btnPlus, btnMinus;
    Button btnMul,  btnDiv,  btnAc,   btnRun;
    TextView preview, result;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        btnNum0  = (Button) view.findViewById(R.id.btn0);
        btnNum1  = (Button) view.findViewById(R.id.btn1);
        btnNum2  = (Button) view.findViewById(R.id.btn2);
        btnNum3  = (Button) view.findViewById(R.id.btn3);
        btnNum4  = (Button) view.findViewById(R.id.btn4);
        btnNum5  = (Button) view.findViewById(R.id.btn5);
        btnNum6  = (Button) view.findViewById(R.id.btn6);
        btnNum7  = (Button) view.findViewById(R.id.btn7);
        btnNum8  = (Button) view.findViewById(R.id.btn8);
        btnNum9  = (Button) view.findViewById(R.id.btn9);
        btnPlus  = (Button) view.findViewById(R.id.btnplus);
        btnMinus = (Button) view.findViewById(R.id.btnminus);
        btnMul   = (Button) view.findViewById(R.id.btnmul);
        btnDiv   = (Button) view.findViewById(R.id.btndiv);
        btnAc    = (Button) view.findViewById(R.id.btncancel);
        btnRun   = (Button) view.findViewById(R.id.btnrun);
        preview  = (TextView) view.findViewById(R.id.preview);
        result   = (TextView) view.findViewById(R.id.result);

        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnAc.setOnClickListener(this);
        btnRun.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if(preview.getText()=="0"){
            preview.setText("");
        }

        switch(view.getId()){
            case R.id.btn0:      preview.setText(preview.getText() +"0");    break;
            case R.id.btn1:      preview.setText(preview.getText() +"1");    break;
            case R.id.btn2:      preview.setText(preview.getText() +"2");    break;
            case R.id.btn3:      preview.setText(preview.getText() +"3");    break;
            case R.id.btn4:      preview.setText(preview.getText() +"4");    break;
            case R.id.btn5:      preview.setText(preview.getText() +"5");    break;
            case R.id.btn6:      preview.setText(preview.getText() +"6");    break;
            case R.id.btn7:      preview.setText(preview.getText() +"7");    break;
            case R.id.btn8:      preview.setText(preview.getText() +"8");    break;
            case R.id.btn9:      preview.setText(preview.getText() +"9");    break;
            case R.id.btnplus:   preview.setText(preview.getText() +"+");    break;
            case R.id.btnminus:  preview.setText(preview.getText() +"-");    break;
            case R.id.btnmul:    preview.setText(preview.getText() +"*");    break;
            case R.id.btndiv:    preview.setText(preview.getText() +"/");    break;
            case R.id.btnrun:    preview.setText(preview.getText() +"=");
                                 Calculator();                               break;
            case R.id.btncancel: preview.setText("0");                       break;
        }
    }


    private void Calculator() {
        int inc = 0;
        String opData[] =new String[15];

        String tString = (String) preview.getText();
        if(debug) {

            System.out.println(tString);
        }

        char arrs[] = tString.toCharArray();
        int strStart=0, strEnd=0;
        int opCount=0;
        ArrayList<String> list = new ArrayList<>();

        for(inc=0;inc<tString.length();inc++) {
            if( (arrs[inc]=='+') | (arrs[inc]=='-') | (arrs[inc]=='*') | (arrs[inc]=='/') |(arrs[inc]=='=')) {
                strEnd = inc;
                opData[opCount] = tString.substring(strStart, strEnd);
                list.add(opData[opCount]);
                opCount++;
                if (arrs[inc] == '+')       list.add("+");
                else if (arrs[inc] == '-')  list.add("-");
                else if (arrs[inc] == '*')  list.add("*");
                else if (arrs[inc] == '/')  list.add("/");

                if (arrs[inc] != '=') {
                    strStart=inc+1;
                    opCount++;
                }
            }
        }

        boolean opCal = false;
        double temp1=0;
        double temp2=0;
        double sum=0;

        // * 또는 / 연산 우선
        for(inc=0;inc<list.size(); ) {
            String item = list.get(inc);

            if(item.equals("*")) {
                temp1 = Double.parseDouble(list.get(inc-1));
                temp2 = Double.parseDouble(list.get(inc+1));
                sum   = temp1 * temp2;
                opCal = true;
            } else if(item.equals("/")) {
                temp1 = Double.parseDouble(list.get(inc - 1));
                temp2 = Double.parseDouble(list.get(inc + 1));
                sum = temp1 / temp2;
                opCal = true;
            } else {
                opCal = false;
            }

            if(opCal) {
                list.set(inc, sum+"");
                list.remove(inc+1);
                list.remove(inc-1);
            } else {
                inc++;
            }

            if(debug) {

                for (Object str : list) {
                    System.out.print(str);
                }
                System.out.println("");
                System.out.println(sum);
            }
        }

        // + 또는 - 연산 우선
        for(inc=0;inc<list.size(); ) {
            String item = list.get(inc);

            if(item.equals("+")) {
                temp1 = Double.parseDouble(list.get(inc-1));
                temp2 = Double.parseDouble(list.get(inc+1));
                sum   = temp1 + temp2;
                opCal = true;
            } else if(item.equals("-")) {
                temp1 = Double.parseDouble(list.get(inc - 1));
                temp2 = Double.parseDouble(list.get(inc + 1));
                sum = temp1 - temp2;
                opCal = true;
            } else {
                opCal = false;
            }

            if(opCal) {
                list.set(inc, sum+"");
                list.remove(inc+1);
                list.remove(inc-1);
            } else {
                inc++;
            }

            if(debug) {

                for (Object str : list) {
                    System.out.print(str);
                }
                System.out.println("");
                System.out.println(sum);
            }
        }
        result.setText(sum+"");
    }
}