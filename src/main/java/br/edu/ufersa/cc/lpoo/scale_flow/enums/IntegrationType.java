package br.edu.ufersa.cc.lpoo.scale_flow.enums;

import java.util.HashSet;
import java.util.Set;

import lombok.val;

public enum IntegrationType {

    OBSERVER,
    MEMBER(OBSERVER),
    ADMIN(MEMBER);

    private final Set<IntegrationType> includedValues = new HashSet<>();

    private IntegrationType(final IntegrationType... includedValues) {
        for (val value : includedValues) {
            this.includedValues.add(value);
            this.includedValues.addAll(value.includedValues);
        }
    }

}
