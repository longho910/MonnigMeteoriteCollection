package edu.tcu.cs.monnigmeteoritecollection.system;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.LoanService;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.MeteoriteService;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistoryService;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {
    
    private final MeteoriteService meteoriteService;
    private final LoanService loanService;
    private final SampleHistoryService sampleHistoryService;

    private final UserService userService;

    public DBDataInitializer(MeteoriteService meteoriteService, SampleHistoryService sampleHistoryService, LoanService loanService, UserService userService) {
        this.meteoriteService = meteoriteService;
        this.sampleHistoryService = sampleHistoryService;
        this.loanService = loanService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeMeteorites();
        System.out.println("METEORITES DB INIT");
        initializeLoans();
        System.out.println("LOANS DB INIT");
        initializeHistories();
        System.out.println("HISTORIES DB INIT");
        initializeUsers();
        System.out.println("USERS DB INIT");
    }

    public void initializeUsers() {
        // Create some users.
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("john");
        u1.setPassword("123");
        u1.setEnabled(true);
        u1.setRoles("curator user");

        User u2 = new User();
        u2.setId(2);
        u2.setUsername("eric");
        u2.setPassword("123");
        u2.setEnabled(true);
        u2.setRoles("user");

        User u3 = new User();
        u3.setId(3);
        u3.setUsername("tom");
        u3.setPassword("123");
        u3.setEnabled(false);
        u3.setRoles("user");

        this.userService.save(u1);
        this.userService.save(u2);
        this.userService.save(u3);
    }

    public void initializeMeteorites() {
        // Name	Monnig Number	Country	Class	Group	Year Found	Sample Weight (g)
        // Abbott	M398.1	USA	Ordinary Chondrite	H	1951	325.1
        Meteorite meteorite1 = new Meteorite();
        meteorite1.setId(1L);
        meteorite1.setName("Abbott");
        meteorite1.setMonnigNumber("M398.1");
        meteorite1.setCountry("USA");
        meteorite1.set_class("Ordinary Chondrite");
        meteorite1.set_group("H");
        meteorite1.setYearFound(1951);
        meteorite1.setWeight(BigDecimal.valueOf(325.1));
        meteorite1.setHowFound("Fall");

//        Abee	M499.2	Canada	Enstatite Chondrite	EH	1952	785.9

        Meteorite meteorite2 = new Meteorite();
        meteorite2.setId(2L);
        meteorite2.setName("Abee");
        meteorite2.setMonnigNumber("M499.2");
        meteorite2.setCountry("Canada");
        meteorite2.set_class("Enstatite Chondrite");
        meteorite2.set_group("EH");
        meteorite2.setYearFound(1952);
        meteorite2.setWeight(BigDecimal.valueOf(785.9));
        meteorite2.setHowFound("Find");


        Meteorite meteorite3 = new Meteorite();
        meteorite3.setId(3L);
        meteorite3.setName("Abernathy");
        meteorite3.setMonnigNumber("M239.1");
        meteorite3.setCountry("USA");
        meteorite3.set_class("Ordinary Chondrite");
        meteorite3.set_group("L");
        meteorite3.setYearFound(1941);
        meteorite3.setWeight(BigDecimal.valueOf(453.1));
        meteorite3.setHowFound("Fall");


        Meteorite meteorite4 = new Meteorite();
        meteorite4.setId(4L);
        meteorite4.setName("Abu Moharek");
        meteorite4.setMonnigNumber("M787.1");
        meteorite4.setCountry("Egypt");
        meteorite4.set_class("Ordinary Chondrite");
        meteorite4.set_group("H");
        meteorite4.setYearFound(1997);
        meteorite4.setWeight(BigDecimal.valueOf(56.4));
        meteorite4.setHowFound("Fall");

        //        Acapulco	M901.1	Mexico	Primitive Achondrite	Acapulcoite	1976	0.4
        Meteorite meteorite5 = new Meteorite();
        meteorite5.setId(5L);
        meteorite5.setName("Acapulco");
        meteorite5.setMonnigNumber("M901.1");
        meteorite5.setCountry("Mexico");
        meteorite5.set_class("Primitive Achondrite");
        meteorite5.set_group("Acapulcoite");
        meteorite5.setYearFound(1976);
        meteorite5.setWeight(BigDecimal.valueOf(0.4));
        meteorite5.setHowFound("Find");

        meteoriteService.save(meteorite1);
        meteoriteService.save(meteorite2);
        meteoriteService.save(meteorite3);
        meteoriteService.save(meteorite4);
        meteoriteService.save(meteorite5);
    }

    public void initializeLoans() {
        Loan loan1 = new Loan();
        loan1.setName("Loan1");
        loan1.setInstitution("TCU");
        loan1.setEmail("null@gmail.com");
        loan1.setPhone("999-999-9999");
        loan1.setAddress("1111 North Main Street, Fort Worth, Texas");
        loan1.setLoanStartDate("2024-04-24");
        loan1.setLoanDueDate("2025-04-24");

        loan1.setArchived(false);
        
        loan1.setNotes("some notes");
        loan1.setExtraFiles("extra files");

        Loan savedLoan = loanService.save(loan1);

        // get saved loan's ID
        Integer loanId = savedLoan.getId();

        // create empty Loan
        Loan emptyLoan = new Loan();

        // add Meteorite List to this loan - containing meteorite with id == 1
        List<Meteorite> meteoriteList = new ArrayList<>();
        meteoriteList.add(meteoriteService.findById(1L));
        emptyLoan.setMeteorites(meteoriteList);
        
        loanService.update(String.valueOf(loanId), emptyLoan);
    }

    public void initializeHistories() {
        SampleHistory history1 = new SampleHistory();
        history1.setDate("04-24-2024");
        history1.setCategory("Example category");
        history1.setNotes("Some notes");
        history1.setMeteorite(meteoriteService.findById(3L));

        sampleHistoryService.save(history1);
    }

}
