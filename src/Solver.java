package Imobiliaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;

class Solver {
    /**
     * Método no qual implementa o menu da aplicação
     * 
     * @param imobiliaria recebe a imobiliaria no qual irá vender ou alugar imoveis
     * @param client recebe o cliente que será a classe do usuário
     * @param scanner recebe um objeto do tipo scanner
     * @param line recebe uma String para a configuração do menu
     * @param n recebe um inteiro n que será a escolha do usuário
     * @return retorna um inteiro, que servirá para a escolha da proxima acao
     */
    public static int menu(Imobiliaria imobiliaria, Client client, Scanner scanner, String line, int n) {
        try {
            Integer num = n;
            Integer prd;
            if(num.equals(9)) {
                return num;
            } else if(num.equals(1)) {
                imobiliaria.setEscolha(num);
                Collections.sort(imobiliaria.getCasa());
                System.out.println(imobiliaria);
                System.out.println("Qual escolhe?");
                System.out.println("Aperte 0 para voltar ao menu principal.");

                System.out.print("Casa: ");
                num = scanner.nextInt();
                line = scanner.nextLine();
                System.out.println();

                while(num != 0) {
                    System.out.println("0 - voltar menu");
                    System.out.println("1 - comprar");
                    System.out.println("2 - alugar");
                    System.out.print("Opcao: ");
                    line = scanner.nextLine();
                    System.out.println();

                    if(line.equals("1")) {
                        if(imobiliaria.rmSellHouse(imobiliaria.getCasa().get(num-1), client)) {
                            System.out.println(". . .");
                            System.out.println("compra realizada com sucesso");
                            System.out.println();
                            num = 0;
                        }
                    }
                    else if(line.equals("2")) {
                        if(imobiliaria.rmRentHouse(imobiliaria.getCasa().get(num-1), client)) {
                            System.out.println(". . .");
                            System.out.println("alugaçao realizada com sucesso");
                            System.out.println();
                            num = 0;
                        }
                    }
                    else if(line.equals("0")) {
                        num = 0;
                    }
                    else {
                        System.out.println("comando não entendido \n");
                    }
                }
            /* Compra ou alugação de Casas em Condominios */
            } else if(num.equals(2)) {
                imobiliaria.setEscolha(num);
                Collections.sort(imobiliaria.getCondominio());
                for(int i = 0; i < imobiliaria.getCondominio().size(); i++) { Collections.sort(imobiliaria.getCondominio().get(i).getCondominioHouse()); }

                System.out.println(imobiliaria);
                System.out.println("Qual Condominio?");
                System.out.println("Aperte 0 para voltar ao menu principal.");
                System.out.print("Condominio: ");
                num = scanner.nextInt();
                line = scanner.nextLine();

                if(num != 0) {
                    System.out.print("Casa: ");
                    prd = scanner.nextInt();
                    line = scanner.nextLine();
                    System.out.println();

                    if(num > 0 && num <= imobiliaria.getCondominio().size()) {
                        if(prd > 0 && prd < imobiliaria.getCondominio().get(num-1).getCondominioHouse().size()) {
                            while(num != 0) {
                                System.out.println("0 - voltar menu");
                                System.out.println("1 - comprar");
                                System.out.println("2 - alugar");
                                System.out.print("Opcao: ");
                                line = scanner.nextLine();
                                System.out.println();

                                /*
                                * verifica se o condominio ou casa escolhidos estão dentro da lisa de
                                * condominios do usuário ou dentro da lista de casas dentro do condominio
                                * respectivamente, também verifica se o usuário quer voltar ao menu principal
                                */
                                if(line.equals("1")) {
                                    if(imobiliaria.rmSellCondominum(imobiliaria.getCondominio().get(num-1), imobiliaria.getCondominio().get(num-1).getCondominioHouse().get(prd-1), client)) {
                                        System.out.println(". . .");
                                        System.out.println("compra realizada com sucesso");
                                        System.out.println();
                                        num = 0;
                                    }
                                }
                                else if(line.equals("2")) {
                                    Collections.sort(imobiliaria.getCondominio().get(num-1).getCondominioHouse());
                                    if(imobiliaria.rmRentCondominum(imobiliaria.getCondominio().get(num-1), imobiliaria.getCondominio().get(num-1).getCondominioHouse().get(prd-1), client)) {
                                        System.out.println(". . .");
                                        System.out.println("alugaçao realizada com sucesso");
                                        System.out.println();
                                        num = 0;
                                    }
                                }
                                else if(line.equals("0")) {
                                    num = 0;
                                }
                                else {
                                    System.out.println("comando não entendido \n");
                                }
                            }
                        } else { System.out.println("Casa nao existente\n"); }
                    } else { System.out.println("Condominio inexistente\n"); }
                }

            /* Compra ou alugação de Apartamentos */
            } else if(num.equals(3)) {
                imobiliaria.setEscolha(num);
                Collections.sort(imobiliaria.getPredio());
                for(int i = 0; i < imobiliaria.getPredio().size(); i++) { Collections.sort( imobiliaria.getPredio().get(i).getApartamento()); }

                
                System.out.println(imobiliaria);
                System.out.println("Qual Predio?");
                System.out.println("Aperte 0 para voltar ao menu principal.");
                System.out.print("Predio: ");
                num = scanner.nextInt();
                line = scanner.nextLine();

                if(num != 0) {
                    System.out.print("Apartamento: ");
                    prd = scanner.nextInt();
                    line = scanner.nextLine();
                    System.out.println();

                    /*
                    * verifica se o apartamento ou predio escolhido está dentro da lista de predios
                    * do usuário e dentro da lista de apartamentos do predio escolhido, também verifica se o usuário
                    * deseja voltar ao menu principal 
                    */

                    if(num > 0 && num <= imobiliaria.getPredio().size()) {
                        if(prd > 0 && prd < imobiliaria.getPredio().get(num-1).getApartamento().size()) {
                            while(num != 0) {
                                System.out.println("0 - voltar menu");
                                System.out.println("1 - comprar");
                                System.out.println("2 - alugar");
                                System.out.print("Opcao: ");
                                line = scanner.nextLine();

                                if(line.equals("1")) {
                                    if(imobiliaria.rmSellBuild(imobiliaria.getPredio().get(num-1),imobiliaria.getPredio().get(num-1).getApartamento().get(prd-1), client)) {
                                        System.out.println(". . .");
                                        System.out.println("compra realizada com sucesso");
                                        System.out.println();
                                        num = 0;
                                    }
                                }
                                else if(line.equals("2")) {
                                    if(imobiliaria.rmRentBuild(imobiliaria.getPredio().get(num-1),imobiliaria.getPredio().get(num-1).getApartamento().get(prd-1), client)) {
                                        System.out.println(". . .");
                                        System.out.println("alugaçao realizada com sucesso");
                                        System.out.println();
                                        num = 0;
                                    }
                                }
                                else if(line.equals("0")) {
                                    num = 0;
                                }
                                else {
                                    System.out.println("comando não entendido \n");
                                }
                            }
                        } else { System.out.println("Apartamento nao existente\n"); }
                    } else { System.out.println("Predio inexistente\n"); }
                }
            /* Compra de terrenos */
            } else if(num.equals(4)) {
                imobiliaria.setEscolha(num);
                Collections.sort(imobiliaria.getTerreno());

                System.out.println(imobiliaria);
                System.out.println("Qual escolhe?");
                System.out.println("Aperte 0 para voltar ao menu principal.");

                System.out.print("Terreno: ");
                num = scanner.nextInt();
                line = scanner.nextLine();
                System.out.println();

                while(num != 0) {
                    System.out.println("0 - voltar menu");
                    System.out.println("1 - comprar");
                    System.out.print("Opcao: ");
                    line = scanner.nextLine();

                    if(line.equals("1")) {
                        if(imobiliaria.rmSellGround(imobiliaria.getTerreno().get(num-1), client)) {
                            System.out.println(". . .");
                            System.out.println("compra realizada com sucesso");
                            System.out.println();
                            num = 0;
                        }
                    } else if(line.equals("0")) {
                        num = 0;
                    }
                    else {
                        System.out.println("comando não entendido \n");
                    }
                }
            /*
            * item no qual permite ao usuário ver as casas que ele possui seja compradas ou alugadas
            * Se por acaso tiver alguma propriedade no nome, será permitido ao usuário vender
            * essa propriedade caso ela estiver no seu nome
            */
            } else if(num.equals(5)) {
                Collections.sort(client.getHaveHouse());
                Collections.sort(client.getHaveBuild());
                Collections.sort(client.getHaveCondominio());
                Collections.sort(client.getHaveGround());
                for(int i = 0; i < client.getHaveCondominio().size(); i++) { Collections.sort(client.getHaveCondominio().get(i).getCondominioHouse()); }
                for(int i = 0; i < client.getHaveBuild().size(); i++) { Collections.sort(client.getHaveBuild().get(i).getApartamento()); }

                System.out.print(client);
                line = scanner.nextLine();
                System.out.println("Deseja vender algum imovel para a imobiliaria?\n");

                System.out.println("0 - voltar ao menu principal.");
                if(client.getHaveHouse().size() != 0) { System.out.println("1 - Casa sem Condominio"); }
                if(client.getHaveCondominio().size() != 0) { System.out.println("2 - Casa com Condominio"); }
                if(client.getHaveBuild().size() != 0) { System.out.println("3 - Apartamento"); }
                if(client.getHaveGround().size() != 0) { System.out.println("4 - Terreno"); }

                System.out.print("Opcao: ");
                line = scanner.nextLine();
                System.out.println();

                if(line.equals("1")) {
                    System.out.print("Casa: ");
                    num = scanner.nextInt();
                    
                    /*
                    * O usuário escolherá uma casa da sua lista de casas para revender a imobiliaria
                    * se a venda da casa escolhida der algum erro, irá escrever uma mensagem de 
                    * venda falha.
                    */
                    if(imobiliaria.buyHouse(client.getHaveHouse().get(num-1), client)) {
                        System.out.println(". . .");
                        System.out.println("Venda realizada com sucesso\n");
                    }
                    else {
                        System.out.println(". . .");
                        System.out.println("Falha na venda do ímovel\n");
                    }
                } else if(line.equals("2")) {
                    System.out.print("Condominio: ");
                    num = scanner.nextInt();
                    System.out.print("Casa: ");
                    prd = scanner.nextInt();

                    /*
                    * Se por acaso o condominio ou casa escolhidos forem fora do range, irá escrever uma
                    * mensagem de erro no terminal, se por acaso estiverem dentro irá chamar o método
                    * no qual o usuário vende o imovel escolhido para a imobiliaria, se a venda der erro 
                    * irá retorna uma mensagem de venda falha.
                    */
                    if(num > 0 && num <= client.getHaveCondominio().size()) {
                        if(prd > 0 && prd <= client.getHaveCondominio().get(num-1).getCondominioHouse().size()) {
                            if(imobiliaria.buyCondominium(client.getHaveCondominio().get(num-1), client.getHaveCondominio().get(num-1).getCondominioHouse().get(prd-1), client)) {
                                System.out.println(". . .");
                                System.out.println("Venda realizada com sucesso.\n");
                            }
                            else {
                                System.out.println(". . .");
                                System.out.println("Falha na venda do ímovel.\n");
                            }
                        } else { System.out.println("Casa nao existente\n"); }
                    } else { System.out.println("Condominio inexistente\n"); }

                } else if(line.equals("3")) {
                    System.out.print("Predio: ");
                    num = scanner.nextInt();
                    System.out.print("Apartamento: ");
                    prd = scanner.nextInt();

                    /*
                    * Se por acaso o predio ou apartamento escolhidos forem fora do range, irá escrever uma
                    * mensagem de erro no terminal, se por acaso estiverem dentro irá chamar o método
                    * no qual o usuário vende o imovel escolhido para a imobiliaria, se a venda der erro 
                    * irá retorna uma mensagem de venda falha.
                    */
                    if(num > 0 && num <= client.getHaveBuild().size()) {
                        if(prd > 0 && prd <= client.getHaveBuild().get(num-1).getApartamento().size()) {
                            if(imobiliaria.buyBuild(client.getHaveBuild().get(num-1), client.getHaveBuild().get(num-1).getApartamento().get(prd-1), client)) {
                                System.out.println(". . .");
                                System.out.println("Venda realizada com sucesso.\n");
                            }
                            else {
                                System.out.println(". . .");
                                System.out.println("Falha na venda do ímovel.\n");
                            }
                        } else { System.out.println("Apartamento nao existente\n"); }
                    } else { System.out.println("Predio inexistente\n"); }

                } else if(line.equals("4")) {
                    System.out.print("Terreno: ");
                    num = scanner.nextInt();
                    
                    /*
                    * O usuário escolherá um terreno da sua lista de terreno para revender a imobiliaria
                    * se a venda da casa escolhida der algum erro, irá escrever uma mensagem de 
                    * venda falha.
                    */
                    if(imobiliaria.buyGround(client.getHaveGround().get(num-1), client)) {
                        System.out.println(". . .");
                        System.out.println("Venda realizada com sucesso");
                    }
                    else {
                        System.out.println(". . .");
                        System.out.println("Falha na venda do ímovel");
                    }
                }

            /*
            * opcao que escreve no terminal instruções sobre oque ele pode fazer
            * dentro da imobiliaria
            */
            } else if(num.equals(6)) {
                System.out.println("Para realizar a compra de um ímovel é preciso");
                System.out.println("ter o valor que ele total do item requerido.");
                System.out.println();
                System.out.println("Para alugar um ímovel se é preciso pagar 10%");
                System.out.println("do valor total do ímovel e pagar o primeiro mês");
                System.out.println("do aluguel.");
                System.out.println();
                System.out.println("Para vender um ímovel, apenas será possivel");
                System.out.println("aqueles que estiverem no seu nome.");
                System.out.println();
                line = scanner.nextLine();
            } else if(num.equals(7)) {
                System.out.print("Digite o endereço da casa: ");
                Integer novoEndereco = scanner.nextInt();
                line = scanner.nextLine();
                System.out.println();

                System.out.print("Digite a rua da casa: ");
                String novaRua = scanner.nextLine();
                System.out.println();

                System.out.print("Digite a quantidade de comodos da casa: ");
                Integer novosComodos = scanner.nextInt();
                line = scanner.nextLine();
                System.out.println();

                Boolean novaGaragem = false;
                System.out.println("possui garagem?");
                System.out.print("1 - sim \n2 - nao \nopcao: ");
                num = scanner.nextInt();
                System.out.println();
                if(num.equals(1)) { novaGaragem = true; }
                else if(!num.equals(2)) { System.out.println("opcao nao encontrada."); }

                Boolean novoQuintal = false;
                System.out.println("possui quintal?");
                System.out.print("1 - sim \n2 - nao \nopcao:");
                num = scanner.nextInt();
                System.out.println();
                if(num.equals(1)) { novoQuintal = true; }
                else if(!num.equals(2)) { System.out.println("opcao nao encontrada."); }

                Casa novaCasa = new Casa(novoEndereco, novaRua, novosComodos, novaGaragem, novoQuintal);
                novaCasa.setDono(client.getNome());
                client.addCasa(novaCasa);
                System.out.println("Concluindo...");
                System.out.println("Concluido");
                System.out.println();
            } else if(num.equals(8)) {    
                Collections.sort(client.getHaveHouse());
                Collections.sort(client.getHaveBuild());
                Collections.sort(client.getHaveCondominio());
                Collections.sort(client.getHaveGround());
                for(int i = 0; i < client.getHaveCondominio().size(); i++) { Collections.sort(client.getHaveCondominio().get(i).getCondominioHouse()); }
                for(int i = 0; i < client.getHaveBuild().size(); i++) { Collections.sort(client.getHaveBuild().get(i).getApartamento()); }

                System.out.print(client);
                line = scanner.nextLine();
                System.out.println("Qual deseja vender?\n");
                System.out.println();

                System.out.println("0 - voltar ao menu principal.");
                if(client.getHaveHouse().size() != 0) { System.out.println("1 - Casa sem Condominio"); }
                if(client.getHaveCondominio().size() != 0) { System.out.println("2 - Casa com Condominio"); }
                if(client.getHaveBuild().size() != 0) { System.out.println("3 - Apartamento"); }
                if(client.getHaveGround().size() != 0) { System.out.println("4 - Terreno"); }

                System.out.print("Opcao: ");
                line = scanner.nextLine();
                System.out.println();

                if(line.equals("1")) {
                    System.out.print("Casa: ");
                    num = scanner.nextInt();

                    client.addSaldo(client.getHaveHouse().get(num-1).saleValue());
                    if(client.rmCasa(client.getHaveHouse().get(num-1))) {
                        System.out.println(". . .");
                        System.out.println("Venda realizada com sucesso!\n");
                    }
                    else {
                        client.addSaldo(-client.getHaveHouse().get(num-1).saleValue());
                        System.out.println(". . .");
                        System.out.println("Falha na venda do ímovel!\n");
                    }
                } else if(line.equals("2")) {
                    System.out.print("Condominio: ");
                    num = scanner.nextInt();
                    System.out.print("Casa: ");
                    prd = scanner.nextInt();

                    if(num > 0 && num <= client.getHaveCondominio().size()) {
                        if(prd > 0 && prd <= client.getHaveCondominio().get(num-1).getCondominioHouse().size()) {
                            client.addSaldo(client.getHaveCondominio().get(num-1).getCondominioHouse().get(prd-1).saleValue());
                            if(client.rmCasaCondominio(client.getHaveCondominio().get(num-1).getCondominioHouse().get(prd-1))) {
                                System.out.println(". . .");
                                System.out.println("Venda realizada com sucesso.\n");
                            }
                            else {
                                client.addSaldo(-client.getHaveCondominio().get(num-1).getCondominioHouse().get(prd-1).saleValue());
                                System.out.println(". . .");
                                System.out.println("Falha na venda do ímovel.\n");
                            }
                        } else { System.out.println("Casa nao existente\n"); }
                    } else { System.out.println("Condominio inexistente\n"); }

                } else if(line.equals("3")) {
                    System.out.print("Predio: ");
                    num = scanner.nextInt();
                    System.out.print("Apartamento: ");
                    prd = scanner.nextInt();

                    if(num > 0 && num <= client.getHaveBuild().size()) {
                        if(prd > 0 && prd <= client.getHaveBuild().get(num-1).getApartamento().size()) {
                            client.addSaldo(client.getHaveBuild().get(num-1).saleValue(client.getHaveBuild().get(num-1).getApartamento().get(prd-1)));
                            if(client.rmApartamento(client.getHaveBuild().get(num-1).getApartamento().get(prd-1))) {
                                System.out.println(". . .");
                                System.out.println("Venda realizada com sucesso.\n");
                            }
                            else {
                                client.addSaldo(-client.getHaveBuild().get(num-1).saleValue(client.getHaveBuild().get(num-1).getApartamento().get(prd-1)));
                                System.out.println(". . .");
                                System.out.println("Falha na venda do ímovel.\n");
                            }
                        } else { System.out.println("Apartamento nao existente\n"); }
                    } else { System.out.println("Predio inexistente\n"); }

                } else if(line.equals("4")) {
                    System.out.print("Terreno: ");
                    num = scanner.nextInt();

                    client.addSaldo(client.getHaveGround().get(num-1).saleValue());
                    if(client.rmTerrno(client.getHaveGround().get(num-1))) {
                        System.out.println(". . .");
                        System.out.println("Venda realizada com sucesso");
                    }
                    else {
                        client.addSaldo(-client.getHaveGround().get(num-1).saleValue());
                        System.out.println(". . .");
                        System.out.println("Falha na venda do ímovel");
                    }
                }
            }
            else { System.out.println("Resposta não identificada"); } 

            return num;
        } catch (MsgException me) {
            System.out.println(me.getMessage());
        }

        return n;
    }

