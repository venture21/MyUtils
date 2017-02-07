package com.venture.android.myutils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */

public class TwoFragment extends Fragment {
//public class TwoFragment extends Fragment implements View.OnClickListener {
    /*
    Button btnLength, btnArea, btnWeight;                   // Button 3ea
    LinearLayout layoutLength, layoutArea, layoutWeight;    // Layout 3ea
    Spinner spLength1, spLength2;
    Spinner spArea1, spArea2;
    Spinner spWeight1, spWeight2;
    EditText etLength;
    EditText etArea;
    EditText etWeight;
    int lengthUnitNum1, lengthUnitNum2;
    int areaUnitNum1, areaUnitNum2;
    int weightUnitNum1, weightUnitNum2;

    ArrayList<String> lengthUnit = new ArrayList<>();
    ArrayList<String> areaUnit  = new ArrayList<>();
    ArrayList<String> weightUnit = new ArrayList<>();
*/
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_two, container, false);
        return inflater.inflate(R.layout.fragment_two, container, false);
/*
        btnLength = (Button)  view.findViewById(R.id.btnLength);
        btnArea   = (Button)  view.findViewById(R.id.btnArea);
        btnWeight = (Button)  view.findViewById(R.id.btnWeight);
        spLength1 = (Spinner) view.findViewById(R.id.spLength1);
        spLength2 = (Spinner) view.findViewById(R.id.spLength2);
        spArea1   = (Spinner) view.findViewById(R.id.spArea1);
        spArea2   = (Spinner) view.findViewById(R.id.spArea2);
        spWeight1 = (Spinner) view.findViewById(R.id.spWeight1);
        spWeight2 = (Spinner) view.findViewById(R.id.spWeight2);
        etLength  = (EditText)view.findViewById(R.id.etLengthIn);
        etArea    = (EditText)view.findViewById(R.id.etAreaIn);
        etWeight  = (EditText)view.findViewById(R.id.etWeightIn);

        // Layout 3ea (For visablity, unvisability)
        layoutLength = (LinearLayout) view.findViewById(R.id.layoutLength);
        layoutArea   = (LinearLayout) view.findViewById(R.id.layoutArea);
        layoutWeight = (LinearLayout) view.findViewById(R.id.layoutWeight);

        btnLength.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnWeight.setOnClickListener(this);
        etLength.setOnClickListener(this);
        etArea.setOnClickListener(this);
        etWeight.setOnClickListener(this);

        makeSpinnerData();
        // 3.1 Spinner Data 아답터로 생성
        // length spinner
        ArrayAdapter<String> length_adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_dropdown_item, lengthUnit
                // 컨텍스트              스피너에서 사용할 레이아웃          배열데이터
        );
        // area spinner
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_dropdown_item, areaUnit
                // 컨텍스트              스피너에서 사용할 레이아웃          배열데이터
        );
        // weight spinner
        ArrayAdapter<String> weight_adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_dropdown_item, weightUnit
                // 컨텍스트              스피너에서 사용할 레이아웃          배열데이터
        );

        // 3.2 spinner 아답터 등록
        spLength1.setAdapter(length_adapter);
        spLength2.setAdapter(length_adapter);
        spArea1.setAdapter(area_adapter);
        spArea2.setAdapter(area_adapter);
        spWeight1.setAdapter(weight_adapter);
        spWeight2.setAdapter(weight_adapter);

        // 3.3 스피너 리스너에 등록
        // length spinner 1
        OnItemSelectedListener Length1SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lengthUnitNum1 = Integer.parseInt(lengthUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spLength1.setOnItemSelectedListener(Length1SelectedListener);

        // length spinner 2
        OnItemSelectedListener Length2SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lengthUnitNum2 = Integer.parseInt(lengthUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spLength2.setOnItemSelectedListener(Length2SelectedListener);

        // area spinner 1
        OnItemSelectedListener Area1SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaUnitNum1 = Integer.parseInt(areaUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spArea1.setOnItemSelectedListener(Area1SelectedListener);

        // area spinner 2
        OnItemSelectedListener Area2SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaUnitNum2 = Integer.parseInt(areaUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spArea2.setOnItemSelectedListener(Area2SelectedListener);

        // weight spinner 1
        OnItemSelectedListener Weight1SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weightUnitNum1 = Integer.parseInt(weightUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spWeight1.setOnItemSelectedListener(Weight1SelectedListener);

        // weight spinner 2
        OnItemSelectedListener Weight2SelectedListener = new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weightUnitNum2 = Integer.parseInt(weightUnit.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spWeight2.setOnItemSelectedListener(Weight2SelectedListener);


        return view;
        */
    }
/*
    @Override
    public void onClick(View view) {
        // 클릭이 일어나면 모두 지운다.
        layoutLength.setVisibility(View.GONE);
        layoutArea.setVisibility(View.GONE);
        layoutWeight.setVisibility(View.GONE);

        // 클릭된 버튼에 해당하는 레이아웃만 보여준다.
        switch (view.getId()) {
            case R.id.btnLength:
                layoutLength.setVisibility(View.VISIBLE);
                break;
            case R.id.btnArea:
                layoutArea.setVisibility(View.VISIBLE);
                break;
            case R.id.btnWeight:
                layoutWeight.setVisibility(View.VISIBLE);
                break;
            case R.id.etLengthIn:
                System.out.println("length value = " + etLength.getText());
                convLength(lengthUnitNum1, lengthUnitNum2);
                //Toast.makeText(this, "EditText:"+etLength.getText().toString(), Toast.LENGTH_SHORT);
                break;
            case R.id.etAreaIn:
                //Toast.makeText(this, "EditText:"+etArea.getText().toString(), Toast.LENGTH_SHORT);
                break;
            case R.id.etWeightIn:
                //Toast.makeText(this, "EditText:"+etWeight.getText().toString(), Toast.LENGTH_SHORT);
                break;
        }
    }

    private void makeSpinnerData() {
        int index;

        // length
//        lengthUnit.add("mm");
//        lengthUnit.add("cm");
//        lengthUnit.add("m");
//        lengthUnit.add("km");
//        lengthUnit.add("in");
//        lengthUnit.add("ft");

        // area
        areaUnit.add("m2");
        areaUnit.add("a");
        areaUnit.add("ha");
        areaUnit.add("km2");
        areaUnit.add("ft2");
        areaUnit.add("yd2");

        // weight
        weightUnit.add("mg");
        weightUnit.add("g");
        weightUnit.add("kg");
        weightUnit.add("t");
        weightUnit.add("kt");
        weightUnit.add("gr");
    }

    public void convLength(int unit1, int unit2) {
        switch(unit1) {
            case 1:
                System.out.println("mm");
                break;
            case 2:
                System.out.println("cm");
                break;
            case 3:
                System.out.println("m");
                break;
            case 4:
                System.out.println("km");
                break;
            case 5:
                System.out.println("in");
                break;
            case 6:
                System.out.println("ft");
                break;
        }
    }
    */
}
