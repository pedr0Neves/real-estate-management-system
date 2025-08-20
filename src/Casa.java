package Imobiliaria;

import java.lang.Comparable;

class Casa implements Comparable<Casa> {
    /* Atributos */
    private int endereco;
    private int comodos;
    private float aluguel;
    private String rua;
    private String dono;
    private boolean garage;
    private boolean quintal;

    /* Métodos */
    Casa(int endereco, String rua, int comodos, boolean garage, boolean quintal) {
        this.endereco = endereco;
        this.rua = rua;
        this.comodos = comodos;
        this.garage = garage;
        this.quintal = quintal;
        dono = "imobiliaria";
    }

    //Setters
    void setEndereco(int endereco) { this.endereco = endereco; }
    void setComodos(int comodos) { this.comodos = comodos; }
    void setAluguel(float aluguel) { this.aluguel = aluguel; }
    void setRua(String rua) { this.rua = rua; }
    void setDono(String dono) { this.dono = dono; }
    void setGarage(Boolean garage) { this. garage = garage; }
    void setQuintal(boolean quintal) { this.quintal = quintal; }

    //Getter
    int getEndereco() { return this.endereco; }
    int getComodos() { return comodos; }
    float getAluguel() { return aluguel; }
    String getRua() { return this.rua; }
    String getDono() { return this.dono; }
    boolean getGarage() { return garage; }
    boolean getQuintal() { return quintal; }

    int saleValue() {
        int value = getComodos()*10000;
        if(getGarage()) { value += 2500; }
        if(getQuintal()) { value += 1000; }
        return value;
    }

    float rentValue() {
        float value = saleValue()*(float)0.02;
        this.aluguel = value;
        return value;
    }

    @Override
    public String toString() {
        String str = "rua "+getRua() + ", " + "endereço "+getEndereco() + "\ncomodos: " + getComodos() + ", ";
        if(getGarage()) { str += "possui garagem, "; }
        else { str += "nao possui garagem, "; }
        if(getQuintal()) { str += "possui quintal. \n"; }
        else { str += "nao possui quintal. \n"; }
        return str;
    }

    @Override
    public int compareTo(Casa o) {
        return this.comodos - o.comodos;
    }
}
