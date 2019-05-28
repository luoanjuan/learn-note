package jdkz.javaspliterator;

import java.util.Spliterator;

/**
 * Created by wb-zj373670 on 2019/1/10.
 */
public class TaggedArray<T> {
    private final Object[] elements;
    TaggedArray(T[] data, Object[] tags){
        int size = data.length;
        if(tags.length != size) throw new IllegalArgumentException();
        elements = new Object[size*2];
        for(int i = 0, j = 0; i < size; i++){
            elements[j++] = data[i];
            elements[j++] = tags[i];
        }
    }

    public Spliterator<T> spliterator() {
        return null;
    }

//    static class TaggedArraySpliterator<T> implements Spliterator<T>{
//        private final Object[] array;
//    }



















}
