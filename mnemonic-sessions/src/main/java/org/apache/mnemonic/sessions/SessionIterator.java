/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mnemonic.sessions;

import java.util.Iterator;

import org.apache.mnemonic.CloseableIterator;
import org.apache.mnemonic.RestorableAllocator;

public interface SessionIterator<V, A extends RestorableAllocator<A>, T, S>
    extends CloseableIterator<V>, DurableComputable<A> {

  void setAllocator(A alloc);

  void setHandler(long hdl);

  void setIterator(Iterator<V> iter);

  Iterator<V> getIterator();

  void setSourceIterator(Iterator<T> iter);

  Iterator<T> getSourceIterator();

  S getState();

  void setState(S state);

}
