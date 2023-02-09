import dao.DaoFactory;
import dao.VendedorDAO;
import entidades.Vendedor;

public class Programm {
    public static void main(String[] args){
        VendedorDAO vendedorDAO = DaoFactory.createVendedorDAO();
        Vendedor vendedor = vendedorDAO.findById(3);
        System.out.println(vendedor);
    }
}
