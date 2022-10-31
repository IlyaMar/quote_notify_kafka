package org.imartynov.quote.input.initializers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;


@ConfigurationProperties(prefix = "index")      // read properties with given prefix
@Validated              // validate with JSR-380. at startup.
@ConstructorBinding     // create with all-args constructor. immutable.
public class IndexProperties {
    @Positive
    private final int customerShardNumber;  // index sharded by customer_id

    public IndexProperties(@Positive int customerShardNumber) {
        this.customerShardNumber = customerShardNumber;
    }

    public int getCustomerShardNumber() {
        return customerShardNumber;
    }
}
