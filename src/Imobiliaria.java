package Imobiliaria;

import java.util.List;
import java.util.ArrayList;

class Imobiliaria {
    /* Atributos */
    private List<Casa> Casa;
    private List<Terreno> Terreno;
    private List<Predio> Predio;
    private List<Condominio> Condominio;
    private Integer escolha;

    /* Métodos */
    Imobiliaria() {
        Casa = new ArrayList<Casa>();
        Terreno = new ArrayList<Terreno>();
        Predio = new ArrayList<Predio>();
        Condominio = new ArrayList<Condominio>();
    }

    //Getters
    List<Casa> getCasa() { return this.Casa; }
    List<Terreno> getTerreno() { return this.Terreno; }
    List<Predio> getPredio() { return this.Predio; }
    List<Condominio> getCondominio() { return this.Condominio; }

    //Setter
    void setEscolha(int n) { this.escolha = n; }
    void setCasa(List<Casa> Casa) {
        this.Casa.clear();
        for(int i = 0; i < Casa.size(); i++) { this.Casa.add(Casa.get(i)); } 
    }
    void setTerreno(List<Terreno> Terreno) {
        this.Terreno.clear();
        for(int i = 0; i < Terreno.size(); i++) { this.Terreno.add(Terreno.get(i)); }
    }
    void setPredio(List<Predio> Predio) {
        this.Predio.clear();
        for(int i = 0; i < Predio.size(); i++) { this.Predio.add(Predio.get(i)); }
    }
    void setCondominio(List<Condominio> Condominio) {
        this.Condominio.clear();
        for(int i = 0; i < Condominio.size(); i++) { this.Condominio.add(Condominio.get(i)); }
    }

    /**
     * Método que adiciona um Obejto casa na lista de casas da imobiliaria
     * @param Casa casa que será colocada na lista para venda
     * @return true ou false
     */
    boolean addSellHouse(Casa Casa) {
        for(int i = 0; i < this.Casa.size(); i++) {
            if(this.Casa.get(i).getRua().equals(Casa.getRua()) && this.Casa.get(i).getEndereco() == Casa.getEndereco()) {
                // já existe uma casa no mesmo endereço e não existe em endereços iguais
                return false;
            }
        }
        this.Casa.add(Casa);
        return true;
    }

    /**
     * Método que remove uma casa da lista de casas da imobiliaria e coloca em client
     * chamada quando uma casa é vendida
     * @param Casa casa que foi vendida
     * @param client usuário
     * @return retorna ture se a venda tiver sido um sucesso, false caso contrário
     */
    boolean rmSellHouse(Casa Casa, Client client) {
        for(int i = 0; i < this.Casa.size(); i++) {
            if(client.getSaldo() > Casa.saleValue()) {
                if(this.Casa.get(i).equals(Casa)) {
                    this.Casa.remove(Casa);
                    client.addCasa(Casa);
                    client.addSaldo(-Casa.saleValue());
                    Casa.setDono(client.getNome());
                    return true;
                }
            }
            else {
                System.out.println("o cliente possui saldo insuficiente");
            }
        }
        return false;
    }

    /**
     * Método que permite que o usuário alugue uma casa, retira da lista
     * de casas da imobiliria e coloca em client
     * @param Casa casa que foi alugada
     * @param client usuário
     * @return retorna true se a alugaçao tiver sido um sucesso, false caso contrário
     */
    boolean rmRentHouse(Casa Casa, Client client) {
        for(int i = 0; i < this.Casa.size(); i++) {
            if(client.getSaldo() > Casa.saleValue()*0.1) {
                if(this.Casa.get(i).equals(Casa)) {
                    this.Casa.remove(Casa);
                    client.addCasa(Casa);
                    client.addSaldo(-(float)(Casa.saleValue()*0.1) - Casa.rentValue());
                    return true;
                }
            } else {
                System.out.println("o cliente possui saldo insuficiente");
                return false;
            }
        }
        return false;
    }

    /**
     * Método que é chamado quando o usuário vende uma casa a imobiliaria
     * @param Casa casa a ser adicionada na lista
     * @param client cliente que vendeu a casa
     * @return retorna true ou false
     */
    boolean buyHouse(Casa Casa, Client client) {
        if(client.rmCasa(Casa)) {
            client.addSaldo(Casa.saleValue());
            addSellHouse(Casa);
            Casa.setDono("imobiliaria");
            return true;
        }
            
        return false;
    }

