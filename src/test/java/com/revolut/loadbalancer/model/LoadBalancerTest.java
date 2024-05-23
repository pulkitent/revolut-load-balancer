package com.revolut.loadbalancer.model;

import com.revolut.loadbalancer.exception.CapacityExceededException;
import com.revolut.loadbalancer.exception.DuplicateInstanceException;
import java.util.LinkedList;
import java.util.Random;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {

    @Test
    @DisplayName("")
    void registerAnInstance_whenAnInstanceIsGivenThenShouldRegisterSuccessfully() {
        //given
        Instance instance = new Instance("https://facebook.com");
        LoadBalancer loadBalancer = new LoadBalancer(new LinkedList<>(), new Random());

        //when - then
        Assertions.assertDoesNotThrow(() -> loadBalancer.registerAnInstance(instance));
    }

    @Test
    @DisplayName("")
    void registerAnInstance_whenAnInstanceIsGivenAndThatIsAlreadyRegisteredThenShouldNotRegisterSuccessfully() {
        //given
        Instance instance = new Instance("https://facebook.com");
        LinkedList<Instance> instances = new LinkedList<>();
        instances.add(instance);
        LoadBalancer loadBalancer = new LoadBalancer(instances, new Random());
        Instance instance2 = new Instance("https://facebook.com");

        //when - then
        Assertions.assertThrows(DuplicateInstanceException.class, () -> loadBalancer.registerAnInstance(instance2));
    }

    @Test
    @DisplayName("")
    void registerAnInstance_whenAnInstanceIsGivenAndCapacityIsFullThenShouldNotRegisterSuccessfully() {
        //given
        Instance instance = new Instance("https://facebook.com");
        Instance instance1 = new Instance("https://facebook1.com");
        Instance instance2 = new Instance("https://facebook2.com");
        Instance instance3 = new Instance("https://facebook3.com");
        Instance instance4 = new Instance("https://facebook4.com");
        Instance instance5 = new Instance("https://facebook5.com");
        Instance instance6 = new Instance("https://facebook6.com");
        Instance instance7 = new Instance("https://facebook7.com");
        Instance instance8 = new Instance("https://facebook8.com");
        Instance instance9 = new Instance("https://facebook9.com");
        Instance instance10 = new Instance("https://facebook10.com");
        LinkedList<Instance> instances = new LinkedList<>();
        instances.add(instance);
        instances.add(instance1);
        instances.add(instance2);
        instances.add(instance3);
        instances.add(instance4);
        instances.add(instance5);
        instances.add(instance6);
        instances.add(instance7);
        instances.add(instance8);
        instances.add(instance9);
        instances.add(instance10);
        LoadBalancer loadBalancer = new LoadBalancer(instances, new Random());
        Instance instance11 = new Instance("https://facebook12.com");

        //when - then
        assertThrows(CapacityExceededException.class, () -> loadBalancer.registerAnInstance(instance11));
    }

    @Test
    @DisplayName("")
    void get_ShouldReturnInstanceSuccessfullyAndRandomly() {
        //given
        Instance instance0 = new Instance("https://facebook.com");
        Instance instance1 = new Instance("https://facebook1.com");
        LinkedList<Instance> instances = new LinkedList<>();
        instances.add(instance0);
        instances.add(instance1);
        RandomGenerator mockRandomInstance = Mockito.mock(RandomGenerator.class);
        Mockito.when(mockRandomInstance.nextInt(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(0);
        LoadBalancer loadBalancer = new LoadBalancer(instances, mockRandomInstance);

        //when
        Instance resultInstance = loadBalancer.get();

        //then
        Assertions.assertNotNull(resultInstance);
        Assertions.assertEquals(instance0, resultInstance);
    }
}
