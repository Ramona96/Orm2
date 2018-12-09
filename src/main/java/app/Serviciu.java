package app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Serviciu")
@Table(name = "servicii")

public class Serviciu implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String denumire;

    @ManyToMany(mappedBy = "servicii")
    private List<Medic> medici = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "servicii_produse",
            joinColumns = @JoinColumn(name = "servicii_id"),
            inverseJoinColumns = @JoinColumn(name = "produse_id")
    )
    private List<Produs> produse = new ArrayList<>();


    public List<Medic> getMedici() {
        return medici;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setMedici(List<Medic> medici) {
        this.medici = medici;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    public Serviciu(){}

    public Serviciu(String denumire){
        this.denumire = denumire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serviciu)) return false;
        return id != null && id.equals(((Serviciu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }



}
