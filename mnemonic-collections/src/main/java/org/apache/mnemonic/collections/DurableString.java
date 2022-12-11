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

package org.apache.mnemonic.collections;

import org.apache.mnemonic.Durable;
import org.apache.mnemonic.DurableEntity;
import org.apache.mnemonic.DurableGetter;
import org.apache.mnemonic.DurableSetter;
import org.apache.mnemonic.DurableType;
import org.apache.mnemonic.EntityFactoryProxy;
import org.apache.mnemonic.OutOfHybridMemory;
import org.apache.mnemonic.RetrieveDurableEntityError;

@DurableEntity
public abstract class DurableString implements Durable, Comparable<DurableString> {

  @Override
  public void initializeAfterCreate() {
  }

  @Override
  public void initializeAfterRestore() {
  }

  @Override
  public void setupGenericInfo(EntityFactoryProxy[] efproxies, DurableType[] gftypes) {

  }

  public int compareTo(DurableString anotherString) {
    int ret = getStr().compareTo(anotherString.getStr());
    return ret;
  }

  @DurableGetter
  public abstract String getStr() throws RetrieveDurableEntityError;

  @DurableSetter
  public abstract void setStr(String str, boolean destroy)
      throws OutOfHybridMemory, RetrieveDurableEntityError;

}
