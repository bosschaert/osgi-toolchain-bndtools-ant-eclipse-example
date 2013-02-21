/*
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
package org.example.osgi.impl;

import java.util.Dictionary;

import junit.framework.TestCase;

import org.example.osgi.MyOSGiService;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;

public class ActivatorTest extends TestCase {
    public void testRegisterServiceInStart() throws Exception {
        BundleContext mockContext = Mockito.mock(BundleContext.class);

        Activator a = new Activator();
        a.start(mockContext);

        Mockito.verify(mockContext).registerService(
                Mockito.eq(MyOSGiService.class.getName()),
                Mockito.isA(MyOSGiServiceImpl.class),
                (Dictionary<?, ?>) Mockito.isNull());
    }
}
