package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "VENDEDOR")
public class Vendedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ANIVERSARIO")
    private Date aniv;
    @Column(name = "SALARIO")
    private Double salario;
    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO")
    private Departamento departamento;
    public Vendedor(Integer id, String nome, String email, Date aniv, Double salario, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.aniv = aniv;
        this.salario = salario;
        this.departamento = departamento;
    }
    public Vendedor(){
    }
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Date getAniv() {return aniv;}
    public void setAniv(Date aniv) {this.aniv = aniv;}
    public Double getSalario() {return salario;}
    public void setSalario(Double salario) {this.salario = salario;}
    public Departamento getDepartamento() {return departamento;}
    public void setDepartamento(Departamento departamento) {this.departamento = departamento;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return id.equals(vendedor.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", aniv=" + aniv +
                ", salario=" + salario +
                ", departamento=" + departamento +
                '}';
    }
}
