package Imobiliaria;

import java.util.List;
import java.util.ArrayList;

class Client {
    /* Atributos */
    private String nome;
    private float saldo;
    private List<Casa> haveHouse;
    private List<Terreno> haveGround;
    private List<Predio> haveBuild;
    private List<Condominio> haveCondominio;

    /* Métodos */

    Client(String nome) {
        this.nome = nome;
        this.saldo = 0;
        haveHouse = new ArrayList<Casa>();
        haveGround = new ArrayList<Terreno>();
        haveBuild = new ArrayList<Predio>();
        haveCondominio = new ArrayList<Condominio>();
    }

    //Getters
    String getNome() { return nome; }
    float getSaldo() { return saldo; }
    List<Casa> getHaveHouse() { return haveHouse; }
    List<Terreno> getHaveGround() { return haveGround; }
    List<Predio> getHaveBuild() { return haveBuild; }
    List<Condominio> getHaveCondominio() { return haveCondominio; }

    //Setters
    void addSaldo(float saldo) { 
        this.saldo += saldo;
    }

    /*
     * método que adiciona uma casa a sua lista de casas
     * no qual ele comprou
     */
    void addCasa(Casa casa) {
        haveHouse.add(casa);
    }

    /*
     * método que permite o cliente vender uma casa que está em sua lista
     * de propriedades
     */
    boolean rmCasa(Casa Casa) {
        if(Casa.getDono().equals(getNome()))
            for(int i = 0; i < haveHouse.size(); i++) {
                if(haveHouse.get(i).equals(Casa)) {
                    haveHouse.remove(Casa);
                    return true;
                }
            }
        return false;
    }

    /* 
     * método que adiciona um apartamento adiquirido na lista de ímoveis
     * do cliente
     */
    void addApartamento(Predio Predio, Apartamento Aprt) {
        for(int i = 0; i < haveBuild.size(); i++) {
            if(haveBuild.get(i).Equal(Predio)) {
                haveBuild.get(i).addSellApart(Aprt);
                return;
            }
        }
        if(Aprt.getNumAndar() > 0 && Aprt.getNumAndar() <= Predio.getMaxAndar() &&
            Aprt.getNumApart() >= 0 && Aprt.getNumApart() <= Predio.getNumMaxApart()) {
            Predio myBuild = new Predio(Predio.getEndereco(), Predio.getRua(), Predio.getElevator(), Predio.getPool(), Predio.getGarage(), Predio.getMaxAndar(), Predio.getNumMaxApart(), Predio.getNamePredio());
            myBuild.setDono(getNome());
            myBuild.addSellApart(Aprt);
            haveBuild.add(myBuild);
        }
        else {
            System.out.println("Apartamento não existe");
        }
    }

    /* 
     * método que permite que ele venda uma apartamento no seu nome
     */
    boolean rmApartamento(Apartamento Aprt) {
        for(int i = 0; i < haveBuild.size(); i++) {
            for(int j = 0; j < haveBuild.get(i).getApartamento().size(); j++) {
                if(haveBuild.get(i).getApartamento().get(j).equals(Aprt)) {
                    if(haveBuild.get(i).getApartamento().get(j).getDono().equals(getNome())) {
                        haveBuild.get(i).getApartamento().remove(haveBuild.get(i).getApartamento().get(j));
                        return true;
                    }
                }
            }
        }
        return false;
        // esse apartamento nn existe ou nao ta no nome dele
    }

    void addTerreno(Terreno terreno) {
        haveGround.add(terreno);
    }

    boolean rmTerrno(Terreno Terreno) {
        for(int i = 0; i < haveGround.size(); i++) {
            if(haveGround.get(i).equals(Terreno)) {
                haveGround.remove(Terreno);
                return true;
            }
        }
        return false;
        // a casa nn ta no nome dele
    }

    void addCasaCondominio(Condominio Condominio, int endereco, String nome) {
        for(int i = 0; i < haveCondominio.size(); i++) {
            if(haveCondominio.get(i).condominioEqual(Condominio)) {
                haveCondominio.get(i).addCasaCondominio(endereco, getNome());
                haveCondominio.get(i).getCondominioHouse().get(haveCondominio.get(i).getCondominioHouse().size()-1).setDono(getNome());
                return;
            }
        }
        if(endereco > 0 && endereco <= Condominio.getNumMaxHouse()) {
            Condominio myCondominio = new Condominio(Condominio.getNome(), Condominio.getRua(), Condominio.getNumMaxHouse());
            myCondominio.addCasaCondominio(endereco, getNome());
            myCondominio.getCondominioHouse().get(0).setDono(getNome());
            haveCondominio.add(myCondominio);
        }
        else { System.out.println("numero da residencia ultrapassa numero de casas"); }
    }

    boolean rmCasaCondominio(Casa Casa) {
        for(int i = 0; i < haveCondominio.size(); i++) {
            for(int j = 0; j < haveCondominio.get(i).getCondominioHouse().size(); j++) {
                if(haveCondominio.get(i).getCondominioHouse().get(j).equals(Casa))
                    if(haveCondominio.get(i).getCondominioHouse().get(j).getDono().equals(getNome())) {
                        haveCondominio.get(i).getCondominioHouse().remove(Casa);
                        return true;
                    }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "nome: " + getNome() + "\n";
        str += "saldo: " + getSaldo() + "\n\n";

        if(haveHouse.size() != 0 || haveCondominio.size() != 0) { str += "    CASAS    \n"; }
        for(int i = 0; i < haveHouse.size(); i++) {
            str += "- casa " + (i+1) + "\n"; 
            str += "proprietario: " + haveHouse.get(i).getDono() + "\n";
            str += haveHouse.get(i) + "\n";
        }
        for(int i = 0; i < haveCondominio.size(); i++) {
            str += "Condominio " + (i+1) + ": " + haveCondominio.get(i).getNome() + "\n";
            for(int j = 0; j < haveCondominio.get(i).getCondominioHouse().size(); j++) {
                str += "-casa " + (i+1) + "\n";
                str += "proprietario: " + haveCondominio.get(i).getCondominioHouse().get(j).getDono() + "\n";
                str += haveCondominio.get(i).getCondominioHouse().get(j) + "\n";
            }
        }

        if(haveBuild.size() != 0) { str += "    APARTAMENTOS    \n"; }
        for(int i = 0; i < haveBuild.size(); i++) {
            str += "Predio " + (i+1)+ ": " + haveBuild.get(i).getNamePredio() + "\n";
            for(int j = 0; j < haveBuild.get(i).getApartamento().size(); j++) {
                str += "- Apartamento " + (j+1) + "\n";
                str += "proprietario: " + haveBuild.get(i).getApartamento().get(j).getDono() + "\n";
                str += haveBuild.get(i).getApartamento().get(j) + "\n";
            }
        }

        if(haveGround.size() != 0) { str += "    TERRENOS    \n"; }
        for(int i = 0; i < haveGround.size(); i++) {
            str += "- terreno " + (i+1) + "\n";
            str += "proprietario: " + haveGround.get(i).getDono() + "\n" + haveGround.get(i) + "\n";
        }

        if(haveHouse.size() == 0 && haveGround.size() == 0 && haveBuild.size() == 0 && haveCondominio.size() == 0) {
            str += "nao possui ímoveis. \n"; 
        }

        return str;
    }
}
