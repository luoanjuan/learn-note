package middleware.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * guava中的集合工具类有：Collections2,Lists,Sets,Maps,QUeues,Multisets,Multimaps,Tables
 * Created by wb-zj373670 on 2019/1/14.
 */
public class CollectionsTest {
    public static void main(String[] args) {
        SetsTest();
    }

    /**
     * 很多支持所有集合的操作都在Iterables类中。大多数Iterables方法有一个在Iterators类中的对应版本，用来处理Iterator
     * 返回的是FluentIterable，JAVA中的Collection继承了Iterable，所以List和set这些都是Iterable
     */
    public static void IterableTest(){
        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6
        List<String> list = new ArrayList<>();
        list.add("zzzz");
        Iterable<String> stringIterables = Iterables.concat(list,new ArrayList<String>());
        Integer lastAdded = Iterables.getLast(concatenated);
        // stringIterable如果不是含有唯一的元素，会报错
        String test = Iterables.getOnlyElement(stringIterables);
        System.out.println(lastAdded);
        System.out.println(test);
    }

    /**
     * 工厂方法：basic, with elements, from Iterable, with exact capacity, with expected size, from Iterator
     * 函数式编程
     * 其他工具方法
     */
    public static void ListTests(){
        List countUp = Ints.asList(1, 2, 3, 4, 5);
        List countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List> parts = Lists.partition(countUp, 2);//{{1,2}, {3,4}, {5}}
    }

    /**
     * 静态工厂方法:HashSet：basic, with elements, from Iterable, with expected size, from Iterator；
     *             LinkedHashSet:basic, from Iterable, with expected size
     *             TreeSet：basic, with Comparator, from Iterable
     *集合操作方法：交集，并集等 返回SetView
     * 其他工具方法
     */
    public static void SetsTest(){
        Set<Integer> para1 = ImmutableSet.of(1,2,3,4,5);
        Set<Integer> para2 = ImmutableSet.of(1,3,6,7,8);
        Sets.SetView<Integer> setView = Sets.intersection(para1, para2);
        Set<Integer> result = setView.immutableCopy();
        for(Integer in : result) System.out.println(in);

        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
//  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
        Set<Set<String>> animalSets = Sets.powerSet(animals);// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
    }
}