    /**
     * Método que adiciona um predio na lista de predios da imobiliaria
     * @param Predio predio a ser adicionada
     * @return true or false
     */
    boolean addSellBuild(Predio predio) {
        for(int i = 0; i < this.Predio.size(); i++) {
            if(this.Predio.get(i).getRua().equals(predio.getRua()) && this.Predio.get(i).getEndereco() == predio.getEndereco()) {
                // já existe um Predio no mesmo endereço e não existe em endereços iguais
                return false;
            }
        }
        this.Predio.add(predio);
        return true;
    }

    /**
     * Método no qual é chamado quando o usuário compra um apartamento, é feito 
     * uma procura na lista de predios e depois na lista de apartamento do prédio.get(i)
     * se o apartamento existir e o usuário tiver saldo para comprar, a compra é realizada
     * @param Predio predio em que está o apartamento
     * @param Aprt apartamento a ser comprado pelo usuário
     * @param client usuário
     * @return true ou false
     */
    boolean rmSellBuild(Predio predio, Apartamento Aprt, Client client) {
        for(int i = 0; i < this.Predio.size(); i++) {
            if(this.Predio.get(i).equals(predio)) {
                for(int j = 0; j < this.Predio.get(i).getApartamento().size(); j++) {
                    if(this.Predio.get(i).getApartamento().get(j).equals(Aprt)) {
                        if(client.getSaldo() > this.Predio.get(i).saleValue(Aprt)) {
                            this.Predio.get(i).getApartamento().remove(Aprt);
                            client.addApartamento(this.Predio.get(i), Aprt);
                            client.addSaldo(-this.Predio.get(i).saleValue(Aprt));
                            Aprt.declararDono(client.getNome());
                            return true;
                        } else {
                            System.out.println("o cliente possui saldo insuficiente");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
        // esse Predio não existe na lista de venda
    }

    /**
     * Método no qual permite a alugação de um apartamento, mesmo procedimento da venda de um
     * apartamento, sendo que o dodo do apartamento continua sendo a imobiliaria
     * @param Predio predio em que está o apartamento
     * @param Aprt apartamento a ser alugado
     * @param client usuário
     * @return true ou false
     */
    boolean rmRentBuild(Predio predio, Apartamento Aprt, Client client) {
        for(int i = 0; i < this.Predio.size(); i++) {
            if(this.Predio.get(i).equals(predio)) {
                for(int j = 0; j < this.Predio.get(i).getApartamento().size(); j++) {
                    if(this.Predio.get(i).getApartamento().get(j).equals(Aprt)) {
                        if(client.getSaldo() > this.Predio.get(i).saleValue(Aprt)*0.1) {
                            this.Predio.get(i).getApartamento().remove(Aprt);
                            client.addApartamento(this.Predio.get(i), Aprt);
                            client.addSaldo(-(float)(this.Predio.get(i).saleValue(Aprt)*0.1) - Aprt.getAluguel());
                            return true;
                        } else {
                            System.out.println("o cliente possui saldo insuficiente");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
        // esse Predio não existe na lista de venda
    }

    boolean buyBuild(Predio predio, Apartamento Aprt, Client client) {
        for(int i = 0; i < this.Predio.size(); i++) {
            if(this.Predio.get(i).Equal(predio)) {
                if(client.rmApartamento(Aprt)) {
                    client.addSaldo(predio.saleValue(Aprt));
                    Aprt.declararDono("imobiliaria");
                    this.Predio.get(i).addSellApart(Aprt);
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * método no qual adiciona terreno na lista de terrenos
     */
    boolean addSellGround(Terreno terreno) {
        for(int i = 0; i < this.Terreno.size(); i++) {
            if(this.Terreno.get(i).getRua().equals(terreno.getRua()) && this.Terreno.get(i).getEndereco() == terreno.getEndereco()) {
                // já existe um Predio no mesmo endereço e não existe em endereços iguais
                return false;
            }
        }
        this.Terreno.add(terreno);
        return true;
    }

    /*
     * método no qual vende um terreno para algum client
     */
    boolean rmSellGround(Terreno terreno, Client client) {
        for(int i = 0; i < this.Terreno.size(); i++) {
            if(client.getSaldo() > terreno.saleValue()) {
                if(this.Terreno.get(i).equals(terreno)) { 
                    this.Terreno.remove(terreno);
                    client.addTerreno(terreno);
                    client.addSaldo(-terreno.saleValue());
                    terreno.setDono(client.getNome());
                    return true;
                }
            } else {
                System.out.println("o cliente possui saldo insuficiente");
            }
        }
        return false;
        // esse Terreno não existe na lista de venda
    }

    boolean buyGround(Terreno terreno, Client client) {
        if(client.rmTerrno(terreno)) {
            client.addSaldo(terreno.saleValue());
            addSellGround(terreno);
            return true;
        }
        return false;
    }

    void addSellCondominum(Condominio condominio) {
        this.Condominio.add(condominio);
    }

    boolean rmSellCondominum(Condominio condominio, Casa Casa, Client client) {
        for(int i = 0; i < this.Condominio.size(); i++) {
            if(this.Condominio.get(i).equals(condominio)) {
                for(int j = 0; j < this.Condominio.get(i).getCondominioHouse().size(); j++) {
                    if(this.Condominio.get(i).getCondominioHouse().get(j).equals(Casa)) {
                        if(client.getSaldo() > Casa.saleValue()) {
                            this.Condominio.get(i).getCondominioHouse().remove(Casa);
                            client.addCasaCondominio(condominio, Casa.getEndereco(),client.getNome());
                            client.addSaldo(-Casa.saleValue());
                            return true;
                        } else {
                            System.out.println("o cliente possui saldo insuficiente");
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean rmRentCondominum(Condominio condominio, Casa Casa, Client client) {
        for(int i = 0; i < this.Condominio.size(); i++) {
            if(this.Condominio.get(i).equals(condominio)) {
                for(int j = 0; j < this.Condominio.get(i).getCondominioHouse().size(); j++) {
                    if(this.Condominio.get(i).getCondominioHouse().get(j).equals(Casa)) {
                        if(client.getSaldo() > Casa.saleValue()*0.1) {
                            this.Condominio.get(i).getCondominioHouse().remove(Casa);
                            client.addCasaCondominio(condominio, Casa.getEndereco(),"imobiliaria");
                            client.addSaldo(-(float)(Casa.saleValue()*0.1) - Casa.rentValue());
                            return true;
                        } else {
                            System.out.println("o cliente possui saldo insuficiente");
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean buyCondominium(Condominio condominio, Casa Casa, Client client) {
        for(int i = 0; i < this.Condominio.size(); i++) {
            if(this.Condominio.get(i).condominioEqual(condominio)) {
                if(client.rmCasaCondominio(Casa)) {
                    client.addSaldo(Casa.saleValue());
                    this.Condominio.get(i).addCasaCondominio(Casa.getEndereco(), "imobiliaria");
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String str = new String();
        if(escolha.equals(1)) {
            if(Casa.size() == 0) {
                str += "nao ha casas para venda ou alugação no momento.";
            }
            for(int i = 0; i < Casa.size(); i++) {
                str += "- Casa " + (i+1) + " \n";
                str += Casa.get(i);
                str += "valor de venda: " + Casa.get(i).saleValue() + "\n";
                str += "valor do aluguel: " + Casa.get(i).rentValue() + "\n";
            }
        }
        else if(escolha.equals(2)) {
            for(int i = 0; i < Condominio.size(); i++) {
                str += "- Condominio " + (i+1) + " -\n";
                str += "Condominio " + Condominio.get(i).getNome() + "\n";
                str += "Casas disponiveis: \n";
                str += Condominio.get(i);
            }
        }
        else if(escolha.equals(3)) {
            for(int i = 0; i < Predio.size(); i++) {
                str += "- Predio " + (i+1) + " -\n";
                str += "Predio: " + Predio.get(i).getNamePredio() + "\n";
                str += "apartamentos disponiveis: \n";
                str += Predio.get(i) + "\n";
            }
        }
        else if(escolha.equals(4)) {
            for(int i = 0; i < Terreno.size(); i++) {
                str += "- Terreno " + (i+1) + " \n";
                str += Terreno.get(i);
                str += "valor de venda: " + Terreno.get(i).saleValue() + "\n";
            }
        }

        return str;
    }
}