package app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Uom")
@Table(name = "uom")

public class Uom implements Serializable {

    @Id
    @GeneratedValue 
    private Long id;

    @Column(unique = true)
    private String text;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "uom_id")
    private List<Produs> produse = new ArrayList<>();

    public Uom(){}

    public Uom(String text){
        this.text = text;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uom)) return false;
        return id != null && id.equals(((Uom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
