package br.edu.unifcv.calculadora;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_zero;
    private Button bt_um;
    private Button bt_dois;
    private Button bt_tres;
    private Button bt_quatro;
    private Button bt_cinco;
    private Button bt_seis;
    private Button bt_sete;
    private Button bt_oito;
    private Button bt_nove;
    private Button bt_virg;
    private Button bt_soma;
    private Button bt_subt;
    private Button bt_divi;
    private Button bt_mult;
    private Button bt_limpa;
    private Button bt_igual;
    private EditText edt_visor;
    private Boolean bOperador;
    private Float valor1,valor2;
    private String operador;
    private Integer idxValor;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_zero = findViewById(R.id._0);
        bt_um = findViewById(R.id._1);
        bt_dois = findViewById(R.id._2);
        bt_tres = findViewById(R.id._3);
        bt_quatro = findViewById(R.id._4);
        bt_cinco = findViewById(R.id._5);
        bt_seis = findViewById(R.id._6);
        bt_sete = findViewById(R.id._7);
        bt_oito = findViewById(R.id._8);
        bt_nove = findViewById(R.id._9);
        bt_virg = findViewById(R.id.virg);
        bt_mult = findViewById(R.id.mult);
        bt_soma = findViewById(R.id.soma);
        bt_subt = findViewById(R.id.subt);
        bt_divi = findViewById(R.id.divi);
        bt_limpa = findViewById(R.id.limpa);
        bt_igual = findViewById(R.id.igual);
        edt_visor = findViewById(R.id.visor);

        bt_zero.setOnClickListener(this);
        bt_um.setOnClickListener(this);
        bt_dois.setOnClickListener(this);
        bt_tres.setOnClickListener(this);
        bt_quatro.setOnClickListener(this);
        bt_cinco.setOnClickListener(this);
        bt_seis.setOnClickListener(this);
        bt_sete.setOnClickListener(this);
        bt_oito.setOnClickListener(this);
        bt_nove.setOnClickListener(this);
        bt_virg.setOnClickListener(this);
        bt_mult.setOnClickListener(this);
        bt_soma.setOnClickListener(this);
        bt_subt.setOnClickListener(this);
        bt_divi.setOnClickListener(this);
        bt_limpa.setOnClickListener(this);
        bt_igual.setOnClickListener(this);
        bOperador = false;
        context = this;
    }

    public void Limpa(){
        edt_visor.setText("");
        bOperador = false;
        idxValor = null;
        valor1 = null;
        valor2 = null;
    }

    public void Calcula(String operador){
        switch (operador){
            case "+":
                edt_visor.setText(String.valueOf(valor1 + valor2));
                break;
            case "-":
                edt_visor.setText(String.valueOf(valor1 - valor2));
                break;
            case "/":
                if(valor2 == 0) {
                    Toast.makeText(context,"Impossível dividir por zero!",Toast.LENGTH_LONG).show();
                    Limpa();
                } else {
                    edt_visor.setText(String.valueOf(valor1 / valor2));
                }
                break;
            case "*":
                edt_visor.setText(String.valueOf(valor1 * valor2));
                break;
        }
        bOperador = false;
        idxValor = null;
        valor1 = null;
        valor2 = null;
    }

    public String valorBotao(int id){
        String valor;
        Button btn;

        btn = findViewById(id);
        valor = btn.getText().toString();
        if(btn == bt_limpa){
            Limpa();
            return "";
        }else if(btn == bt_igual){
            valor2 = Float.parseFloat(edt_visor.getText().toString().substring(idxValor,edt_visor.getText().length()));
            Calcula(operador);
            return "";
        }
        return valor;
    };

    public void montaOperacao(String valor){
        Boolean b_opr = false;

        switch (valor){
            case "+":
                b_opr = true;
                break;
            case "-":
                b_opr = true;
                break;
            case "/":
                b_opr = true;
                break;
            case "*":
                b_opr = true;
                break;
        }

        if(bOperador && b_opr) return;
        if(!bOperador) bOperador = b_opr;
        if (edt_visor.getText().toString().isEmpty()) {
            if(!b_opr && !valor.equals(".")) edt_visor.setText(valor);
        } else {
            if(b_opr){
                valor1 = Float.parseFloat(edt_visor.getText().toString());
                operador = valor;
            }
            edt_visor.setText(edt_visor.getText().toString() + valor);
        }

        if(valor1 != null && idxValor == null) idxValor = edt_visor.getText().length();
    }

    public void onClick(View v) {
        montaOperacao(valorBotao(v.getId()));
    }

}
