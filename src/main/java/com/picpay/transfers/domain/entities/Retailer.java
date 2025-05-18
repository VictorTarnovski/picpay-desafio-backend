package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.*;
import com.picpay.transfers.domain.value_objects.*;

public final class Retailer extends User {
    public Retailer(
        String fullName,
        CNPJ cnpj,
        Email email,
        UserPasswordProvider provider,
        Money initialBalance
    ) {
        super(fullName, cnpj.toString(), email, provider, initialBalance);
    }
}
