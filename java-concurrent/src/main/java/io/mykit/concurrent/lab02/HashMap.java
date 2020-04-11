/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.concurrent.lab02;

import java.util.Set;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class HashMap<K ,V> implements Map<K, V> {
    private Entry<K ,V> [] table = null;
    private int size = 0;

    public HashMap(){
        this.table = new Entry[16];
    }

    @Override
    public V put(K k, V v) {
        int index = this.hash(k);
        Entry<K, V> entry = table[index];
        if(entry == null){
            table[index] = new Entry<>(k, v, index, null);
        }else{
            table[index] = new Entry<>(k, v, index, entry);
        }
        size++;
        return table[index].getValue();
    }

    private int hash(K k) {
       int index =  Math.abs(k.hashCode()) % (16 - 1);
       return index;
    }

    @Override
    public V get(K k) {
        if(size == 0){
            return null;
        }
        int index = hash(k);
        Entry<K, V>  entry = getEntry(k, index);
        return entry == null ? null : entry.getValue();
    }

    private Entry<K, V> getEntry(K k, int index) {
        for(Entry<K, V> e = table[index]; e != null; e = e.next){
            if(e.hash == index && (k == e.getKey() || e.getKey().equals(k))){
                return e;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    class Entry<K ,V> implements Map.Entry<K, V>{
        K k;
        V v;
        int hash;
        Entry<K ,V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
