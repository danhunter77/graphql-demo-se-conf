package uk.co.mo.customerapi.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import uk.co.mo.customerapi.model.Customer;

import java.util.List;

@DgsComponent
public class CustomerDataFetcher {

    private final List<Customer> customers = List.of(
            new Customer(123, "Mrs", "Carol", "Shaw", "carol.s@example.com", 1111),
            new Customer(222, "Mrs", "Carol", "Moore", "carol.m@example.com", 2222),
            new Customer(456, "Mr", "Fred", "Dibnah", "fred.d@example.com", 3333),
            new Customer(789, "Miss", "Parisa", "Tabriz", "parisa.t@example.com", 4444),
            new Customer(1111, "Mr", "Andrew", "Eldritch", "andrew.e@example.com", 5555),
            new Customer(2222, "Miss", "Patricia", "Morrison", "patrica.m@example.com", 6666)
    );

    @DgsQuery
    public Customer customer(@InputArgument Integer idFilter) {

        return customers.stream().filter(c -> c.id().equals(idFilter)).findFirst().orElse(null);
    }

}
