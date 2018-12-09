package app;

import repository.GenericRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static util.Hibernate.*;


public class Main {

    public static void main(String [] args) throws ParseException {

        GenericRepository<Client> clientRepo = new GenericRepository<>(Client.class);
        GenericRepository<Uom> uomRepo = new GenericRepository<>(Uom.class);
        GenericRepository<Produs> prodRepo = new GenericRepository<>(Produs.class);
        GenericRepository<Serviciu> serviciuRepo = new GenericRepository<>(Serviciu.class);
        GenericRepository<Medic> medicRepo = new GenericRepository<>(Medic.class);
        GenericRepository<ServiciuCumparat> serviciuCumparatRepo = new GenericRepository<>(ServiciuCumparat.class);

        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        getEM();

        Uom uom = new Uom("g");
        uom = uomRepo.create(uom);


        Uom uom2 = new Uom("ml");
        uom2 = uomRepo.create(uom2);


        Produs prod = new Produs("prod1",1L,11L,10L);
        prod = prodRepo.create(prod);

        Produs prod2 = new Produs("prod2",2L,22L,3L);
        prod2 = prodRepo.create(prod2);

        prod = prodRepo.create(prod);
        prod2 = prodRepo.create(prod2);



        Serviciu serviciu = new Serviciu("serv1");
        serviciu.setProduse(Arrays.asList(prod));

        Serviciu serviciu2 = new Serviciu("serv2");
        serviciu2.setProduse(Arrays.asList(prod, prod2));

        serviciu = serviciuRepo.create(serviciu);
        serviciu2 = serviciuRepo.create(serviciu2);



        Medic medic = new Medic("med1Name","med1Prenume","11111111","1122552");
        medic.setServicii(Arrays.asList(serviciu));

        Medic medic2 = new Medic("med2Name","med2Prenume","2222222","12545585");
        medic2.setServicii(Arrays.asList(serviciu, serviciu2));

        medic = medicRepo.create(medic);
        medic2 = medicRepo.create(medic2);


        ServiciuCumparat serviciuCumparat = new ServiciuCumparat(fmt.parse("11/11/2019"));
        serviciuCumparat.setServiciuFk(serviciu);

        ServiciuCumparat serviciuCumparat2 = new ServiciuCumparat(fmt.parse("15/12/2018"));
        serviciuCumparat2.setServiciuFk(serviciu2);

        serviciuCumparat = serviciuCumparatRepo.create(serviciuCumparat);
        serviciuCumparat2 = serviciuCumparatRepo.create(serviciuCumparat2);




        Client client = new Client("clint1Name","client1Prenume");
        client.setServicii_cumparate(Arrays.asList(serviciuCumparat));


        Client client2 = new Client("client1Name","client2Prenume");
        client2.setServicii_cumparate(Arrays.asList(serviciuCumparat2));


        client = clientRepo.create(client);
        client2 = clientRepo.create(client2);



        //Inainte de REPOSITORY

//        Medic medic1 = new Medic("Marin","Ana","2901522","075558");
//       Serviciu serviciu1 = new Serviciu("Examen psihologic");
//       Produs prod1 = new Produs();
//       Uom uom1 = new Uom();
//
//       save(medic1);
//       save(serviciu1);

       closeEMF();


    }
}
