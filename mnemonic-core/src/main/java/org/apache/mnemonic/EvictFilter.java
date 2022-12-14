/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mnemonic;

/**
 * A listener callback to validate its accepted key-value pair for evacuation.
 *
 */
public interface EvictFilter<KeyT, ValueT> {
  /**
   * A call-back validator when an entry will be evicted.
   *
   * @param pool
   *          the pool which an entry has been evicted from
   *
   * @param k
   *          the entry's key
   *
   * @param v
   *          the entry's value
   *
   * @return <code>true</code> if the entry is allowed to be dropped from its cache
   *         pool.
   */
  boolean validate(CachePool<KeyT, ValueT> pool, KeyT k, ValueT v);
}
