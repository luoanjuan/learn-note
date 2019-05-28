package daily.guava;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * guava集合类
 * Created by wb-zj373670 on 2019/1/14.
 */
public class CollectionTest {

    public static void main(String[] args) {


    }

    /**
     * size(),iterator(),entrySet()返回的是所有值（包含重复值），elementSet返回不重复的值
     */
    public static void  MultiSetTest(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("aa");
        multiset.add("aa");
        multiset.add("bb");
        multiset.add("bb");
        multiset.add("bb");
        System.out.println(multiset.count("aa")); //2

    }

    /**
     * MultiMap 键可重复，获取时返回的是键对应值的集合，主要两个接口SetMultiMap,ListMultiMap
     *
     */
    public static void MultiMapTest(){
        //MutilSet, 主要两个接口
        SetMultimap<Integer, String> multimap = TreeMultimap.create();
        multimap.put(1, "aa");
        multimap.put(1, "aaa");
        multimap.put(2, "bb");
        multimap.put(2, "bbb");
        // keys()包含重复值的
        Multiset<Integer> set =  multimap.keys();
        for (Integer integer : set ) System.out.println(integer);

        // keySet()不包含重复值的
        Set<Integer> set1 =  multimap.keySet();
        for (Integer integer : set1 ) System.out.println(integer);

        //包含重复值的
       Set<Map.Entry<Integer, String>> entries = multimap.entries();
       for(Map.Entry entry : entries){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        ListMultimap<Integer, String> multimap1 = LinkedListMultimap.create();
        multimap1.put(1, "aa");
        multimap1.put(1, "aaa");
        multimap1.put(2, "bb");
        multimap1.put(2, "bbb");
        //对获取到某个key集合的修改会反映到底层的MultiMap上
        List<String> collection1 = multimap1.get(3);
        collection1.add("ccc");
        collection1 = multimap1.get(3);
        for (Object aCollection : collection1) System.out.println(aCollection);
    }

    /**
     * BiMap 可以通过inverse()方法得到值到键的映射
     */
    public static void BiMapTest(){
        //和BiMap的put方法document说明有点冲突啊,put方法说再次添加已存在的值会抛异常，实践证明是覆盖
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "aa");
        BiMap biMap1 = biMap.inverse();
        Object b = biMap1.get("aa");
        System.out.println(b);
        biMap.put(1, "aaa");
        biMap.put(2, "aa");
        b = biMap1.get("aa");
        System.out.println(b);
    }

    /**
     * Table有两个支持所有类型的键，可以根据其中一个键的值得到另外一个键到值的映射
     */
    public static void  TableTest(){
        Table<Integer, Integer, Double> weightedGraph = HashBasedTable.create();
        weightedGraph.put(1, 2, 4.0);
        weightedGraph.put(1, 3, 20.0);
        weightedGraph.put(2, 3, 5.0);
        Map<Integer, Double> map = weightedGraph.row(1); // returns a Map mapping v2 to 4, v3 to 20
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        map = weightedGraph.column(3); // returns a Map mapping v1 to 20, v2 to 5
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 类型到实例的映射，可以添加泛型，泛型类是所有键类型的父类。
     * Guava提供了两种有用的实现：MutableClassToInstanceMap和 ImmutableClassToInstanceMap
     */
    public static void ClassToInstanceMapTest(){
        ClassToInstanceMap  classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.putInstance(Integer.class, 1);
        classToInstanceMap.putInstance(Integer.class,2); // 覆盖
        Integer a = (Integer)classToInstanceMap.getInstance(Integer.class);
        System.out.println(a);
    }

    /**
     * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。
     */
    public static void RangeSetTest(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}
        Set<Range<Integer>> sets =  rangeSet.asRanges();
        for(Range<Integer> range : sets){
            System.out.println(range.toString());
        }
    }

    /**
     * RangeMap描述了”不相交的、非空的区间”到特定值的映射。
     * 和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。
     */
    public static void RangeMap(){
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
    }


}
