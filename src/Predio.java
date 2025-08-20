package Imobiliaria;

import java.util.List;
import java.util.ArrayList;

class Predio implements Comparable<Predio>{
    /* Atributos */
    private String rua;
    private String dono;
    private String namePredio;
    private int endereco;
    private int maxAndar;
    private int numMaxApart;
    private boolean elevator;
    private boolean pool;
    private boolean garage;
    private List<Apartamento> apartamento;

    /* Métodos */
    Predio(int endereco, String rua, boolean elevator, boolean pool, boolean garage, int maxAndar, int numMaxApart, String namePredio) {
        this.endereco = endereco;
        this.rua = rua;
        this.elevator = elevator;
        this.pool = pool;
        this.garage = garage;
        this.maxAndar = maxAndar;
        this.numMaxApart = numMaxApart;
        this.namePredio = namePredio;
        apartamento = new ArrayList<Apartamento>();
    }
        
    //Setters
    void setRua(String rua)               { this.rua = rua; }
    void setDono(String dono)             { this.dono = dono; }
    void setNamePredio(String namePredio) { this.namePredio = namePredio; }
    void setEndereco(int endereco)        { this.endereco = endereco; }
    void setMaxAndar(int maxAndar)        { this.maxAndar = maxAndar; }
    void setNumMaxApart(int numMaxApart)  { this.numMaxApart = numMaxApart; }
    void setElevator(boolean elevator)    { this.elevator = elevator; }
    void setPool(boolean pool)            { this.pool = pool; }
    void setGarage(boolean garage)        { this.garage = garage; }
    void setApartamento(List<Apartamento> apartamento) {
        clearApartamento();
        for(int i = 0; i < apartamento.size(); i++) { this.apartamento.add(apartamento.get(i)); }
    }

    //Getters
    String getRua()                    { return this.rua; }
    String getDono()                   { return this.dono; }
    String getNamePredio()             { return namePredio; }
    int getEndereco()                  { return this.endereco; }
    int getMaxAndar()                  { return this.maxAndar; }
    int getNumMaxApart()               { return this.numMaxApart; }
    boolean getElevator()              { return this.elevator; }
    boolean getPool()                  { return this.pool; }
    boolean getGarage()                { return this.garage; }
    List<Apartamento> getApartamento() { return apartamento; }
    
    /**
     * Método para limpar uma lista de apartamentos
     */
    void clearApartamento() { apartamento.clear(); }

    /**
     * Compara os atributos de um predio com outro objeto predio
     * @param Predio predio a ser comparado
     * @return true ou false
     */
    boolean Equal(Predio Predio) {
        if(getElevator() == Predio.getElevator() && getGarage() == Predio.getGarage() &&
           getPool() == Predio.getPool() && getMaxAndar() == Predio.getMaxAndar() &&
           getNumMaxApart() == Predio.getNumMaxApart() && getNamePredio() == Predio.getNamePredio()) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se um apartamento pode estar dentro dos andares e num de apartamentos do predio
     * @param Apart apartamentamento a ser verificado
     * @return true ou false
     */
    boolean verifyApartamento(Apartamento Apart) {
        if(Apart.getNumAndar() >= 0 && Apart.getNumAndar() < getMaxAndar() + 1) { return true; }
        else if(Apart.getNumApart() >= 0 && Apart.getNumApart() < getNumMaxApart() + 1) { return true; }
        return false;
    }

    boolean addSellApart(Apartamento Apart) {
        if(verifyApartamento(Apart)) { 
            apartamento.add(Apart);
            return true;
        }
        //erro
        return false;
    }

    void rmApart(Apartamento Apart) {
        if(verifyApartamento(Apart)) {
            apartamento.remove(Apart);
        }
        else {
            //error
        }
    }

    /**
     * Retornao valor de venda de um apartamento do prédio, esse depende 
     * do número maximo de apartamentos do prédio, e se contém ou não garagem, piscina ou elevador
     * também é adiciona 250 para cada andar que o apartamento está localizado
     * @param Apart
     * @return
     */
    int saleValue(Apartamento Apart) {
        int value = 1000*(getMaxAndar()*getNumMaxApart());
        if(getElevator()) { value += 500; }
        if(getPool()) { value += 400; }
        if(getGarage()) { value += 1000; }
        value += 250*Apart.getNumAndar() + 250;
        return value;
    }

    /**
     * O valor de aluguel de um apartamento
     * @param Apart
     */
    void rentValue(Apartamento Apart) {
        float aluguel = saleValue(Apart)*(float)0.02;
        Apart.setAluguel(aluguel);
    }

    @Override
    public String toString() {
        String str = new String();
        for(int i = 0; i < apartamento.size(); i++) {
            str += "- Apartamento " + (i+1) + "\n";
            str += apartamento.get(i) + "\n";
            str += "valor de venda: " + saleValue(apartamento.get(i)) + "\n";
            rentValue(apartamento.get(i));
            str += "valor do aluguel: " + apartamento.get(i).getAluguel() + "\n";
        }
        if(apartamento.size()==0) {
            str += "nenhum. \n";
        }
        return str;
    }

    @Override
    public int compareTo(Predio o) {
        return namePredio.length() - o.namePredio.length();
    }
}
