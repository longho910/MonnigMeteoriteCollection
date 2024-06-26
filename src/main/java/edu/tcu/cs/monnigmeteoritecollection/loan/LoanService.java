package edu.tcu.cs.monnigmeteoritecollection.loan;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.MeteoriteRepository;
import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final MeteoriteRepository meteoriteRepository;

    public LoanService(LoanRepository loanRepository, MeteoriteRepository meteoriteRepository) {
        this.loanRepository = loanRepository;
        this.meteoriteRepository = meteoriteRepository;
    }

    public List<Loan> findAll() {
        return this.loanRepository.findAll();
    }

    public List<Loan> findAllNonArchived() {
        List<Loan> loanList = this.loanRepository.findAll();
        for (Loan elem : loanList) {
            // remove all ARCHIVED
            if (elem.isArchived()) {
                loanList.remove(elem);
            }
        }
        return loanList;
    }

    public List<Loan> findAllArchived() {
        List<Loan> loanList = this.loanRepository.findAll();
        for (Loan elem : loanList) {
            // remove all NON-ARCHIVED
            if (!(elem.isArchived())) {
                loanList.remove(elem);
            }
        }
        return loanList;
    }

    public Loan findById(String loanId) {
        return this.loanRepository.findById(Integer.valueOf(loanId))
                .orElseThrow(()->new ObjectNotFoundException("loan", String.valueOf(loanId)));
    }

    public Loan save(Loan loan) {
        // important to first check if any meteorites are coming in on this new loan
        if ( ! (loan.getMeteorites().isEmpty())  ) {
            for (Meteorite elem : loan.getMeteorites()) {
                // first make sure this meteorite is in database
                Meteorite old = this.meteoriteRepository.findById(Long.valueOf(elem.getId()))
                    .orElseThrow(() -> new ObjectNotFoundException("meteorite", String.valueOf(elem.getId())));
                // update the loan to equal the incoming
                old.setLoan(loan);
                this.meteoriteRepository.save(old);
            }
        }

        return this.loanRepository.save(loan);
    }

    public Loan update(String loanId, Loan update) {
        Loan oldLoan = this.loanRepository.findById(Integer.valueOf(loanId))
            .orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
        if (update.getName() != null) {
            oldLoan.setName(update.getName());
        }
        if (update.getInstitution() != null) {
            oldLoan.setInstitution(update.getInstitution());
        }
        if (update.getEmail() != null) {
            oldLoan.setEmail(update.getEmail());
        }
        if (update.getPhone() != null) {
            oldLoan.setPhone(update.getPhone());
        }
        if (update.getAddress() != null) {
            oldLoan.setAddress(update.getAddress());
        }
        if (update.getLoanDueDate() != null) {
            oldLoan.setLoanDueDate(update.getLoanDueDate());
        }
        if (update.getNotes() != null) {
            oldLoan.setNotes(update.getNotes());
        }
        if (!(update.getMeteorites().isEmpty())) {
            oldLoan.setMeteorites(update.getMeteorites());
        }
        if (update.isArchived() != null) {
            oldLoan.setArchived(update.isArchived());
        }

        return this.loanRepository.save(oldLoan);
    }

    public void delete(Integer loanId) {
        this.loanRepository.deleteById(loanId);
    }

    // add paging ability - enhanced search
    public Page<Loan> findByCriteria(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<Loan> spec = Specification.where(null);

        if (StringUtils.hasLength(searchCriteria.get("name"))) {
            spec = spec.and(LoanSpecs.containsName(searchCriteria.get("name")));
        }

        if (StringUtils.hasLength(searchCriteria.get("institution"))) {
            spec = spec.and(LoanSpecs.containsInstitution(searchCriteria.get("institution")));
        }


        if (StringUtils.hasLength(searchCriteria.get("email"))) {
            spec = spec.and(LoanSpecs.containsEmail(searchCriteria.get("email")));
        }


        if (StringUtils.hasLength(searchCriteria.get("phone"))) {
            spec = spec.and(LoanSpecs.containsPhone(searchCriteria.get("phone")));
        }

        if (StringUtils.hasLength(searchCriteria.get("address"))) {
            spec = spec.and(LoanSpecs.containsAddress(searchCriteria.get("address")));
        }

        if (StringUtils.hasLength(searchCriteria.get("isArchived"))) {
            String providedStatus = searchCriteria.get("isArchived");
            if (providedStatus.equals("false")) {
                spec = spec.and(LoanSpecs.isArchived(false));
            } else {
                // if we don't mark isArchived to be false, then we want to include BOTH archived and non-archived
            }

        }

        return this.loanRepository.findAll(spec, pageable);
    }

}