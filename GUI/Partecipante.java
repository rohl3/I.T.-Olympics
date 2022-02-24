package com.GUI;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Partecipante {
    private String codFis;
    private String nominativo;
    private String classe;
    private double punteggio;

    public Partecipante(String codFis, String nominativo, String classe, double punteggio) throws Exception {
        setCodFis(codFis);
        setNominativo(nominativo);
        setClasse(classe);
        setPuunteggio(punteggio);
    }

    public String getCodFis() {
        return codFis;
    }

    public final void setCodFis(@NotNull String codFis) throws Exception{
        if(codFis.length() != 16) throw new Exception("Codice Fiscale Invalido");
        else
            this.codFis = codFis;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) throws Exception{
        if(nominativo == null) throw new Exception("Nominativo Invalido");
        else
            this.nominativo = nominativo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(@NotNull String classe) throws Exception {
        if(classe.equalsIgnoreCase("prima") || classe.equalsIgnoreCase("seconda") || classe.equalsIgnoreCase("terza") || classe.equalsIgnoreCase("quarta")){
            this.classe = classe;
        }
        else
            throw new Exception("Class non esiste");
    }

    public double getPunteggio() {
        return punteggio;
    }

    public void setPuunteggio(double punteggio) throws Exception {
        if(punteggio <-30 && punteggio > 30) throw new Exception("Punteggio Invalido");
        else
            this.punteggio = punteggio;
    }


    public static Comparator<Partecipante> sortPoint = (o1, o2) -> (int) (o2.getPunteggio() - o1.getPunteggio());

    @Override
    public String toString() {
        return "Partecipante{" +
                "codFis='" + codFis + '\'' +
                ", nominativo='" + nominativo + '\'' +
                ", classe='" + classe + '\'' +
                ", punteggio=" + punteggio +
                '}';
    }
}
