package edu.tcu.cs.monnigmeteoritecollection.loan;
import org.springframework.data.jpa.domain.Specification;

public class LoanSpecs {

    public static Specification<Loan> containsName(String providedName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%" + providedName.toLowerCase() + "%");
    }

    public static Specification<Loan> containsInstitution(String providedInstitution) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("institution")),
                "%" + providedInstitution.toLowerCase() + "%");
    }

    public static Specification<Loan> containsEmail(String providedEmail) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                "%" + providedEmail.toLowerCase() + "%");
    }

    public static Specification<Loan> containsPhone(String providedPhone) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")),
                "%" + providedPhone.toLowerCase() + "%");
    }

    public static Specification<Loan> containsAddress(String providedAddress) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("address")),
                "%" + providedAddress.toLowerCase() + "%");
    }

    public static Specification<Loan> isArchived(Boolean providedArchived) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isArchived"), providedArchived);
    }



}
