/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.test;

import com.alibaba.csp.sentinel.util.TimeUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * Mock support for {@link TimeUtil}
 *
 * @author jason
 */
public abstract class AbstractTimeBasedTest {

    private static long currentMillis = 0;
    static MockedStatic<?> mockedStatic;

    @BeforeClass
    public static void beforeClass() {
        mockedStatic = Mockito.mockStatic(TimeUtil.class);
        Mockito.when(TimeUtil.currentTimeMillis()).thenReturn(currentMillis);
    }

    @AfterClass
    public static void afterClass() {
        mockedStatic.close();
    }

    protected final void useActualTime() {
        Mockito.when(TimeUtil.currentTimeMillis()).thenCallRealMethod();
    }

    protected final void setCurrentMillis(long cur) {
        currentMillis = cur;
        Mockito.when(TimeUtil.currentTimeMillis()).thenReturn(currentMillis);
    }

    protected final void sleep(int t) {
        currentMillis += t;
        Mockito.when(TimeUtil.currentTimeMillis()).thenReturn(currentMillis);
    }

    protected final void sleepSecond(int timeSec) {
        sleep(timeSec * 1000);
    }
}
