package edu.tcu.cs.monnigmeteoritecollection.meteorite;
import org.springframework.data.jpa.domain.Specification;

public class MeteoriteSpecs {
    public static Specification<Meteorite> hasMonnigNumber(String providedNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("monnigNumber"), providedNumber);
    }
    public static Specification<Meteorite> containsName(String providedName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%" + providedName.toLowerCase() + "%");
    }

    public static Specification<Meteorite> containsClass(String providedClass) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("_class")),
                "%" + providedClass.toLowerCase() + "%");
    }

    public static Specification<Meteorite> containsGroup(String providedGroup) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("_group")),
                "%" + providedGroup.toLowerCase() + "%");
    }

    public static Specification<Meteorite> containsCountry(String providedCountry) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("country")),
                "%" + providedCountry.toLowerCase() + "%");
    }
    public static Specification<Meteorite> hasHowFound(String providedHowFound) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("howFound")), providedHowFound.toLowerCase());
    }



}
