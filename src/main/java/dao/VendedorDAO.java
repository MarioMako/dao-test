package dao;

import entidades.Departamento;
import entidades.Vendedor;

import java.util.List;

public interface VendedorDAO {
    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findById(Integer id);
    List<Vendedor> findAll();
    List<Vendedor> findByDepartamento(Departamento departamento);
}
