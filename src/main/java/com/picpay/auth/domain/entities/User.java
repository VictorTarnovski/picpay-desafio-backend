package com.picpay.auth.domain.entities;

import com.picpay.auth.domain.enums.UserType;
import com.picpay.auth.domain.events.UserCreated;
import com.picpay.auth.domain.value_objects.CNPJ;
import com.picpay.auth.domain.value_objects.CPF;
import com.picpay.auth.domain.value_objects.Email;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "auth")
public class User extends AbstractAggregateRoot<User> {
    @EmbeddedId
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    protected UserId id;
    protected String fullName;
    protected String document;
    @Embedded
    protected Email email;
    protected String password;
    protected UserType type;

    User() {}
    
    public User(
        String fullName,
        String document,
        String email,
        String password,
        UserType type
    ) {
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.fullName = fullName;

        Objects.requireNonNull(type, "email must not be null");
        this.email = new Email(email);

        Objects.requireNonNull(password, "password must not be null");
        this.password = password;

        Objects.requireNonNull(type, "type must not be null");
        this.type = type;

        Objects.requireNonNull(document, "document must not be null");
        this.document = documentForType(document, this.type);

        this.domainEvents().add(new UserCreated(this.id, this.type));
    }

    private static String documentForType(String input, UserType type) {
        return switch (type) {
            case RETAILER -> new CNPJ(input).value();
            case CUSTOMER -> new CPF(input).value();
            default -> throw new RuntimeException("Unimplemented document for type");
        };
    }
}
