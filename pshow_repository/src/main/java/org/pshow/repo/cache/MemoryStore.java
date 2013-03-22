/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pshow.repo.cache;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author roy
 * 
 */
public class MemoryStore<K extends Serializable, V extends Object> implements Store<K, V> {

    private ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<K, V>();

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public V get(K k) {
        return cache.get(k);
    }

    @Override
    public void put(K k, V v) {
        cache.put(k, v);
    }

    @Override
    public void remove(K k) {
        cache.remove(k);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean contains(K k) {
        return cache.containsKey(k);
    }

    @Override
    public boolean hasValue(V v) {
        return cache.containsValue(v);
    }

}
