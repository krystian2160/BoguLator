package com.example.a219.bogulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BoguLator extends AppCompatActivity {

    double pamiec;
    char operacja;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bogu_lator);

        text = findViewById(R.id.text);
    }

    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.zero:
                apendzik(0);
                break;
            case R.id.one:
                apendzik(1);
                break;
            case R.id.two:
                apendzik(2);
                break;
            case R.id.three:
                apendzik(3);
                break;
            case R.id.four:
                apendzik(4);
                break;
            case R.id.five:
                apendzik(5);
                break;
            case R.id.six:
                apendzik(6);
                break;
            case R.id.seven:
                apendzik(7);
                break;
            case R.id.eight:
                apendzik(8);
                break;
            case R.id.nine:
                apendzik(9);
                break;
            case R.id.negation:
                text.setText(String.valueOf(pobierzWartosc()*-1));
                break;
            case R.id.plus:
                ustawOperacje('+');
                break;
            case R.id.minus:
                ustawOperacje('-');
                break;
            case R.id.equal:
                wykonajDzialanie();
                break;
            case R.id.multiply:
                ustawOperacje('*');
                break;
            case R.id.divide:
                ustawOperacje('/');
                break;
            case R.id.back:
                text.setText(text.getText().toString().substring(0, text.getText().toString().length()-1));
                if(text.getText().length() == 0 ) text.setText("0");
                break;
            case R.id.d2:
                text.setText(String.valueOf(pobierzWartosc() / (double)2));
                break;
            case R.id.s2:
                text.setText(String.valueOf(pobierzWartosc()*pobierzWartosc()));
                break;
            case R.id.dot:
                int dots = getDotsCount();
                if(dots == 0) {
                    text.append(".");
                }
                break;
            case R.id.ce:
                text.setText("0");
                break;
        }
    }

    private int getDotsCount() {
        return text.length() - String.valueOf(text.getText()).replace(".", "").length();
    }

    private void apendzik(int i) {
        if(Double.parseDouble(text.getText().toString()) == 0) {
            text.setText(String.valueOf(i));
        } else {
            text.append(String.valueOf(i));
        }
    }

    private void ustawOperacje(char znakOperacji) {
        pamiec = Double.parseDouble(text.getText().toString());
        operacja = znakOperacji;
        text.setText("0");
    }

    private double pobierzWartosc()
    {
        return Double.parseDouble(text.getText().toString());
    }

    public void wykonajDzialanie() {
        double wynik = 0;

        switch (operacja) {
            case '+':
                wynik = pamiec + pobierzWartosc();
                break;
            case '-':
                wynik = pamiec - pobierzWartosc();
                break;
            case '*':
                wynik = pamiec * pobierzWartosc();
                break;
            case '/':
                if(pobierzWartosc() == 0) {
                    wynik = 0;
                }
                wynik = pamiec / (double)pobierzWartosc();
                break;
        }
        text.setText(String.valueOf(wynik));
            }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("pamiec",pamiec);
        outState.putChar("operacja",operacja);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pamiec = savedInstanceState.getDouble("pamiec");
        operacja = savedInstanceState.getChar("operacja");
    }
}