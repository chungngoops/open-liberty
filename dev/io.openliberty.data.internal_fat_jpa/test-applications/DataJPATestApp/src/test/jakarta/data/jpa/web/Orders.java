/*******************************************************************************
 * Copyright (c) 2022,2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package test.jakarta.data.jpa.web;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import jakarta.data.Sort;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Insert;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;

/**
 * Experiments with auto-generated keys.
 */
@Repository
public interface Orders extends CrudRepository<Order, Long> {

    @Query("UPDATE Orders o SET o.total = o.total * :rate + :shipping WHERE o.id = :id")
    boolean addTaxAndShipping(@Param("id") long orderId,
                              @Param("rate") float taxRate,
                              @Param("shipping") float shippingCost);

    @Delete
    int cancel(Order... orders);

    @Insert
    LinkedList<Order> create(Iterable<Order> order);

    @Insert
    Order create(Order order);

    @Insert
    Order[] create(Order... orders);

    @OrderBy("id")
    Optional<Order> findFirstByPurchasedBy(String purchaser);

    List<Float> findTotalByPurchasedByIn(Iterable<String> purchasers, Sort... sorts);

    @Update
    boolean modify(Order order);

    @Update
    Order[] modifyAll(Order... orders);

    @Update
    Optional<Order> modifyIfMatching(Order orders);

    @Update
    Vector<Order> modifyMultiple(Collection<Order> orders);

    @Update
    Order modifyOne(Order orders);
}
