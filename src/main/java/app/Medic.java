package app;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Medic")
@Table(name = "medici")
public class Medic implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nume;

    @Column
    private String prenume;

    @Column
    private String cnp;

    @Column
    private String telefon;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "medici_servicii",
            joinColumns = @JoinColumn(name = "medici_id"),
            inverseJoinColumns = @JoinColumn(name = "servicii_id")
    )
    private List<Serviciu> servicii = new ArrayList<>();


    public Medic(){}

    public Medic(String nume,String prenume, String cnp, String telefon){
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.telefon = telefon;
    }

    public List<Serviciu> getServicii() {
        return servicii;
    }

    public void setServicii(List<Serviciu> servicii) {
        this.servicii = servicii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medic)) return false;
        return id != null && id.equals(((Medic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