    /**
     * método que chamado quando se quer escrever algum terreno dentro de um arquivo
     * chamado no final da aplicação.
     * Caso a escrita de erro, uma IOException será pegue
     * @param nome indica se o terreno pertence a imobiliaria ou a usuário
     * @param Terreno terreno no qual irá ser escrito no arquivo
     */
    public static void escreverTerreno(String nome, Terreno Terreno) {
        try {
            FileWriter fileWriter = new FileWriter("imoveis.txt",true);
            fileWriter.write("terreno|");
            if(nome.equals("buffer")) {
                if(Terreno.getDono().indexOf(0) == '!') {
                    fileWriter.write(Terreno.getDono().substring(1) + "|");
                    fileWriter.write("imobiliaria|");
                } else {
                    fileWriter.write(Terreno.getDono() + "|");
                    fileWriter.write(Terreno.getDono() + "|");
                }
            } else { 
                fileWriter.write(nome + "|");
                fileWriter.write(Terreno.getDono() + "|");
            }
            fileWriter.write(Terreno.getRua() + "|");
            fileWriter.write(Terreno.getEndereco() + "|");
            fileWriter.write(Terreno.getLargura() + "|");
            fileWriter.write(Terreno.getComprimento() + "|\n");
            
    
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método no qual permite escrever um condominio em um arquivo
     * sendo chamado no final da aplicação.
     * Caso a escrita de erro, uma IOException será pegue
     * @param nome indica se o terreno pertence a imobiliaria ou a usuário
     * @param condominio condominio que será escrito no arquivo
     */
    public static void escreverCondominio(String nome, Condominio condominio) {
        try {
            FileWriter fileWriter = new FileWriter("imoveis.txt",true);
            fileWriter.write("condominio|");
            if(nome.equals("buffer")) {
                fileWriter.write(condominio.getNome().substring(0, condominio.getNome().indexOf('!')) + "|");
                fileWriter.write(condominio.getNome().substring(condominio.getNome().lastIndexOf('!')+1) + "|");
            } else {
                fileWriter.write(nome + "|");
                fileWriter.write(condominio.getNome() + "|");
            }
            fileWriter.write(condominio.getRua() + "|");
            fileWriter.write(condominio.getNumMaxHouse() + "|");
            fileWriter.write(condominio.getCondominioHouse().size() + "|");
            for(int i = 0; i < condominio.getCondominioHouse().size(); i++) {
                fileWriter.write(condominio.getCondominioHouse().get(i).getDono() + "|");
                fileWriter.write(condominio.getCondominioHouse().get(i).getEndereco() + "|");
            }
            fileWriter.write("\n");

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método no qual permite escrever um predio e seus apartamentos dentro de um
     * arquivo, sendo chamado apenas no final da aplicação.
     * Caso a escrita de erro, uma IOException será pegue
     * @param nome indica se o terreno pertence a imobiliaria ou a usuário
     * @param predio predio que será escrito no arquivo
     */
    public static void escreverPredio(String nome, Predio predio) {
        try {
            FileWriter fileWriter = new FileWriter("imoveis.txt",true);
            fileWriter.write("predio|");
            if(nome.equals("buffer")) {
                fileWriter.write(predio.getNamePredio().substring(0, predio.getNamePredio().indexOf('!')) + "|");
                fileWriter.write(predio.getNamePredio().substring(predio.getNamePredio().lastIndexOf('!')+1) + "|");
            } else {
                fileWriter.write(nome + "|");
                fileWriter.write(predio.getNamePredio() + "|");
            }
            fileWriter.write(predio.getRua() + "|");
            fileWriter.write(predio.getEndereco() + "|");
            fileWriter.write(predio.getMaxAndar() + "|");
            fileWriter.write(predio.getNumMaxApart() + "|");
            fileWriter.write(predio.getElevator() + "|");
            fileWriter.write(predio.getPool() + "|");
            fileWriter.write(predio.getGarage() + "|");
            fileWriter.write(predio.getApartamento().size() + "|");
            for(int i = 0; i < predio.getApartamento().size(); i++) {
                fileWriter.write(predio.getApartamento().get(i).getDono() + "|");
                fileWriter.write(predio.getApartamento().get(i).getNumAndar() + "|");
                fileWriter.write(predio.getApartamento().get(i).getNumApart() + "|");
            }
            fileWriter.write("\n");
    
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método no qual permite escrever uma casa dentro de um arquvio
     * sendo chamado apenas no final da aplicação.
     * Caso a escrita de erro, uma IOException será pegue
     * @param nome indica se o terreno pertence a imobiliaria ou a usuário
     * @param casa casa no qual será escrita no arquivo
     */
    public static void escreverCasa(String nome, Casa casa) {
        try {
            FileWriter fileWriter = new FileWriter("imoveis.txt",true);
            fileWriter.write("casa|");
            if(nome.equals("buffer")) {
                if(casa.getDono().indexOf(0) == '!') {
                    fileWriter.write(casa.getDono().substring(1) + "|");
                    fileWriter.write("imobiliaria|");
                } else {
                    fileWriter.write(casa.getDono() + "|");
                    fileWriter.write(casa.getDono() + "|");
                }
            } else { 
                fileWriter.write(nome + "|");
                fileWriter.write(casa.getDono() + "|");
            }
            fileWriter.write(casa.getRua() + "|");
            fileWriter.write(casa.getEndereco() + "|");
            fileWriter.write(casa.getComodos() + "|");
            if(casa.getGarage()) fileWriter.write("1|"); // 1 para true, 0 para false
            else fileWriter.write("0|");
            if(casa.getQuintal()) fileWriter.write("1|\n"); // 1 para true, 0 para false
            else fileWriter.write("0|\n");
    
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método chamado no começo do programa no qual, se o arquivo existir, caso ele exista dentro do package
     * se não existe, cria um novo arquivo e chama fileNotFoundException. Chamada no começo do programa para distribuir os
     * imoveis existentes.
     * @param imobiliaria obejto imobiliaria
     * @param client objeto client
     * @param buffer obejto client que recebe imoveis que não são do usuario nem da imobiliaria
     * @throws FileNotFoundException exceção caso não exista o arquivo
     */
    public static void leitura(Imobiliaria imobiliaria, Client client, Client buffer) throws FileNotFoundException {
        File imovel = new File("imoveis.txt");

        String line = new String();
        String procuraNome = new String();
        int i = 0;

        if(imovel.exists()) {
            try {
                InputStreamReader fileReader = new InputStreamReader(new FileInputStream("imoveis.txt"));
                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    while((line = bufferedReader.readLine()) != null) {
                        procuraNome = line.substring(0, line.indexOf('|'));
                        i = line.indexOf('|') + 1;

                        /* Caso ache alguma casa */
                        if(procuraNome.equals("casa")) {
                            procuraNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // dono da casa
                            String casaDono = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // rua da casa
                            String casaRua = (line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // endereco da casa
                            int casaEndereco = (Integer.parseInt(line.substring((i), line.indexOf('|', (i+1)))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // comodos da casa
                            int casaComodos = (Integer.parseInt(line.substring((i), line.indexOf('|', (i+1)))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // definir se a casa tem garagem
                            boolean casaGarage = false;
                            if(line.substring((i), line.indexOf('|', (i+1))).equals("1")) { casaGarage = true; }
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // definir se a casa tem quintal
                            boolean casaQuintal = false;
                            if(line.substring((i), line.indexOf('|', (i+1))).equals("1")) { casaQuintal = true; }
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            Casa casa = new Casa(casaEndereco, casaRua, casaComodos, casaGarage, casaQuintal);
                            casa.setDono(casaDono);

                            if(procuraNome.equals(client.getNome())) {
                                client.addCasa(casa);
                            }
                            else if(procuraNome.equals("imobiliaria")) {
                                if(imobiliaria.addSellHouse(casa));
                            } else {
                                if(casaDono.equals("imobiliaria")) { casa.setDono("!" + procuraNome); }
                                buffer.addCasa(casa);
                            }
                        }

                        if(procuraNome.equals("condominio")) {
                            procuraNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // nome do condominio
                            String condominioNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // rua do condominio
                            String condominioRua = (line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // numero maximo de casas no condominio
                            int condominioMaxCasa = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            int casasCondominio = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            Condominio condominio = new Condominio(condominioNome, condominioRua, condominioMaxCasa);

                            for(int j = 0; j < casasCondominio; j++) {
                                // dono da casa
                                String casaCondominoDono = line.substring((i), line.indexOf('|', (i+1)));
                                i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                                
                                // endereco da casa
                                int casaCondominioEndereco = (Integer.parseInt(line.substring((i), line.indexOf('|', (i+1)))));
                                i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                                if(procuraNome.equals(client.getNome())) {
                                    client.addCasaCondominio(condominio, casaCondominioEndereco,casaCondominoDono);
                                } else if(procuraNome.equals("imobiliaria"))  {
                                    condominio.addCasaCondominio(casaCondominioEndereco,casaCondominoDono);
                                } else {
                                    condominio.setName(procuraNome + "!" + condominioNome);
                                    buffer.addCasaCondominio(condominio, casaCondominioEndereco,procuraNome);
                                }
                            }

                            if(procuraNome.equals("imobiliaria")) {
                                imobiliaria.addSellCondominum(condominio);
                            }
                        }

                        /* Caso ache algum predio */
                        if(procuraNome.equals("predio")) {
                            procuraNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // nome do predio
                            String predioNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // rua do predio
                            String predioRua = (line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // endereco do predio
                            int predioEndereco = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // Numero maximo de andar do predio
                            int predioMaxAndar = (Integer.parseInt(line.substring((i), line.indexOf('|', (i+1)))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // Numero maximo de apartamento por andar do predio
                            int predioMaxAprt = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // definir se o predio tem elevador
                            boolean predioElevator = false;
                            if(line.substring((i), line.indexOf('|', (i+1))).equals("1")) { predioElevator = true; }
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // definir se o predio tem piscina
                            boolean predioPool = false;
                            if(line.substring((i), line.indexOf('|', (i+1))).equals("1")) { predioPool = true; }
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // definir se o predio tem garagem
                            boolean predioGarage = false;
                            if(line.substring((i), line.indexOf('|', (i+1))).equals("1")) { predioGarage = true; }
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            int predioAprts = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            Predio predio = new Predio(predioEndereco, predioRua, predioElevator, predioPool, predioGarage, predioMaxAndar, predioMaxAprt, predioNome);

                            for(int j = 0; j < predioAprts; j++) {
                                // Dono do apartamento
                                String aprtDono = line.substring((i), line.indexOf('|', (i+1)));
                                i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                                // andar do aprt
                                int aprtAndar = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                                i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                                //numero do aprt
                                int aprtNum = Integer.parseInt(line.substring((i), line.indexOf('|', (i+1))));
                                i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                                Apartamento aprt = new Apartamento(aprtNum, aprtAndar);
                                aprt.declararDono(aprtDono);

                                if(procuraNome.equals(client.getNome())) {
                                    client.addApartamento(predio, aprt);
                                } else if(procuraNome.equals("imobiliaria")) {
                                    predio.addSellApart(aprt);
                                } else {
                                    predio.setNamePredio(procuraNome + "!" + predioNome);
                                    buffer.addApartamento(predio, aprt);
                                }
                            }

                            if(procuraNome.equals("imobiliaria")) {
                                imobiliaria.addSellBuild(predio);
                            }
                        }

                        /* Caso ache algum terreno */
                        if(procuraNome.equals("terreno")) {
                            procuraNome = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // dono do terreno
                            String terrenoDono = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // rua do terreno
                            String terrenoRua = line.substring((i), line.indexOf('|', (i+1)));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // endereco do terreno
                            int terrenoEndereco = (Integer.parseInt(line.substring((i), line.indexOf('|', (i+1)))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            // largura do terreno
                            float terrenoLargura = Float.parseFloat(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;
                            
                            // comprimento do terreno
                            float terrenoComprimento = Float.parseFloat(line.substring((i), line.indexOf('|', (i+1))));
                            i += line.substring((i), line.indexOf('|', (i+1))).length() + 1;

                            Terreno terreno = new Terreno(terrenoEndereco, terrenoRua, terrenoLargura, terrenoComprimento);
                            terreno.setDono(terrenoDono);

                            if(procuraNome.equals(client.getNome())) {
                                client.addTerreno(terreno);
                            }
                            else if(procuraNome.equals("imobiliaria")) {
                                imobiliaria.addSellGround(terreno);
                            } else {
                                if(terrenoDono.equals("imobiliaria")) { terreno.setDono("!" + procuraNome); }
                                buffer.addTerreno(terreno);
                            }
                        }
                    }
                }
            } catch (IOException e) { e.printStackTrace(); }
        } else {
            try (FileOutputStream os = new FileOutputStream("imoveis.txt")) {
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Método no qual permite escrever um usuário em um arquivo, se arquivo não
     * for conseguir ser aberto lança um exception
     * @param saldo saldo do cliente
     * @param nome nome no cliente
     */
    public static void escreverUsuário(String saldo, String nome) {
        try {
            FileWriter fileWriter = new FileWriter("client.txt",true);
            fileWriter.write(nome + " " + saldo + "|\n");
    
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método no qual lé um usuário de um arquivo, caso ele exista retorna o nome dele
     * caso não, retornar null. Caso o arquivo não seja encontrado lançará uma exceção e criára
     * um novo;
     * @param nome nome do usuário que quer ser achado
     * @return o nome e saldo caso ele exista, null caso ele nã exista;
     * @throws FileNotFoundException lançado quando não achado o arquivo
     */
    public static String leituraUsuário(String nome) throws FileNotFoundException {
        File client = new File("client.txt");
        String line = new String();
        if(client.exists()) {
            try {
                InputStreamReader fileReader = new InputStreamReader(new FileInputStream("client.txt"));
                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String procuraNome = new String();
                    while((line = bufferedReader.readLine()) != null) {
                        procuraNome = line.substring(0, line.indexOf(' '));
                        if(procuraNome.equals(nome)) {
                            return line;
                        }
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try (FileOutputStream os = new FileOutputStream("client.txt")) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    /**
     * função main da aplicação
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        try(Scanner scanner = new Scanner(System.in)){
            Imobiliaria imobiliaria = new Imobiliaria();
            Client buffer = new Client("buffer");

            /*
            * menu de entrada, aqui o usuário poderá escolher se ele deseja se cadastrar na imobiliaria
            * caso ele não tenha cadastro, poderá entrar dentro da imobiliaria caso ele tenha cadastro
            * e poderá sair do programa.
            */
            while(true) {
                System.out.println("Bem vindo, possui cadastro de cliente?");
                System.out.println("1 - sim");
                System.out.println("2 - nao");
                System.out.println("3 - sair");
                System.out.println();
                System.out.print("Opcao: ");
                Integer num = scanner.nextInt();
                String line = scanner.nextLine();
                System.out.println();

                /*
                * caso o usuário tenha escolhido entrar, terá que dizer o nome de usuário
                * caso exista, entrará no menu principal, caso não retornar para o menu de entrada
                */
                if(num.equals(1)) {
                    String nome = new String();
                    int saldo = 0;
                    System.out.print("digite seu nome: ");
                    line = scanner.nextLine();
                    line = leituraUsuário(line);
                    if(line == null) {
                        System.out.println("Usuario nao existe");
                        System.out.println();
                    } else {
                        nome = line.substring(0, line.indexOf(' '));
                        saldo = Integer.parseInt(line.substring((line.indexOf(' ') + 1), line.indexOf('|')));
                        Client client = new Client(nome);
                        client.addSaldo(saldo);
                        System.out.println("bem vindo " + client.getNome());
                        System.out.println("Carregando . . .");
                        System.out.println();

                        /*
                        * aqui será feita a leitura do arquivo no qual contém os imoveis a venda
                        * ou dos usuários.
                        */
                        leitura(imobiliaria, client, buffer);
                        System.out.println();

                        /*
                        * menu principal do programa, aqui o usuário poderá
                        * escolher a ação que ele deseja como comprar ou alugar imoveis que estão
                        * a disposição (Casas/Apartamentos/Terrenos).
                        * Também poderá ver os imoveis que tem comprado ou alugado, assim como terá uma
                        * opção que dirá como funciona a venda e alugação dentro da imobiliaria
                        */
                        while(true) {
                            System.out.println("O que deseja?");
                            System.out.println("1 - Casa sem condominio");
                            System.out.println("2 - Casa com condominio");
                            System.out.println("3 - Apartamento");
                            System.out.println("4 - Terrenos");
                            System.out.println("5 - ver informações do cliente");
                            System.out.println("6 - ajuda");
                            System.out.println("7 - adicionar casa no seu nome");
                            System.out.println("8 - vender imovel");
                            System.out.println("9 - sair");
                            System.out.println();
                            System.out.print("Opcao: ");
                            num = scanner.nextInt();
                            System.out.println();

                            num = menu(imobiliaria, client, scanner, line, num);
                            if(num.equals(9)) {
                                break;
                            }
                        }
                        line = scanner.nextLine();
                        
                        /*
                        * nesse processo, o conteúdo do arquivo imoveis.txt será rescrito:
                        * apagará o conteúdo anterior e rescreverá os ímoveis do cliente, imobiliaria e 
                        * buffer, respectivamente.
                        */
                        FileWriter fileWriter = new FileWriter("imoveis.txt",false);
                        fileWriter.close();
                        for(int i = 0; i < client.getHaveHouse().size(); i++) { escreverCasa(client.getNome(), client.getHaveHouse().get(i)); }
                        for(int i = 0; i < client.getHaveBuild().size(); i++) { escreverPredio(client.getNome(), client.getHaveBuild().get(i)); }
                        for(int i = 0; i < client.getHaveCondominio().size(); i++) { escreverCondominio(client.getNome(), client.getHaveCondominio().get(i)); }
                        for(int i = 0; i < client.getHaveGround().size(); i++) { escreverTerreno(client.getNome(), client.getHaveGround().get(i)); }

                        for(int i = 0; i < imobiliaria.getCasa().size(); i++) { escreverCasa("imobiliaria", imobiliaria.getCasa().get(i)); }
                        for(int i = 0; i < imobiliaria.getPredio().size(); i++) { escreverPredio("imobiliaria", imobiliaria.getPredio().get(i)); }
                        for(int i = 0; i < imobiliaria.getCondominio().size(); i++) { escreverCondominio("imobiliaria", imobiliaria.getCondominio().get(i)); }
                        for(int i = 0; i < imobiliaria.getTerreno().size(); i++) { escreverTerreno("imobiliaria", imobiliaria.getTerreno().get(i)); }

                        for(int i = 0; i < buffer.getHaveHouse().size(); i++) { escreverCasa(buffer.getNome(), buffer.getHaveHouse().get(i)); }
                        for(int i = 0; i < buffer.getHaveBuild().size(); i++) { escreverPredio(buffer.getNome(), buffer.getHaveBuild().get(i)); }
                        for(int i = 0; i < buffer.getHaveCondominio().size(); i++) { escreverCondominio(buffer.getNome(), buffer.getHaveCondominio().get(i)); }
                        for(int i = 0; i < buffer.getHaveGround().size(); i++) { escreverTerreno(buffer.getNome(), buffer.getHaveGround().get(i)); }
                    }
                
                /*
                * Aqui será o menu que o usuário poderá fazer um  novo cadastro na imobiliaria
                */
                } else if(num.equals(2)) {
                    while(true) {
                        System.out.println("Deseja se cadastrar?");
                        System.out.println("1 - sim");
                        System.out.println("2 - voltar");
                        System.out.print("Opcao: ");
                        num = scanner.nextInt();
                        line = scanner.nextLine();
                        System.out.println();
                        if(num.equals(1)) {
                            System.out.println("fazer cadastro");
                            System.out.print("Escreva seu nome sem espaços: ");
                            String nome = scanner.nextLine();
                            System.out.println();

                            System.out.println("O valor do seu saldo será o valor");
                            System.out.println("do seu salário multiplicado por 1000.");
                            System.out.print("Escreva seu salário: ");
                            String saldo = Integer.toString(Integer.parseInt(scanner.nextLine())*1000);
                            System.out.println("\n");

                            if(leituraUsuário(nome) == null) { escreverUsuário(saldo, nome); }
                            else { System.out.println("Ja existe usuário com esse nome, escolha outro.\n"); }
                            break;
                        }
                        else if(num.equals(2)) {
                            break;
                        }
                        else {
                            System.out.println("Resposta não identificada");
                        }
                    }
                } else if(num.equals(3)){
                    break;
                } else {
                    System.out.println("Resposta não identificada");
                }
            }
        } catch (MsgException me) {
            System.out.println(me.getMessage());
        }
    }
}