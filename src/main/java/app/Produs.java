package app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Produs")
@Table(name = "produse")

public class Produs implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nume;

    @Column
    private Long pret;

    @Column
    private Long stoc;

    @Column
    private Long cantitate;

    @Column
    private String uomFK;

    @ManyToMany(mappedBy = "produse")
    private List<Serviciu> servicii = new ArrayList<>();


    public Produs(){}

    public Produs(String nume,Long pret,Long stoc,Long cantitate){
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
        this.cantitate = cantitate;
    }

    public void setServicii(List<Serviciu> servicii) {
        this.servicii = servicii;
    }

    public List<Serviciu> getServicii() {
        return servicii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs)) return false;
        return id != null && id.equals(((Produs) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
