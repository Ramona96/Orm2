package app;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "SeviciuCumparat")
@Table(name = "servicii_cumparate")

public class ServiciuCumparat implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    @Column
    private Date dataAleasa;


    @Column
    private Client clientFk;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Serviciu serviciuFk;

    public ServiciuCumparat(){}

    public ServiciuCumparat(Date dataAleasa){
        this.dataAleasa = dataAleasa;
    }

    public void setClientFk(Client clientFk) {
        this.clientFk = clientFk;
    }

    public void setServiciuFk(Serviciu serviciuFk) {
        this.serviciuFk = serviciuFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiciuCumparat)) return false;
        return id != null && id.equals(((ServiciuCumparat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
