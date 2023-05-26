package de.hhu.accso.warenkorb.hexagonal.domain.stereotypes;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AggregateRoot {
}
