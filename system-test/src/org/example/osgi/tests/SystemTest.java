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
package org.example.osgi.tests;

import junit.framework.TestCase;

import org.example.osgi.MyOSGiService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class SystemTest extends TestCase {
    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

    public void testServiceRegistered() throws Exception {
        ServiceReference ref = context.getServiceReference(MyOSGiService.class.getName());
        assertEquals("service-bundle", ref.getBundle().getSymbolicName());
    }

    public void testServiceConsumed() throws Exception {
        ServiceReference ref = context.getServiceReference(MyOSGiService.class.getName());
        for (Bundle b : ref.getUsingBundles()) {
            if (b.getSymbolicName().equals("consumer-bundle")) {
                // good, it's consumed by the consumer bundle
                return;
            }
        }
        fail("The consumer bundle isn't consuming the service");
    }
}
