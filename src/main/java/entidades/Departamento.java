package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    public Departamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Departamento() {
    }
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
