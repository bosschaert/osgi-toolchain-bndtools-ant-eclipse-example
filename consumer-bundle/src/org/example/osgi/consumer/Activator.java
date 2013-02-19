package org.example.osgi.consumer;

import org.example.osgi.MyOSGiService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    private ServiceTracker st;

    @Override
    public void start(BundleContext context) throws Exception {
        st = new ServiceTracker(context, MyOSGiService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object svc = super.addingService(reference);
                if (svc instanceof MyOSGiService) {
                    invokeService((MyOSGiService) svc);
                }
                return svc;
            }
        };
        st.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        st.close();
    }

    void invokeService(MyOSGiService svc) {
        long input = 42L;
        System.out.println("Invoking Service with input: " + input);
        System.out.println("  Result: " + svc.performOp(input));
    }
}
