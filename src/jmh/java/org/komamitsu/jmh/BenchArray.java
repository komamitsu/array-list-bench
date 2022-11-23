package org.komamitsu.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@State(Scope.Benchmark)
@Fork(1)
@Measurement(iterations = 8)
public class BenchArray {
    private static final int ARRAY_SIZE = 1000000;

    private static final class Pojo {
        String name;
        int age;

        public Pojo(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private static final Pojo value = new Pojo("foobar", 42);

    public Pojo[] array;
    public List<Pojo> arrayList;
    public Random random;

    @Setup(Level.Invocation)
    public void init() {
        array = new Pojo[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = value;
        }

        arrayList = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            arrayList.add(value);
        }

        random = new Random();
    }

    @Benchmark
    public void benchArray(BenchArray ba) {
        int pos = random.nextInt(ARRAY_SIZE);
        Pojo x = ba.array[pos];
        x.age++;
        ba.array[pos] = x;
    }

    @Benchmark
    public void benchArrayList(BenchArray ba) {
        int pos = random.nextInt(ARRAY_SIZE);
        Pojo x = ba.arrayList.get(pos);
        x.age++;
        ba.arrayList.set(pos, x);
    }
}
