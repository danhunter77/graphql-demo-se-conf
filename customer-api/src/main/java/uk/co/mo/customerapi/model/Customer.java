package uk.co.mo.customerapi.model;

public record Customer (Integer id,
                        String title,
                        String firstname,
                        String lastname,
                        String emailAddress,
                        Integer agreementId) {
}
