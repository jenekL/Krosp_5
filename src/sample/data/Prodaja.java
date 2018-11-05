package sample.data;


import java.sql.Date;

public class Prodaja implements TableData {
    private int id;
    private String date;
    private int quantity;
    private Klients klients;
    private Zakupka zakupka;

    public Prodaja(int id, String date, int quantity, Klients klients, Zakupka zakupka) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.klients = klients;
        this.zakupka = zakupka;
    }

    public String getFirm() {
        return klients.getFirm();
    }

    public String getName() {
        return zakupka.getName();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Zakupka getZakupka() {
        return zakupka;
    }

    public void setZakupka(Zakupka zakupka) {
        this.zakupka = zakupka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klients getKlients() {
        return klients;
    }

    public void setKlients(Klients klients) {
        this.klients = klients;
    }
}
