package com.revolut.loadbalancer.model;

import com.revolut.loadbalancer.exception.CapacityExceededException;
import com.revolut.loadbalancer.exception.DuplicateInstanceException;
import java.util.List;
import java.util.random.RandomGenerator;

public class LoadBalancer {

    private final List<Instance> instances;

    private final RandomGenerator randomGenerator;

    private static final int MAX_SIZE_ALLOWED = 10;

    public LoadBalancer(List<Instance> instances, RandomGenerator randomGenerator) {

        this.instances = instances;
        this.randomGenerator = randomGenerator;
    }

    public synchronized void registerAnInstance(final Instance instance) {

        if (instances.contains(instance)) {
            throw new DuplicateInstanceException("We can't register a same instance twice");
        }

        if (instances.size() >= MAX_SIZE_ALLOWED) {
            throw new CapacityExceededException("Maximum size allowed is: " + MAX_SIZE_ALLOWED);
        }

        instances.add(instance);
    }

    public synchronized Instance get() {

        int randomIndex = randomGenerator.nextInt(0, instances.size() - 1);
        return instances.get(randomIndex);
    }
}
