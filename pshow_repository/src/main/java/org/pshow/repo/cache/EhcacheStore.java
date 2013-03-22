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

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.util.Assert;

/**
 * @author roy
 *
 */
public class EhcacheStore<K extends Serializable, V extends Object> implements Store<K, V> {
	private CacheManager cacheManager = null;
	private String name;
 
	private Ehcache ehcache = null;
 
	public void init() {
		Assert.hasText(name, "the name of EhCacheMetisStore must have text");
		Assert.notNull(cacheManager, "this cacheManager must not be null");
		Assert.isTrue(cacheManager.cacheExists(name), "ehcache whose cacheName is " + name + " is not configured");
 
		this.ehcache = cacheManager.getEhcache(name);
	}
 
	@Override
	public void clear() {
		ehcache.removeAll();
	}
 
	@SuppressWarnings("unchecked")
    @Override
	public V get(K k) {
		Element element = ehcache.get(k);
		return element == null ? null : (V) element.getValue();
	}
 
	@Override
	public void put(K k, V v) {
		ehcache.put(new Element(k, v));
	}
 
	@Override
	public void remove(K k) {
		ehcache.remove(k);
	}
 
	@Override
	public int size() {
		return ehcache.getSize();
	}
 
	/** spring inject */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

    @Override
    public boolean contains(K k) {
        return ehcache.isKeyInCache(k);
    }

    @Override
    public boolean hasValue(V v) {
        return ehcache.isValueInCache(v);
    }
}
