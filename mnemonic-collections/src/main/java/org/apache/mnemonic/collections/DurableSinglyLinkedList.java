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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.mnemonic.Durable;
import org.apache.mnemonic.EntityFactoryProxy;
import org.apache.mnemonic.DurableType;

/**
 * this class defines a non-volatile node for a generic value to form a
 * unidirectional link
 *
 */
public abstract class DurableSinglyLinkedList<E> implements Durable, Iterable<E> {
  protected transient EntityFactoryProxy[] m_node_efproxies;
  protected transient DurableType[] m_node_gftypes;
  protected long pheadhandler = 0L;
  protected SinglyLinkedNode<E> m_cur_node;

  /**
   * creation callback for initialization
   *
   */
  @Override
  public void initializeAfterCreate() {
    // System.out.println("Initializing After Created");
  }

  /**
   * restore callback for initialization
   *
   */
  @Override
  public void initializeAfterRestore() {
    // System.out.println("Initializing After Restored");
  }

  /**
   * this function will be invoked by its factory to setup generic related info
   * to avoid expensive operations from reflection
   *
   * @param efproxies
   *          specify a array of factory to proxy the restoring of its generic
   *          field objects
   *
   * @param gftypes
   *          specify a array of types corresponding to efproxies
   */
  @Override
  public void setupGenericInfo(EntityFactoryProxy[] efproxies, DurableType[] gftypes) {
    m_node_efproxies = efproxies;
    m_node_gftypes = gftypes;
  }


  public abstract SinglyLinkedNode<E> createNode();

  /**
   * get current node
   *
   * @return the current node
   *
   */
  public SinglyLinkedNode<E> getCurrentNode() {
    return m_cur_node;
  }

  public abstract boolean forwardNode();

  public abstract SinglyLinkedNode<E> getNode(long handler);

  public abstract boolean addNode(SinglyLinkedNode<E> newnode);

  public abstract boolean add(E item);

  /**
   * can be used to release list chain
   */
  public void reset() {
    m_cur_node = null;
  }

  public void setHeadHandler(long handler) {
    pheadhandler = handler;
  }

  /**
   * get an iterator instance of this list
   *
   * @return an iterator of this list
   */
  @Override
  public Iterator<E> iterator() {
    return new Intr();
  }

  /**
   * this class defines a iterator for this non-volatile list
   *
   */
  private class Intr implements Iterator<E> {

    protected boolean gotNext = false;
    protected SinglyLinkedNode<E> next = null;

    /**
     * determine the existing of next
     *
     * @return true if there is a next node
     *
     */
    @Override
    public boolean hasNext() {
      if (!gotNext) {
        if (null == next) {
          next = getNode(pheadhandler);
        } else {
          next = next.getNext();
        }
        gotNext = true;
      }
      return null != next;
    }

    /**
     * get next node
     *
     * @return the next node
     */
    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException("End of stream");
      }
      gotNext = false;
      return next.getItem();
    }

  }
}
