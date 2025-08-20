package Imobiliaria;

class Terreno implements Comparable<Terreno>{
    /* Atributos */
    private int endereco;
    private String rua;
    private String dono;
    private float largura;
    private float comprimento;
    private float Area;

    /* Métodos */
    Terreno(int endereco, String rua, float largura, float comprimento) {
        this.endereco = endereco;
        this.rua = rua;
        this.largura = largura;
        this.comprimento = comprimento;
        this.Area = largura*comprimento;
    }

    //Setters
    void setLargura(float largura) { this.largura = largura; }
    void setComprimento(float comprimento) { this.comprimento = comprimento; }
    void setArea(float Area) { this.Area = Area; }
    void setEndereco(int endereco) { this.endereco = endereco; } 
    void setRua(String rua) { this.rua = rua; }
    void setDono(String dono) { this.dono = dono; }
    
    //Getters
    int getEndereco() { return this.endereco; }
    String getRua() { return this.rua; }
    String getDono() { return this.dono; }
    float getComprimento() { return this.comprimento; }
    float getLargura() { return this.largura; }
    float getArea() { return this.Area; }

    int saleValue() {
        int value = (int)getArea()*1500;
        return value;
    }

    @Override
    public String toString() {
        String str = new String();
        str = "rua "+getRua() + ", " + "endereço "+getEndereco() + "\n";
        str += "Area por metros quadrados: " + getArea() + "\n";
        return str;
    }

    @Override
    public int compareTo(Terreno o) {
        return (int)(Area - o.Area);
    }
}
