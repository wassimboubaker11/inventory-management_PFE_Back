package com.GestionDeStock.Entity;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import java.math.BigInteger;
import java.text.DecimalFormat;

import java.time.LocalDate;

import java.util.regex.Pattern;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Facture")
public class Facture implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idFacture")
    private int idFacture;

    private LocalDate dateFacture;

    private double tva;

    private String reference;

    private double ttc;

    private double ht;

    private String totalLettre;





    @OneToOne(mappedBy = "facture" )
    private Commande commande;



    private static final String PREFIX = "DEV-";
    private static final int ID_LENGTH = 5;
    private static int lastId = 0;


    public synchronized String generateID() {

        lastId++;
        String id = PREFIX + String.format("%0" + ID_LENGTH + "d", lastId);
        return id;
    }
    public double calculTotal(double ht, double TVA) {
        double ttc= ht*(1+(TVA/100));
        return ttc;
    }
    public String totalEnLettre(double total){
        DecimalFormat DFORMAT = new DecimalFormat("###0.000");
        NumberFormat FORMATTER = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        String[] s = DFORMAT.format(total).split(Pattern.quote(String.valueOf(DFORMAT.getDecimalFormatSymbols().getDecimalSeparator())));
        BigInteger intPart = new BigInteger(s[0]);
        if ( s.length==1 ) {
            return FORMATTER.format(intPart);
        }
        else {
            BigInteger decPart = new BigInteger(s[1]);
            return FORMATTER.format(intPart)
                    // pour les parties fixes il faudrait faire un resourcebundle
                    + " Dinar"
                    + (intPart.intValue()>1?"s":"")
                    + " et "
                    + FORMATTER.format(decPart)
                    + " millim"
                    + (decPart.intValue()>1?"s":"");
        }
    }






}
