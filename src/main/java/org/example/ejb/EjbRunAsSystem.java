package org.example.ejb;

import javax.annotation.security.RunAs;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

@LocalBean
@Stateless
@RunAs("System")
public class EjbRunAsSystem {
    @Inject
    private SecurityContext securityContext;
    @Inject
    private EjbWithoutRunAs ejbWithoutRunAs;

    public void test() {
        System.out.println("ejbRunAsSystem.test isSystem: " + securityContext.isCallerInRole("System"));
        System.out.println("ejbRunAsSystem.test isAdmin: " + securityContext.isCallerInRole("Admin"));
        System.out.println();
    }

    public void testAnotherEjb() {
        ejbWithoutRunAs.testFromRunAsSystem();
    }
}
