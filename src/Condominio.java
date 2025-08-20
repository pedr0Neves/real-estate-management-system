package Imobiliaria;

import java.util.List;
import java.util.ArrayList;

class Condominio implements Comparable<Condominio>{
    /* Atributos */
    private String name;
    private String rua;
    private int numMaxHouse;
    private List<Casa> condominiosHouse;
    
    /* Métodos */
    Condominio(String name, String rua, int numMaxHouse) {
        this.name = name;
        this.rua = rua;
        this.numMaxHouse = numMaxHouse;
        condominiosHouse = new ArrayList<Casa>();
    }

    //Setters
    void setName(String name) { this.name = name; } 
    void setRua(String rua) { this.rua = rua; }
    void setNumMaxHouse(int numMaxHouse) { this.numMaxHouse = numMaxHouse; }
    void setCondominiosHouse(List<Casa> condominiosHouse) {
        this.condominiosHouse.clear();
        for(int i = 0; i < condominiosHouse.size(); i++) { this.condominiosHouse.add(condominiosHouse.get(i)); }
    }

    //Getters
    String getNome() { return this.name;  }
    String getRua() { return this.rua; }
    int getNumMaxHouse() { return this.numMaxHouse; }
    List<Casa> getCondominioHouse() { return this.condominiosHouse; }

    /**
     * Compara os atributos de um condominio com outro objeto condominio
     * @param Condominio condominio a ser comparada
     * @return true ou false
     */
    boolean condominioEqual(Condominio condominio) {
        if(getNome() == condominio.getNome() && getRua() == condominio.getRua() &&
           getNumMaxHouse() == condominio.getNumMaxHouse()) {
        return true;
        }
        return false;
    }

    /**
     * Adiciona uma casa no condominio, se a casa estiver dentro dos limites, cria uma casa
     * e adiciona na lista de casas do condominio
     * @param num numero da casa
     * @param dono dono da casa;
     * @return true ou falso
     */
    boolean addCasaCondominio(int num, String dono) {
        if(verifyCasa(num)) {
            Casa casa = new Casa(num, "rua condominio " + getNome(), 5, true, true);
            casa.setDono(dono);
            condominiosHouse.add(casa);
            return true;
        }
        return false;
    }

    void rmCasaCondominio(Casa casa) {
        if(verifyCasa(casa.getEndereco())) {
            condominiosHouse.remove(casa);
        }
        else {
            //error
        }
    }

    /**
     * Verifica se a casa está dentro do aceitável, compara o numero dela
     * @param num numero da casa
     * @return true ou false
     */
    boolean verifyCasa(int num) {
        if(num >= 0 && num <= getNumMaxHouse()) { return true; }
        return false;
    }

    @Override
    public String toString() {
        String str = new String();
        for(int i = 0; i < condominiosHouse.size(); i++) {
            str += "- casa " + (i+1) + "\n";
            str += condominiosHouse.get(i);
            str += "valor de venda: " + condominiosHouse.get(i).saleValue() + "\n";
            str += "valor do aluguel: " + condominiosHouse.get(i).rentValue() + "\n\n";
        }
        if(condominiosHouse.size() == 0) {
            str += "nenhum. \n";
        }
        return str;
    }

    @Override
    public int compareTo(Condominio o) {
        return name.length() - o.name.length();
    }
}