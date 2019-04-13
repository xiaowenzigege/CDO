package com.cdo.util.serial;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectorImpl<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
    	BiConsumer<Set<T>, T> t=(t1,t2)->{
    		t1.add(t2);
    	};
    	System.out.println("BiConsumer.....");
        return t;
    }
    
    @Override
    public BinaryOperator<Set<T>> combiner() {
    	System.out.println("BinaryOperator.....");
        return (set, item) -> {set.addAll(item); return set;};
    }




    @Override
    public Function<Set<T>, Set<T>> finisher() {

        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH,Collector.Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome");
        Set<String> set = list.stream().collect(new MyCollectorImpl<>());
        set.forEach(System.out::println);
    }
}