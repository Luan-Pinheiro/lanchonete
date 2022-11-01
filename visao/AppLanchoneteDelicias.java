
package visao;
import javax.swing.JOptionPane;
import java.util.Date;
import controle.*;
import modelo.*;

public class AppLanchoneteDelicias {
    public static  String menu(){
        return "Escolha uma opção:\n"
                + "1 - Cadastrar produto\n"
                + "2 - Cadastrar Pedido\n"
                + "3 - Inserir Item no pedido\n"
                + "4 - Remover pedido\n" 
                + "5 - Listar pedido\n"
                + "0 - Sair";
    }

    public static void main(String[] args) {

        ControlePedido cPed = new ControlePedido();
        ControleProduto cPrd = new ControleProduto();

        int escolha;
        int cont = 0;

        escolha= Integer.parseInt(JOptionPane.showInputDialog(menu()));

        while(escolha!=0){
            switch(escolha){
                case 1: {
                    String nome= JOptionPane.showInputDialog(null, "Informe o nome do produto: ");
                    String descricao= JOptionPane.showInputDialog(null, "Descricao do produto: ");
                    int quantidade= Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade desse produto no estoque: "));
                    String tipo= JOptionPane.showInputDialog(null, "Informe o tipo do produto (salgado, bebida, doce, etc): ");
                    String valor= JOptionPane.showInputDialog(null, "Informe o valor de compra do produto: ");
                    if(valor.contains(",")){
                        JOptionPane.showMessageDialog(null, "Erro, dados incorretos!");
                        break;
                    }
                    else{
                        double valorDouble; 
                        valorDouble = Double.parseDouble(valor);
                        cPrd.cadastrarProduto(nome, tipo, descricao, quantidade, valorDouble);
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    }
                    
                    break;
                }
                case 2: {
                    Date hoje = new Date();
                    cPed.cadastrarPedido(hoje);
                    JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!" + "\nCódigo do pedido: " + cPed.getPedidos().get(cont).getNumero());
                    cont++;
                    break;
                }
                case 3:{
                    int numero= Integer.parseInt(JOptionPane.showInputDialog("Informe o numero do pedido: "));
                    Pedido pedido = cPed.pesquisarPedido(numero);
                    if(pedido==null){
                        JOptionPane.showMessageDialog(null, "Erro!\n"+
                                                    "Pedido invalido!\n Tente novamente.");
                        break;
                    }
                    else{
                        int numeroProduto= Integer.parseInt(JOptionPane.showInputDialog("Informe o numero do item\n"+
                        " para ser adicionado no pedido: "));
                        Produto produto = cPrd.pesquisarProduto(numeroProduto);
                        if(produto==null){
                            JOptionPane.showMessageDialog(null, "Erro!\n"+
                            "Pedido invalido!\n Tente novamente.");
                            break;
                        }
                        else{
                            int quantidade= Integer.parseInt(JOptionPane.showInputDialog("Informe quantos produtos desse tipo\ndeseja adicionar ao pedido: "));
                            Item item = cPed.adicionarItem(numero, produto, quantidade);
                            JOptionPane.showMessageDialog(null, "Item inserido com sucesso!" + item.imprimirItem());
                            break;
                        }
                    }
                }
                case 4: {
                    int numeroPedido = Integer.parseInt(JOptionPane.showInputDialog("Insira o número do pedido que deseja remover: "));
                    Pedido pedido = cPed.pesquisarPedido(numeroPedido);
                    if(pedido==null){
                        JOptionPane.showMessageDialog(null, "Erro!\n"+
                                                    "Pedido invalido!\n Tente novamente.");
                        break;
                    }
                    else{
                        int aux = cPed.getPedidos().size();
                        cPed.removerPedido(numeroPedido);
                        if(cPed.getPedidos().size() == aux -1){
                            JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");
                        }
                        break;
                    }
                }
                case 5: {
                    int numero= Integer.parseInt(JOptionPane.showInputDialog("Insira o número do pedido: "));
                    Pedido pedido = cPed.pesquisarPedido(numero);
                    if(numero <= cPed.getPedidos().size()){
                        if(pedido==null){
                            JOptionPane.showMessageDialog(null, "Erro!\n"+
                                                        "Pedido invalido!\n Tente novamente.");
                            break;
                        }
                        else{
                            String itens = "";
                            for(int i = 0; i <cPed.getPedidos().get(numero-1).getItens().size(); i++){
                                if(cPed.getPedidos().get(numero-1).getItens().get(i) != null)
                                    itens += cPed.getPedidos().get(numero-1).getItens().get(i).imprimirItem() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, cPed.imprimirPedido(numero) + "\n  --ITENS DO PEDIDO-- \n" + itens + "--------------------------");
                            break;
                        }   
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro!\n"+"Pedido invalido!\n Tente novamente.");
                    }
                    break;
                }
            }
            escolha= Integer.parseInt(JOptionPane.showInputDialog(menu()));
        }
        
    }    
}

