package telas;

import modelo.Funcionario;
import modelo.Cliente;
import modelo.VendaItem;

public interface CarrinhoManager {
    void setFuncionario(Funcionario funcionario);
    void setCliente(Cliente cliente);
    void addItem(VendaItem item);
    void removeItem(int rowIndex);
    void updateCarrinhoUI();
    Integer getIdVenda();
}