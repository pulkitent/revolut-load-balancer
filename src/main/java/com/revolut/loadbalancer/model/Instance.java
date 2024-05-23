package com.revolut.loadbalancer.model;

import java.util.Objects;

public class Instance {

    private final String address;

    public Instance(String address) {

        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instance instance = (Instance) o;
        return Objects.equals(address, instance.address);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(address);
    }
}
