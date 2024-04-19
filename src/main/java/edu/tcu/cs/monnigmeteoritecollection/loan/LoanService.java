package edu.tcu.cs.monnigmeteoritecollection.loan;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {

    private final LoanRepository LoanRepository;

    public LoanService(LoanRepository LoanRepository) {
        this.LoanRepository = LoanRepository;
    }

    public List<Loan> findAll() {
        return this.LoanRepository.findAll();
    }
}
