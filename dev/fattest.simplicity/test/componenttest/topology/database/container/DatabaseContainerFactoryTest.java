/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package componenttest.topology.database.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Map;

import org.junit.Test;
import org.testcontainers.containers.Db2Container;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import componenttest.custom.junit.runner.FATRunner;

public class DatabaseContainerFactoryTest {

    @Test
    public void testDB2ContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> db2 = DatabaseContainerFactory.createType(DatabaseContainerType.DB2);
        assertTrue(db2.getClass().isAssignableFrom(Db2Container.class));
        Db2Container _db2 = (Db2Container) db2;

        Map<String, String> env = getField(Db2Container.class, _db2, "env", Map.class);
        assertTrue(env.containsKey("LICENSE"));
        assertEquals("accept", env.get("LICENSE"));

        WaitStrategy waitStrategy = getField(Db2Container.class, _db2, "waitStrategy", WaitStrategy.class);
        assertTrue(waitStrategy.getClass().isAssignableFrom(LogMessageWaitStrategy.class));
        LogMessageWaitStrategy _waitStrategy = (LogMessageWaitStrategy) waitStrategy;

        Duration startupTimeout = getField(LogMessageWaitStrategy.class, _waitStrategy, "startupTimeout", Duration.class);
        if (startupTimeout.toMinutes() == 5) {
            assertTrue(FATRunner.FAT_TEST_LOCALRUN && !FATRunner.ARM_ARCHITECTURE);
        } else if (startupTimeout.toMinutes() == 15) {
            assertFalse(FATRunner.FAT_TEST_LOCALRUN && !FATRunner.ARM_ARCHITECTURE);
        } else {
            fail("Unexpected startupTimeout " + startupTimeout.toMinutes() + " should have been either 5 or 15 minutes");
        }

    }

    @Test
    public void testDerbyContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> derby = DatabaseContainerFactory.createType(DatabaseContainerType.Derby);
        assertTrue(derby.getClass().isAssignableFrom(DerbyNoopContainer.class));
    }

    @Test
    public void testDerbyClientContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> derbyClient = DatabaseContainerFactory.createType(DatabaseContainerType.DerbyClient);
        assertTrue(derbyClient.getClass().isAssignableFrom(DerbyClientContainer.class));
    }

    @Test
    public void testOracleContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> oracle = DatabaseContainerFactory.createType(DatabaseContainerType.Oracle);
        assertTrue(oracle.getClass().isAssignableFrom(OracleContainer.class));
        OracleContainer _oracle = (OracleContainer) oracle;

        boolean usingSid = getField(OracleContainer.class, _oracle, "usingSid", boolean.class);
        assertTrue(usingSid);

        WaitStrategy waitStrategy = getField(OracleContainer.class, _oracle, "waitStrategy", WaitStrategy.class);
        assertTrue(waitStrategy.getClass().isAssignableFrom(LogMessageWaitStrategy.class));
        LogMessageWaitStrategy _waitStrategy = (LogMessageWaitStrategy) waitStrategy;

        Duration startupTimeout = getField(LogMessageWaitStrategy.class, _waitStrategy, "startupTimeout", Duration.class);
        if (startupTimeout.toMinutes() == 3) {
            assertTrue(FATRunner.FAT_TEST_LOCALRUN && !FATRunner.ARM_ARCHITECTURE);
        } else if (startupTimeout.toMinutes() == 25) {
            assertFalse(FATRunner.FAT_TEST_LOCALRUN && !FATRunner.ARM_ARCHITECTURE);
        } else {
            fail("Unexpected startupTimeout " + startupTimeout.toMinutes() + " should have been either 3 or 25 minutes");
        }
    }

    @Test
    public void testPostgreSQLContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> postgresql = DatabaseContainerFactory.createType(DatabaseContainerType.Postgres);
        assertTrue(postgresql.getClass().isAssignableFrom(PostgreSQLContainer.class));
        PostgreSQLContainer _postgresql = (PostgreSQLContainer) postgresql;

        String[] cmdParts = _postgresql.getCommandParts();
        assertEquals(3, cmdParts.length);
        assertEquals("postgres", cmdParts[0]);
        assertEquals("-c", cmdParts[1]);
        assertEquals("max_prepared_transactions=5", cmdParts[2]);
    }

    @Test
    public void testSQLServerContainerCreation() throws Exception {
        JdbcDatabaseContainer<?> sqlserver = DatabaseContainerFactory.createType(DatabaseContainerType.SQLServer);
        assertTrue(sqlserver.getClass().isAssignableFrom(MSSQLServerContainer.class));
        MSSQLServerContainer _sqlserver = (MSSQLServerContainer) sqlserver;

        Map<String, String> env = getField(MSSQLServerContainer.class, _sqlserver, "env", Map.class);
        assertTrue(env.containsKey("ACCEPT_EULA"));
        assertEquals("Y", env.get("ACCEPT_EULA"));
    }

    private static <T> T getField(Class<?> clazz, Object obj, String name, Class<T> type) throws Exception {
        Field f = null;
        Class<?> tempClazz = clazz;

        while (tempClazz.getSuperclass() != null) {
            try {
                f = tempClazz.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException e) {
                tempClazz = tempClazz.getSuperclass();
            }
        }

        if (f == null) {
            throw new RuntimeException("Could not find field with name: " + name);
        }

        assertTrue(f.getType().isAssignableFrom(type));
        f.setAccessible(true);
        return (T) f.get(obj);
    }

}
