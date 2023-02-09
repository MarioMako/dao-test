package query;

import dao.VendedorDAO;
import db.DB;
import db.DbException;
import entidades.Departamento;
import entidades.Vendedor;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendedorQuery implements VendedorDAO {

    private Connection conn;

    public VendedorQuery(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("" +
                    "INSERT INTO VENDEDOR " +
                    "(NOME, EMAIL, SALARIO, ANIVERSARIO, DEPARTAMENTO_ID) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?) ",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,obj.getNome());
            ps.setString(2,obj.getEmail());
            ps.setDouble(3,obj.getSalario());
            ps.setDate(4,new java.sql.Date(obj.getAniv().getTime()));
            ps.setInt(5,obj.getDepartamento().getId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected>0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("nenhuma row afetada");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Vendedor obj) {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("" +
                            "UPDATE VENDEDOR " +
                            "SET NOME = ?, EMAIL = ?, SALARIO = ?, ANIVERSARIO = ?, DEPARTAMENTO_ID = ? " +
                            "WHERE ID = ? ",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,obj.getNome());
            ps.setString(2,obj.getEmail());
            ps.setDouble(3,obj.getSalario());
            ps.setDate(4,new java.sql.Date(obj.getAniv().getTime()));
            ps.setInt(5,obj.getDepartamento().getId());
            ps.setInt(6,obj.getId());

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("DELETE FROM VENDEDOR WHERE ID = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs =  null;
        try{
            ps = conn.prepareStatement(
                    "SELECT VENDEDOR.*,DEPARTAMENTO.NOME as DEPARTAMENTO_NOME "+
                    "FROM VENDEDOR INNER JOIN DEPARTAMENTO "+
                    "ON VENDEDOR.DEPARTAMENTO_ID = DEPARTAMENTO.ID "+
                    "WHERE VENDEDOR.ID = ?"
            );

            ps.setInt(1,id);
            rs= ps.executeQuery();

            if(rs.next()){
                Departamento dep = instanciarDepartamento(rs);
                Vendedor obj = instanciarVendedor(rs, dep);
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

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setSalario(rs.getDouble("salario"));
        obj.setAniv(rs.getDate("aniversario"));
        obj.setDepartamento(dep);
        return obj;
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("departamento_id"));
        dep.setNome(rs.getString("departamento_nome"));
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        PreparedStatement ps = null;
        ResultSet rs =  null;
        try{
            ps = conn.prepareStatement(
                    "SELECT VENDEDOR.*,DEPARTAMENTO.NOME as DEPARTAMENTO_NOME " +
                            "FROM VENDEDOR INNER JOIN DEPARTAMENTO " +
                            "ON VENDEDOR.DEPARTAMENTO_ID = DEPARTAMENTO.ID " +
                            "ORDER BY ID"
            );

            rs= ps.executeQuery();
            List<Vendedor> lista = new ArrayList<>();
            Map<Integer,Departamento> map = new HashMap<>();

            while(rs.next()){
                Departamento dep = map.get(rs.getInt("DEPARTAMENTO_ID"));
                if(dep == null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DEPARTAMENTO_ID"),dep);
                }
                Vendedor obj = instanciarVendedor(rs, dep);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement ps = null;
        ResultSet rs =  null;
        try{
            ps = conn.prepareStatement(
                    "SELECT VENDEDOR.*,DEPARTAMENTO.NOME as DEPARTAMENTO_NOME " +
                            "FROM VENDEDOR INNER JOIN DEPARTAMENTO " +
                            "ON VENDEDOR.DEPARTAMENTO_ID = DEPARTAMENTO.ID " +
                            "WHERE DEPARTAMENTO_ID = ? " +
                            "ORDER BY NOME"
            );

            ps.setInt(1,departamento.getId());
            rs= ps.executeQuery();
            List<Vendedor> lista = new ArrayList<>();
            Map<Integer,Departamento> map = new HashMap<>();

            while(rs.next()){
                Departamento dep = map.get(rs.getInt("DEPARTAMENTO_ID"));
                if(dep == null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DEPARTAMENTO_ID"),dep);
                }
                Vendedor obj = instanciarVendedor(rs, dep);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
