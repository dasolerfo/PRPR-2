package com.example.pokedexls;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pokedexls.Entity.Trainer;
import com.example.pokedexls.Entity.TrainerUpdate;

import java.text.NumberFormat;
import java.util.Locale;


public class ShopFragment extends Fragment {

    private Button boto_mesP;
    private Button boto_menysP;
    private Button boto_mesS;
    private Button boto_menysS;
    private Button boto_mesU;
    private Button boto_menysU;
    private Button boto_mesM;
    private Button boto_menysM;
    private TextView textP;
    private TextView textS;
    private TextView textU;
    private TextView textM;
    private TextView cartera;
    private TextView totalCost;
    private ImageButton buy;
    private int totalPokes;
    private int totalSuper;
    private int totalUltra;
    private int totalMaster;
    private int totalPreu;

    private static final String ARG_TRAINER = "trainer";
    private Trainer trainer;
    private TrainerUpdate trainerUpdate;

    public ShopFragment() {
        totalPokes = 0;
        totalSuper = 0;
        totalUltra = 0;
        totalMaster = 0;
        totalPreu = 0;
    }

    public static ShopFragment newInstance(Trainer trainer) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRAINER, trainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainer = (Trainer) getArguments().getSerializable(ARG_TRAINER);
        trainerUpdate = (TrainerUpdate) getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        boto_mesP = (Button) view.findViewById(R.id.boto_mesP);
        boto_menysP = (Button) view.findViewById(R.id.boto_menysP);
        boto_mesS = (Button) view.findViewById(R.id.boto_mesS);
        boto_menysS = (Button) view.findViewById(R.id.boto_menysS);
        boto_mesU = (Button) view.findViewById(R.id.boto_mesU);
        boto_menysU = (Button) view.findViewById(R.id.boto_menysU);
        boto_mesM = (Button) view.findViewById(R.id.boto_mesM);
        boto_menysM = (Button) view.findViewById(R.id.boto_menysM);
        textP = (TextView) view.findViewById(R.id.textP);
        textS = (TextView) view.findViewById(R.id.textS);
        textU = (TextView) view.findViewById(R.id.textU);
        textM = (TextView) view.findViewById(R.id.textM);
        cartera = (TextView) view.findViewById(R.id.cartera);
        buy = (ImageButton) view.findViewById(R.id.buy);
        totalCost = (TextView) view.findViewById(R.id.quantitatVenta);

        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        String formattedTotalPreu = format.format(trainer.getMoney());
        cartera.setText(formattedTotalPreu+"€");

        boto_mesP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPokes++;
                actPreuTotal();
                textP.setText(totalPokes+"u");
            }
        });
        boto_menysP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPokes != 0) {
                    totalPokes--;
                }
                actPreuTotal();
                textP.setText(totalPokes+"u");
            }
        });

        boto_mesS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalSuper++;
                actPreuTotal();
                textS.setText(totalSuper+"u");
            }
        });
        boto_menysS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalSuper != 0) {
                    totalSuper--;
                }
                actPreuTotal();
                textS.setText(totalSuper+"u");
            }
        });

        boto_mesU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalUltra++;
                actPreuTotal();
                textU.setText(totalUltra+"u");
            }
        });
        boto_menysU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalUltra != 0) {
                    totalUltra--;
                }
                actPreuTotal();
                textU.setText(totalUltra+"u");
            }
        });

        boto_mesM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMaster++;
                actPreuTotal();
                textM.setText(totalMaster+"u");
            }
        });
        boto_menysM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalMaster != 0) {
                    totalMaster--;
                }
                actPreuTotal();
                textM.setText(totalMaster+"u");
            }
        });

        textP.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = textP.getText().toString();
                String[] aux = text.split("u");
                try {
                    int num = Integer.parseInt(aux[0]);
                    if (num < 0){
                        num = 0;
                    }
                    textP.setText(num+"u");
                    totalPokes = num;
                } catch (NumberFormatException e){
                    totalPokes = 0;
                    textP.setText(totalPokes+"u");
                }
                actPreuTotal();
                return false;
            }
        });

        textS.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = textS.getText().toString();
                String[] aux = text.split("u");
                try {
                    int num = Integer.parseInt(aux[0]);
                    if (num < 0){
                        num = 0;
                    }
                    textS.setText(num+"u");
                    totalSuper = num;
                } catch (NumberFormatException e){
                    totalSuper = 0;
                    textS.setText(totalSuper+"u");
                }
                actPreuTotal();
                return false;
            }
        });

        textU.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = textU.getText().toString();
                String[] aux = text.split("u");
                try {
                    int num = Integer.parseInt(aux[0]);
                    if (num < 0){
                        num = 0;
                    }
                    textU.setText(num+"u");
                    totalUltra = num;
                } catch (NumberFormatException e){
                    totalUltra = 0;
                    textU.setText(totalSuper+"u");
                }
                actPreuTotal();
                return false;
            }
        });

        textM.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = textM.getText().toString();
                String[] aux = text.split("u");
                try {
                    int num = Integer.parseInt(aux[0]);
                    if (num < 0){
                        num = 0;
                    }
                    textM.setText(num+"u");
                    totalMaster = num;
                } catch (NumberFormatException e){
                    totalMaster = 0;
                    textM.setText(totalMaster+"u");
                }
                actPreuTotal();
                return false;
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPreu != 0) {
                    if (trainer.getMoney() >= totalPreu) {
                        View layout = inflater.inflate(R.layout.toast_correcte, null);
                        Toast toast = new Toast(getActivity());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();

                        trainer.setMoney(trainer.getMoney()-totalPreu);
                        trainer.setNumPokeballs(trainer.getNumPokeballs()+totalPokes);
                        trainer.setNumSuperballs(trainer.getNumSuperballs()+totalSuper);
                        trainer.setNumUltraballs(trainer.getNumUltraballs()+totalUltra);
                        trainer.setNumMasterballs(trainer.getNumMasterballs()+totalMaster);
                        trainerUpdate.actualitzaTrainer(trainer);

                        finalitzaCompra();
                    } else {
                        View layout = inflater.inflate(R.layout.toast_incorrecte, null);
                        Toast toast = new Toast(getActivity());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    }
                }
            }
        });

        return view;
    }

    private void actPreuTotal(){
        totalPreu = totalPokes*200 + totalSuper*500 + totalUltra*1500 + totalMaster*100000;
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        String formattedTotalPreu = format.format(totalPreu);
        totalCost.setText(formattedTotalPreu+"€");
    }

    private void finalitzaCompra(){
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        String formattedTotalPreu = format.format(trainer.getMoney());
        cartera.setText(formattedTotalPreu+"€");

        totalPokes = 0;
        totalSuper = 0;
        totalUltra = 0;
        totalMaster = 0;
        totalPreu = 0;

        textP.setText(totalPokes+"u");
        textS.setText(totalSuper+"u");
        textU.setText(totalUltra+"u");
        textM.setText(totalMaster+"u");
        actPreuTotal();
    }
}