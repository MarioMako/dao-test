package query;

import dao.VendedorDAO;
import db.DB;
import db.DbException;
import entidades.Departamento;
import entidades.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class VendedorQuery implements VendedorDAO {

    private Connection conn;

    public VendedorQuery(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs =  null;
        try{
            ps = conn.prepareStatement(
                    "SELECT VENDEDOR.*,DEPARTAMENTO.NOME as DEPARTAMENTO_NOME "+
                    "FROM DEPARTAMENTO INNER JOIN DEPARTAMENTO "+
                    "ON VENDEDOR.DEPARTAMENTO_ID = DEPARTAMENTO.ID "+
                    "WHERE VENDEDOR.ID = ?SELECT VENDEDOR.*,DEPARTAMENTO.NOME"
            );

            ps.setInt(1,id);
            rs= ps.executeQuery();

            if(rs.next()){
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("departamento_id"));
                dep.setNome(rs.getString("departamento_nome"));
                Vendedor obj = new Vendedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSalario(rs.getDouble("salario"));
                obj.setAniv(rs.getDate("aniversario"));
                obj.setDepartamento(dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
         }
    }

    @Override
    public List<Vendedor> findAll() {
        return null;
    }
}
