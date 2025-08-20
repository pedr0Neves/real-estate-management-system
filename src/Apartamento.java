package Imobiliaria;

class Apartamento implements Comparable<Apartamento>{
    /* atributos */
    private int numAprt;
    private int numAndar;
    private float aluguel;
    private String dono;

    /* métodos */
    Apartamento(int numApart, int numAndar) {
        this.numAndar = numAndar;
        this.numAprt = numApart;
        aluguel = 0;
        this.dono = "imobiliaria";
    }

    //getters
    int getNumApart() { return this.numAprt; }
    int getNumAndar() { return this.numAndar; }
    float getAluguel() { return this.aluguel; }
    String getDono() { return this.dono; }

    //Setters
    void setNumApart(int numApart) { this.numAprt = numApart; }
    void setNumAndar(int numAndar) { this.numAndar = numAndar; }
    void setAluguel(float aluguel) { this.aluguel = aluguel; }
    void setDono(String dono) { this.dono = dono; }

    void declararDono(String dono) {
        setDono(dono);
    }

    //@Override
    public String toString() {
        String str = new String();
        str = "Andar: " + numAndar + ", apt: " + (numAprt*100 + numAndar);
        return str;
    }

    @Override
    public int compareTo(Apartamento o) {
        return numAndar - o.numAndar;
    }
    
}
