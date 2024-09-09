package uk.co.mo.agreementapi.datafetchers;

import com.netflix.graphql.dgs.*;
import uk.co.mo.types.Agreement;
import uk.co.mo.types.Customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DgsComponent
public class AgreementDataFetcher {

    private final Map<String, List<Agreement>> agreements = new HashMap<>();

    public AgreementDataFetcher() {
        List<Agreement> agreementList1 = List.of(
                new Agreement(1111, LocalDate.now(), LocalDate.now().plusYears(3), Boolean.TRUE),
                new Agreement(2222, LocalDate.now().minusYears(6), LocalDate.now().minusYears(3), Boolean.FALSE));

        List<Agreement> agreementList2 = List.of(
                new Agreement(3333, LocalDate.now(), LocalDate.now().plusYears(3), Boolean.TRUE));

        List<Agreement> agreementList3 = List.of(
                new Agreement(4444, LocalDate.now(), LocalDate.now().plusYears(3), Boolean.TRUE));

        agreements.put("123", agreementList1);
        agreements.put("222", agreementList2);
        agreements.put("456", agreementList3);
    }

    @DgsQuery
    public Agreement agreement(@InputArgument Integer id) {

        List<Agreement> agreementList = agreements.values().stream().flatMap(List::stream).toList();

        return agreementList.stream().filter(agreement -> id.equals(agreement.getId())).findFirst().orElse(null);
    }

    @DgsEntityFetcher(name = "Customer")
    public Customer customer(Map<String, Object> values) {
        return new Customer((String) values.get("id"), null);
    }

    @DgsData(parentType = "Customer", field = "agreements")
    public List<Agreement> agreementsFetcher(DgsDataFetchingEnvironment dfe) {
        Customer customer = dfe.getSource();

        return customer != null ? agreements.get(customer.getId()) : null;
    }

}
