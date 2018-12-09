package app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
@Table(name = "clienti")

public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nume;

    @Column
    private String prenume;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "client_id")
    private List<ServiciuCumparat> servicii_cumparate = new ArrayList<>();

    public Client(){}

    public Client(String nume, String prenume){
        this.nume = nume;
        this.prenume = prenume;
    }

    public List<ServiciuCumparat> getServicii_cumparate() {
        return servicii_cumparate;
    }

    public void setServicii_cumparate(List<ServiciuCumparat> servicii_cumparate) {
        this.servicii_cumparate = servicii_cumparate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
