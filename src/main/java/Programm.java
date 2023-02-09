import dao.DaoFactory;
import dao.VendedorDAO;
import entidades.Departamento;
import entidades.Vendedor;

import java.util.*;

public class Programm {
    public static void main(String[] args){
        VendedorDAO vendedorDAO = DaoFactory.createVendedorDAO();

        System.out.println("======TEST 1: FIND BY ID=========");
        Vendedor vendedor = vendedorDAO.findById(3);
        System.out.println(vendedor);
        System.out.println("======TEST 2: FIND BY DEPARTAMENT====");
        Departamento dep = new Departamento(2,null);
        List<Vendedor> listaDep = vendedorDAO.findByDepartamento(dep);
        for(Vendedor obj: listaDep){
            System.out.println(obj);
        }
        System.out.println("========TEST 3: FIND ALL=======");
        List<Vendedor> lista = vendedorDAO.findAll();
        for(Vendedor obj: lista){
            System.out.println(obj);
        }

/*
        System.out.println("=====TEST 4: INSERT=========");
        Departamento dep2 = new Departamento(2,null);
        Vendedor newvendedor = new Vendedor(null,"Peterino","peter@gmail.com",new Date(),6666.00,dep);
        vendedorDAO.insert(newvendedor);
        System.out.println("INSERTED! Novo ID:\n "+newvendedor);


 */
        System.out.println("=====TEST 5: UPDATE======");;
        vendedor = vendedorDAO.findById(1);
        vendedor.setNome("Mikito Hérison");
        vendedorDAO.update(vendedor);
        System.out.println("UPDATED");

        System.out.println("====TEST 6: DELETE======");
        vendedorDAO.deleteById(8);
        System.out.println("DELETED");
    }
}
